

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    View view;

    public Controller(View v) {
        this.view = v;

        view.setClearListener(new ClearListener());
        view.setStartListener(new StartListener());
    }

    private int getNrTasks() {
        String nrTasks = view.getTasks().getText();
        return Integer.parseInt(nrTasks);
    }

    private int getNrQueue() {
        String nrQueue = view.getQueue().getText();
        return Integer.parseInt(nrQueue);
    }

    private int getSimTime() {
        String simTime = view.getSimTime().getText();
        return Integer.parseInt(simTime);
    }

    private int getMinAT() {
        String minAT = view.getMinAT().getText();
        return Integer.parseInt(minAT);
    }

    private int getMaxAT() {
        String maxAT = view.getMaxAT().getText();
        return Integer.parseInt(maxAT);
    }

    private int getMinPT() {
        String minPT = view.getMinPT().getText();
        return Integer.parseInt(minPT);
    }

    private int getMaxPT() {
        String maxPT = view.getMaxPT().getText();
        return Integer.parseInt(maxPT);
    }

    public class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setEvents("");
            view.setQueue("");
            view.setTasks("");
            view.setSimTime("");
            view.setMinAT("");
            view.setMaxAT("");
            view.setMinPT("");
            view.setMaxPT("");
        }
    }

    public class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int simTime = getSimTime();
            int maxProcessingTime = getMaxPT();
            int minProcessingTime = getMinPT();
            int maxArrivalTime = getMaxAT();
            int minArrivalTime = getMinAT();
            int nrServers = getNrQueue();
            int nrTasks = getNrTasks();
            final SimulationManager simulationManager = new SimulationManager(simTime, maxProcessingTime, minProcessingTime, maxArrivalTime, minArrivalTime, nrTasks, nrServers);

            Thread simulationManagerThread = new Thread(simulationManager);
            Thread updateUIThread =
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int currentTime = 0;
                            while (currentTime < getSimTime()) {
                                try {
                                    Thread.sleep(1000);
                                    currentTime++;
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                                view.setEvents(simulationManager.getEventsLog());
                            }
                        }
                    });
            updateUIThread.start();
            simulationManagerThread.start();
        }
    }
}
