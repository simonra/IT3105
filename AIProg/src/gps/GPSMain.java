package gps;

public class GPSMain {
	public static void main(String[] args) {

		// simulatedAnnealingKQ();
		// minConflictsKQ();
	}

	private static void minConflictsKQ() {
		MCStateManager manager = new MCKQStateManager(100);
		MinConflicts mc = new MinConflicts();

		manager = mc.mcSearch(manager);
		System.out.println(manager.toString());
	}

	private static void simulatedAnnealingKQ() {
		SAStateManager manager = new SAKQStateManager(8);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 200000, 1);
		System.out.println(manager.toString());
	}
}
