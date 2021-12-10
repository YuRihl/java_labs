import java.io.File;

public class Program {
    public static void main(String[] args) {
        File filePath = new File("D:\\Apps");

        printOneThreadAnalysis(filePath);
        printThreadsRunnable(filePath);
        printThreadsThread(filePath);
        ui(filePath);
    }

    public static void ui(File filePath){
        DirectoryAnalysisRunnable.threadManager = new ThreadManager(16);
        DirectoryAnalysisRunnable.threadManager.access();

        DirectoryAnalysisRunnable analysis = new DirectoryAnalysisRunnable(filePath, 50000, ".*\\.json$");
        Thread thread = new Thread(analysis);
        UI ui = new UI(DirectoryAnalysisRunnable.threadManager);

        thread.start();
        ui.start();

        try{
            thread.join();
        }
        catch (Exception exception){
            System.out.println(exception);
        }

        DirectoryAnalysisRunnable.threadManager.release();
    }

    public static void printOneThreadAnalysis(File filePath){
        System.out.println("One thread analysis: ");

        long start = System.currentTimeMillis();
        DirectoryInfo info = DirectoryAnalysis.analyse(filePath, 50000, ".*\\.json$");
        long end = System.currentTimeMillis();
        long timeElapsed = (end - start) / 1000;

        System.out.println(info);
        System.out.println("Analysis time: " + timeElapsed);
    }

    public static void printThreadsRunnable(File filePath){
        System.out.println("One thread analysis: ");

        long start = System.currentTimeMillis();
        DirectoryInfo info = DirectoryAnalysisRunnable.analyse(filePath, 50000, ".*\\.json$", 16);
        long end = System.currentTimeMillis();
        long timeElapsed = (end - start) / 1000;

        System.out.println(info);
        System.out.println("Analysis time: " + timeElapsed);
    }

    public static void printThreadsThread(File filePath){
        System.out.println("One thread analysis: ");

        long start = System.currentTimeMillis();
        DirectoryInfo info = DirectoryAnalysisThread.analyse(filePath, 5000, ".*\\.json$", 16);
        long end = System.currentTimeMillis();
        long timeElapsed = (end - start) / 1000;

        System.out.println(info);
        System.out.println("Analysis time: " + timeElapsed);
    }
}
