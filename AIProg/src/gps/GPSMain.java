package gps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GPSMain {
	private static String testSudoku1 = "094000130000000000000076002080010000032000000000200060000050400000008007006304008";
	private static String testSudoku2 = "000000000000942080160000029000000008906000001400250000004000000020008090050000700";
	private static String testSudoku3 = "390600000002000000010030870000005000030010060000400000064090030000000600000007052";
	private static String solvedSudoku = "248395716571628349936741582682539174359174628714862953863417295195286437427953861";
	private static String emptySoduko = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	private static String easySudoku = "219876435840001276765240001394157628127000954658429317482735160006914782971682543";
	private static String mediumSudoku = "210876030803001276760203801300107600027368000608020317082730000036010782071682003";
	private static String hardSudoku = "219876435840000006765243891000000628127300954658400310082735160036014002971600043";

	private static String fileName0 = "Files/GCInput0";
	private static String fileName1 = "Files/GCInput1";
	private static String fileName2 = "Files/GCInput2";
	private static String fileName3 = "Files/GCInput3";

	public static void main(String[] args) {
		String temp = "";
		while (true) {
			System.out
					.println("Please enter a valid string: sas, sagc, sakq, mcs, mcgc, mckq");
			temp = readInput();

			// Simluated annealing sudoku
			if (temp.equals("sas")) {
				while (true) {
					System.out
							.println("Please enter difficulty: easy, medium or hard");
					String temp2 = readInput();
					if (temp2.equals("easy")) {
						simulatedAnnealingSudoku(easySudoku);
						break;
					}

					if (temp2.equals("medium")) {
						simulatedAnnealingSudoku(mediumSudoku);
						break;
					}

					if (temp2.equals("hard")) {
						simulatedAnnealingSudoku(hardSudoku);
						break;
					}
				}
			}

			// Simulated annealing graph color
			if (temp.equals("sagc")) {
				while (true) {
					System.out.println("Please enter difficulty: 0, 1, 2 or 3");
					String temp2 = readInput();
					if (temp2.equals("0")) {
						simulatedAnnealingGC(fileName0);
						break;
					}

					if (temp2.equals("1")) {
						simulatedAnnealingGC(fileName1);
						break;
					}

					if (temp2.equals("2")) {
						simulatedAnnealingGC(fileName2);
						break;
					}

					if (temp2.equals("3")) {
						simulatedAnnealingGC(fileName3);
						break;
					}
				}
			}

			// Simulated annealing K-Queens
			if (temp.equals("sakq")) {
				while (true) {
					System.out.println("Please enter board size as an integer");
					String temp2 = readInput();
					simulatedAnnealingKQ(Integer.parseInt(temp2));
					break;
				}
			}

			// Minconflicts sudoku
			if (temp.equals("mcs")) {
				while (true) {
					System.out
							.println("Please enter difficulty: easy, medium or hard");
					String temp2 = readInput();
					if (temp2.equals("easy")) {
						minConflictsSudoku(easySudoku);
						break;
					}

					if (temp2.equals("medium")) {
						minConflictsSudoku(mediumSudoku);
						break;
					}

					if (temp2.equals("hard")) {
						minConflictsSudoku(hardSudoku);
						break;
					}
				}
			}

			// Minconflicts graph color
			if (temp.equals("mcgc")) {
				while (true) {
					System.out.println("Please enter difficulty: 0, 1, 2 or 3");
					String temp2 = readInput();
					if (temp2.equals("0")) {
						minConflictsGC(fileName0);
						break;
					}

					if (temp2.equals("1")) {
						minConflictsGC(fileName1);
						break;
					}

					if (temp2.equals("2")) {
						minConflictsGC(fileName2);
						break;
					}

					if (temp2.equals("3")) {
						minConflictsGC(fileName3);
						break;
					}
				}
			}

			// Simulated annealing K-Queens
			if (temp.equals("mckq")) {
				while (true) {
					System.out.println("Please enter board size as an integer");
					String temp2 = readInput();
					minConflictsKQ(Integer.parseInt(temp2));
					break;
				}
			}
		}

		// simulatedAnnealingSudoku();
		// minConflictsSudoku();

		// simulatedAnnealingGC();
		// minConflictsGC();

		// simulatedAnnealingKQ();
		// minConflictsKQ();
	}

	// Sudoku
	private static void simulatedAnnealingSudoku(String sudoku) {
		SAStateManager manager = new SASudokuStateManager(sudoku);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 200000, 1);
		System.out.println(manager.toString());
	}

	private static void minConflictsSudoku(String sudoku) {
		MCStateManager manager = new MCSudokuStateManager(sudoku);
		MinConflictsBest mc = new MinConflictsBest();

		manager = mc.mcSearch(manager);
		System.out.println(manager.toString());
	}

	// Graph-color
	private static void simulatedAnnealingGC(String fileName) {
		SAStateManager manager = new SAGCStateManager(fileName);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 20000, 1);
		System.out.println(manager.toString());

	}

	private static void minConflictsGC(String fileName) {
		MCStateManager manager = new MCGCStateManager(fileName);
		MinConflictsBest mc = new MinConflictsBest();

		manager = mc.mcSearch(manager);
		System.out.println(manager.toString());
	}

	// K-Queens
	private static void simulatedAnnealingKQ(int k) {
		SAStateManager manager = new SAKQStateManager(k);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 200000, 1);
		System.out.println(manager.toString());
	}

	private static void minConflictsKQ(int k) {
		MCStateManager manager = new MCKQStateManager(k);
		MinConflictsBest mc = new MinConflictsBest();

		manager = mc.mcSearch(manager);
		System.out.println(manager.toString());
	}

	public static String readInput() {
		String s = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			s = br.readLine();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return s;
	}
}
