package gps;

public class GPSMain {
	// http://www2.warwick.ac.uk/fac/sci/moac/people/students/peter_cock/python/sudoku/
	private static String fileName = "Files/GCInput3";

	public static void main(String[] args) {
		simulatedAnnealingS();
		// minConflictsGC();
		// simulatedAnnealingGC();
		// simulatedAnnealingKQ();
		// minConflictsKQ();
	}

	private static void simulatedAnnealingS() {
		SAStateManager manager = new SASudokuStateManager(
				"094000130000000000000076002080010000032000000000200060000050400000008007006304008");
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 200, 1);
		System.out.println(manager.toString());
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
