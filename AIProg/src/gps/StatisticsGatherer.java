package gps;

public class StatisticsGatherer {

	private static String testSudoku1 = "094000130000000000000076002080010000032000000000200060000050400000008007006304008";
	private static String testSudoku2 = "000000000000942080160000029000000008906000001400250000004000000020008090050000700";
	private static String testSudoku3 = "390600000002000000010030870000005000030010060000400000064090030000000600000007052";
	private static String solvedSudoku = "248395716571628349936741582682539174359174628714862953863417295195286437427953861";
	private static String emptySoduko = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";

	private static String fileName = "Files/GCInput1";

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			minConflictsKQ();
		}
		// simulatedAnnealingSudoku();
		// minConflictsSudoku();

		// simulatedAnnealingGC();
		// minConflictsGC();

		// simulatedAnnealingKQ();
		// minConflictsKQ();
	}

	// Sudoku
	private static void simulatedAnnealingSudoku() {
		SAStateManager manager = new SASudokuStateManager(testSudoku1);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 200000, 1);
//		System.out.println(manager.toString());
		System.out.println("Evaluation: " + manager.objectiveValue() + ", Iterations: " + sa.iterations);
	}

	private static void minConflictsSudoku() {
		MCStateManager manager = new MCSudokuStateManager(emptySoduko);
		MinConflictsBest mc = new MinConflictsBest();

		manager = mc.mcSearch(manager);
//		System.out.println(manager.toString());
		System.out.println("Evaluation: " + manager.getConflicts() + ", Iterations: " + mc.iterations);
	}

	// Graph-color
	private static void simulatedAnnealingGC() {
		SAStateManager manager = new SAGCStateManager(fileName);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 20000, 1);
//		System.out.println(manager.toString());
		System.out.println("Evaluation: " + manager.objectiveValue() + ", Iterations: " + sa.iterations);
	}

	private static void minConflictsGC() {
		MCStateManager manager = new MCGCStateManager(fileName);
		MinConflictsBest mc = new MinConflictsBest();

		manager = mc.mcSearch(manager);
//		System.out.println(manager.toString());
		System.out.println("Evaluation: " + manager.getConflicts() + ", Iterations: " + mc.iterations);
	}

	// K-Queens
	private static void simulatedAnnealingKQ() {
		SAStateManager manager = new SAKQStateManager(1000);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 100, 1);
//		System.out.println(manager.toString());
		System.out.println("Evaluation: " + manager.objectiveValue() + ", Iterations: " + sa.iterations);
	}

	private static void minConflictsKQ() {
		MCStateManager manager = new MCKQStateManager(1000);
		MinConflictsBest mc = new MinConflictsBest();

		manager = mc.mcSearch(manager);
//		System.out.println(manager.toString());
		System.out.println("Evaluation: " + manager.getConflicts() + ", Iterations: " + mc.iterations);
	}
}
