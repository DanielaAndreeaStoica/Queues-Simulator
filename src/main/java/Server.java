
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    public BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int numberOfServer;

    public Server(int numberOfServer) {
        this.tasks = new LinkedBlockingDeque<Task>();
        this.waitingPeriod = new AtomicInteger(0);
        this.numberOfServer=numberOfServer;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.set(this.waitingPeriod.get() + newTask.getProcessingTime());
    }


    public void run() {
        while (true) {
            try {

                if (!(tasks.isEmpty())) {

                    tasks.element().decrementProcessingTime();
                    if (tasks.element().getProcessingTime() <= 0) {
                        tasks.take();
                    }
                    waitingPeriod.set(waitingPeriod.get() - 1);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    @Override
    public String toString() {
        String s = "Server " + numberOfServer +":";
        for (Task t : tasks) {
            s = s + t.toString() + " ";
        }
        return s + "\n";
    }

}
