package gps;

public class GPSMain {
	// http://www2.warwick.ac.uk/fac/sci/moac/people/students/peter_cock/python/sudoku/
	private static String testSudoku1 = "094000130000000000000076002080010000032000000000200060000050400000008007006304008";
	private static String testSudoku2 = "000000000000942080160000029000000008906000001400250000004000000020008090050000700";
	private static String testSudoku3 = "390600000002000000010030870000005000030010060000400000064090030000000600000007052";
	private static String solvedSudoku = "248395716571628349936741582682539174359174628714862953863417295195286437427953861";
	private static String solvedSudoku2 = "248395700071628349930001580682530004359074000014862953863417295195286437427953861";
	private static String emptySoduko = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	private static String fileName = "Files/GCInput3";

	public static void main(String[] args) {
		// simulatedAnnealingS();
		minConflictsSudoku();
		// minConflictsGC();
		// simulatedAnnealingGC();
		// simulatedAnnealingKQ();
		// minConflictsKQ();
	}

	private static void minConflictsSudoku() {
		MCStateManager manager = new MCSudokuStateManager(emptySoduko);
		MinConflicts mc = new MinConflicts();

		manager = mc.mcSearch(manager);
		System.out.println(manager.toString());
	}

	private static void simulatedAnnealingS() {
		SAStateManager manager = new SASudokuStateManager(testSudoku1);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 400000, 1);
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
