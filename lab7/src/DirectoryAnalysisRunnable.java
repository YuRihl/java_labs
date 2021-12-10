import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryAnalysisRunnable implements Runnable{
    public static ThreadManager threadManager;
    private File filePath;
    private int maxSize;
    private String pattern;
    private volatile DirectoryInfo info;

    public DirectoryAnalysisRunnable(File filePath, int maxSize, String pattern){
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

        Map<Thread, DirectoryAnalysisRunnable> activeThreads = new HashMap<>();

        for(File directory : directories){
            if(threadManager.access()){
                DirectoryAnalysisRunnable analyser = new DirectoryAnalysisRunnable(directory, maxSize, pattern);
                Thread thread = new Thread(analyser);

                thread.setName("Thread " + directory.getName());
                thread.start();
                threadManager.addThread(thread);
                activeThreads.put(thread, analyser);
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

        for(var entry : activeThreads.entrySet()){
            try{
                entry.getKey().join();
            }
            catch(Exception exception){
                System.out.println(exception);
            }

            threadManager.release();
            threadManager.removeThread(entry.getKey());
            info = info.addInfo(entry.getValue().getInfo());
        }

        info.setTotalSubdirectoriesCount(info.getTotalSubdirectoriesCount() + directories.size());
        info.setTotalFiles(info.getTotalFiles() + files.size());
    }

    public static DirectoryInfo analyse(File filePath, int maxSize, String pattern, int threadCount){
        DirectoryAnalysisRunnable.threadManager = new ThreadManager(threadCount);
        DirectoryAnalysisRunnable.threadManager.access();

        DirectoryAnalysisRunnable analyser = new DirectoryAnalysisRunnable(filePath, maxSize, pattern);
        Thread thread = new Thread(analyser);

        thread.start();
        try{
            thread.join();
        }
        catch (Exception exception){
            System.out.println(exception);
        }
        DirectoryAnalysisRunnable.threadManager.release();

        return analyser.getInfo();
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
