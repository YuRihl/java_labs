import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private volatile Integer activeThreadCount;
    private int maxThreads;
    public List<Thread> threads;

    public ThreadManager(int maxThreads) {
        activeThreadCount = 0;
        this.maxThreads = maxThreads;
        threads = new ArrayList<>();
    }

    public synchronized boolean access(){
        if(activeThreadCount <= maxThreads){
            activeThreadCount++;
            return true;
        } else{
            return false;
        }
    }

    public synchronized void release(){
        activeThreadCount--;
    }

    public synchronized void addThread(Thread thread) {
        threads.add(thread);
    }

    public synchronized void removeThread(Thread thread) {
        threads.remove(thread);
    }

    public synchronized List<Thread> getThreads() {
        return threads;
    }
}
