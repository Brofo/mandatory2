import simulation.EventSim;
import statistics.Statistics;

public class Main {

    public static void main(String[] args) {
	    EventSim simulator = new EventSim();
	    simulator.runSim();

	    Statistics stats = simulator.getSimStats();
	    stats.printStats();
    }
}
