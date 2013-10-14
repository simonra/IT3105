package gps;

public class GPSMain {
	private static String fileName = "Files/GCInput3";

	public static void main(String[] args) {
		minConflictsGC();
		// simulatedAnnealingGC();
		// simulatedAnnealingKQ();
		// minConflictsKQ();
	}

	private static void minConflictsGC() {
		MCStateManager manager = new MCGCStateManager(fileName);
		MinConflicts mc = new MinConflicts();

		manager = mc.mcSearch(manager);
		System.out.println(manager.toString());
	}

	private static void minConflictsKQ() {
		MCStateManager manager = new MCKQStateManager(100);
		MinConflicts mc = new MinConflicts();

		manager = mc.mcSearch(manager);
		System.out.println(manager.toString());
	}

	private static void simulatedAnnealingGC() {
		SAStateManager manager = new SAGCStateManager(fileName);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 20000, 1);
		System.out.println(manager.toString());

	}

	private static void simulatedAnnealingKQ() {
		SAStateManager manager = new SAKQStateManager(8);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 200000, 1);
		System.out.println(manager.toString());
	}
}
