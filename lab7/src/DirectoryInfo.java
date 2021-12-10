public class DirectoryInfo {
    private int totalSubdirectoriesCount;
    private int totalFiles;
    private int totalFilesBiggerThan;
    private int totalFilesPattern;

    public DirectoryInfo() {
        this.totalSubdirectoriesCount = 0;
        this.totalFiles = 0;
        this.totalFilesBiggerThan = 0;
        this.totalFilesPattern = 0;
    }

    public DirectoryInfo(int totalSubdirectoriesCount, int totalFiles, int totalFilesBiggerThan, int totalFilesPattern) {
        this.totalSubdirectoriesCount = totalSubdirectoriesCount;
        this.totalFiles = totalFiles;
        this.totalFilesBiggerThan = totalFilesBiggerThan;
        this.totalFilesPattern = totalFilesPattern;
    }

    public DirectoryInfo addInfo(DirectoryInfo parent){
        return new DirectoryInfo(totalSubdirectoriesCount + parent.getTotalSubdirectoriesCount(),
                totalFiles + parent.getTotalFiles(),
                totalFilesBiggerThan + parent.getTotalFilesBiggerThan(),
                totalFilesPattern + parent.getTotalFilesPattern());
    }

    public int getTotalSubdirectoriesCount() {
        return totalSubdirectoriesCount;
    }

    public void setTotalSubdirectoriesCount(int totalSubdirectoriesCount) {
        this.totalSubdirectoriesCount = totalSubdirectoriesCount;
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    public void setTotalFiles(int totalFiles) {
        this.totalFiles = totalFiles;
    }

    public int getTotalFilesBiggerThan() {
        return totalFilesBiggerThan;
    }

    public void setTotalFilesBiggerThan(int totalFilesBiggerThan) {
        this.totalFilesBiggerThan = totalFilesBiggerThan;
    }

    public int getTotalFilesPattern() {
        return totalFilesPattern;
    }

    public void setTotalFilesPattern(int totalFilesPattern) {
        this.totalFilesPattern = totalFilesPattern;
    }

    @Override
    public String toString() {
        return "Total Subdirectories Count = " + totalSubdirectoriesCount +
                ", Total Files = " + totalFiles +
                ", Total Files Bigger Than = " + totalFilesBiggerThan +
                ", Total Files Pattern = " + totalFilesPattern +
                '}';
    }
}
