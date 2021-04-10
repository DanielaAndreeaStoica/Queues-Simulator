
import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    public void addTask(List<Server> servers, Task t) {
        int min = servers.get(0).getWaitingPeriod().get();
        Server serverAux = servers.get(0);

        for (Server s : servers) {
            if (s.getWaitingPeriod().get() < min) {
                min = s.getWaitingPeriod().get();
                serverAux = s;
            }
        }
        serverAux.addTask(t);
        t.setWaitingPeriodOnChosenServer(min);
    }
}
