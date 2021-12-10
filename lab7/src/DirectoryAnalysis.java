import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryAnalysis {
    File startDirectory;

    DirectoryAnalysis(File filePath){
        startDirectory = filePath;
    }

    public static DirectoryInfo analyse(File filePath, int maxSize, String pattern){
        Map<Boolean, List<File>> elementsMap = Stream.of(filePath.listFiles()).collect(Collectors.partitioningBy(file -> file.isDirectory()));
        List<File> directories = elementsMap.get(true);
        List<File> files = elementsMap.get(false);
        DirectoryInfo info = new DirectoryInfo();

        for(File directory : directories){
            info = info.addInfo(analyse(directory, maxSize, pattern));
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

}
