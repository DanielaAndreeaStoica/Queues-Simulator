
public class Task implements Comparable<Task> {
    private int id;
    private int arrivalTime;
    private int processingTime;
    private int waitingPeriodOnChosenServer;

    public Task(int id, int arrivalTime, int processingTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        waitingPeriodOnChosenServer = 0;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getWaitingPeriodOnChosenServer() {
        return waitingPeriodOnChosenServer;
    }

    public void decrementProcessingTime() {
        processingTime--;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + arrivalTime + ", " + processingTime + ")";
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.arrivalTime, o.getArrivalTime());
    }

    public void setWaitingPeriodOnChosenServer(int waitingPeriodOnChosenServer) {
        this.waitingPeriodOnChosenServer = waitingPeriodOnChosenServer;
    }


}
