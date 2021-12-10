import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryAnalysisThread extends Thread{
    public static volatile Integer activeThreadCount;
    public static int maxThreadCount;
    private File filePath;
    private int maxSize;
    private String pattern;
    private volatile DirectoryInfo info;

    public DirectoryAnalysisThread(File filePath, int maxSize, String pattern){
        this.filePath = filePath;
        this.maxSize = maxSize;
        this.pattern = pattern;
    }

    @Override
    public void run(){
        Map<Boolean, List<File>> elementsMap = Stream.of(filePath.listFiles()).collect(Collectors.partitioningBy(file -> file.isDirectory()));
        List<File> directories = elementsMap.get(true);
        List<File> files = elementsMap.get(false);
        info = new DirectoryInfo();

        List<DirectoryAnalysisThread> activeThreads = new ArrayList();

        for(File directory : directories){
            if(activeThreadCount < maxThreadCount){
                synchronized (DirectoryAnalysisThread.activeThreadCount){
                    DirectoryAnalysisThread.activeThreadCount++;
                }
                DirectoryAnalysisThread thread = new DirectoryAnalysisThread(directory, maxSize, pattern);

                thread.setName("Thread " + directory.getName());
                thread.start();
                activeThreads.add(thread);
            }
            else{
                info = info.addInfo(analyseOnce(directory, maxSize, pattern));
            }
        }

        for(File file : files){
            int size = (int)file.length();

            if(size > maxSize){
                info.setTotalFilesBiggerThan(info.getTotalFilesBiggerThan() + 1);
            }
            if(file.getName().matches(pattern)){
                info.setTotalFilesPattern(info.getTotalFilesPattern() + 1);
            }
        }

        for(var thread : activeThreads){
            try{
                thread.join();
            }
            catch(Exception exception){
                System.out.println(exception);
            }
            synchronized (DirectoryAnalysisThread.activeThreadCount){
                DirectoryAnalysisThread.activeThreadCount--;
            }
            info = info.addInfo(thread.getInfo());
        }

        info.setTotalSubdirectoriesCount(info.getTotalSubdirectoriesCount() + directories.size());
        info.setTotalFiles(info.getTotalFiles() + files.size());
    }

    public static DirectoryInfo analyse(File filePath, int maxSize, String pattern, int threadCount){
        DirectoryAnalysisThread.activeThreadCount = 1;
        DirectoryAnalysisThread.maxThreadCount = threadCount;

        DirectoryAnalysisThread thread = new DirectoryAnalysisThread(filePath, maxSize, pattern);
        thread.start();
        try{
            thread.join();
        }
        catch (Exception exception){
            System.out.println(exception);
        }
        DirectoryAnalysisThread.activeThreadCount = 0;

        return thread.getInfo();
    }

    public static DirectoryInfo analyseOnce(File filePath, int maxSize, String pattern){
        Map<Boolean, List<File>> elementsMap = Stream.of(filePath.listFiles()).collect(Collectors.partitioningBy(file -> file.isDirectory()));
        List<File> directories = elementsMap.get(true);
        List<File> files = elementsMap.get(false);
        DirectoryInfo info = new DirectoryInfo();

        for(File directory : directories){
            info = info.addInfo(analyseOnce(directory, maxSize, pattern));
        }

        for(File file : files){
            int size = (int)file.length();

            if(size > maxSize){
                info.setTotalFilesBiggerThan(info.getTotalFilesBiggerThan() + 1);
            }
            if(file.getName().matches(pattern)){
                info.setTotalFilesPattern(info.getTotalFilesPattern() + 1);
            }
        }

        info.setTotalSubdirectoriesCount(info.getTotalSubdirectoriesCount() + directories.size());
        info.setTotalFiles(info.getTotalFiles() + files.size());

        return info;
    }

    public DirectoryInfo getInfo() {
        return info;
    }
}
