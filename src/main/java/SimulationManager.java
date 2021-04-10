

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    public String eventsLog = "";
    public int peakHour = 0;
    public int nrMax = 0;
    public float averageWaitingTime = 0;
    public float averageServiceTime = 0;

    private Scheduler scheduler;
    private List<Task> generatedTasks;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfClients, int numberOfServers) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        scheduler = new Scheduler(numberOfServers);
        generateNRandomTasks();
    }

    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<>();

        for (int i = 1; i <= numberOfClients; i++) {
            int arrivalTime = (int) Math.floor(Math.random() * (maxArrivalTime - minArrivalTime + 1) + minArrivalTime);
            int processingTime = (int) Math.floor(Math.random() * (maxProcessingTime - minProcessingTime + 1) + minProcessingTime);
            Task task = new Task(i, arrivalTime, processingTime);
            averageServiceTime = averageServiceTime + processingTime;
            generatedTasks.add(task);
        }
        setAverageServiceTime(averageServiceTime / numberOfClients);
        Collections.sort(generatedTasks);
    }

    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit) {
            if (shouldExit() == 1) {
                setAverageWaitingTime(averageWaitingTime / numberOfClients);
                eventsLog = eventsLog + "Current time: " + currentTime + "\n" + "Waiting tasks: " + generatedTasks.toString()+"\n"+ scheduler.toString() + "\n";
                eventsLog = eventsLog + "The simulation is over!\n";
                eventsLog = eventsLog + "Average serving time: " + averageServiceTime + "\n";
                eventsLog = eventsLog + "Average waiting time: " + averageWaitingTime + "\n";
                eventsLog = eventsLog + "Peak hour: " + peakHour;
                writeText();
                break;
            }
            while (!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime) {
                scheduler.dispatchTask(generatedTasks.get(0));
                averageWaitingTime = averageWaitingTime + generatedTasks.get(0).getWaitingPeriodOnChosenServer();
                generatedTasks.remove(0);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            eventsLog = eventsLog + "Current time: " + currentTime + "\n" + "Waiting tasks: " + generatedTasks.toString()+"\n"+ scheduler.toString() + "\n";
            findPeakHour(currentTime);
            currentTime++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int shouldExit() {
        if (generatedTasks.isEmpty()) {
            boolean exit = true;
            for (Server server : scheduler.getServers()) {
                if (server.getWaitingPeriod().get() != 0)
                    exit = false;
            }
            if (exit)
                return 1;
        }
        return 0;
    }

    public void writeText() {
        File fileOutput = new File("out.txt");
        FileWriter write = null;
        try {
            write = new FileWriter(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(write);

        pw.println(eventsLog);
        pw.close();
    }

    public void findPeakHour(int currentTime) {
        int nrTasks = 0;
        for (Server server : scheduler.getServers()) {
            nrTasks = nrTasks + server.tasks.size();
        }
        if (nrTasks > nrMax) {
            setPeakHour(currentTime);
            setNrMax(nrTasks);
        }
    }

    public void setNrMax(int nrMax) {
        this.nrMax = nrMax;
    }

    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    public String getEventsLog() {
        return eventsLog;
    }

    public void setAverageWaitingTime(float averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public void setAverageServiceTime(float averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }


    public static void main(String[] args) {
        View v = new View();
        Controller c = new Controller(v);

    }
}