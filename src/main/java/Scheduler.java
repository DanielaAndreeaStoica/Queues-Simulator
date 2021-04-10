
import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy = new ConcreteStrategyTime();

    public Scheduler(int maxNoServers){
        servers = new ArrayList<>();
        for(int i=0;i<maxNoServers;i++){
            Server s = new Server(i+1);
            servers.add(s);
            Thread t = new Thread(s);
            t.start();
        }
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }

    public List<Server> getServers(){
        return servers;
    }

    @Override
    public String toString() {
        String s="";
        for(Server server:servers)
            s=s+server.toString();
        return s;
    }
}
