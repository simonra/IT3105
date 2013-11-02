package gps;

public class MCGCStateManager implements MCStateManager {

	boolean[][] neighbourMatrix;

	/** The index is the nodes ID, with the color being the value */
	int[] colors;

	public MCGCStateManager(String fileName) {
		// Reads in the GCproblem and puts it in a neighbourMatrix
		neighbourMatrix = GCFileReader.getNeighborMatrix(GCFileReader
				.readFile(fileName));

		colors = new int[neighbourMatrix.length];

		// Assign a random color to every node
		for (int i = 0; i < neighbourMatrix.length; i++) {
			int randomNumber = (int) Math.floor(Math.random() * 4);
			colors[i] = randomNumber;
		}
	}

	@Override
	public int getConflicts() {
		// Iterates through the neighbourMatrix, counting how often neighbours
		// have the same color
		int conflicts = 0;
		for (int i = 0; i < neighbourMatrix.length - 1; i++) {
			for (int j = i + 1; j < neighbourMatrix.length; j++) {
				if (neighbourMatrix[i][j]) {
					if (colors[i] == colors[j]) {
						conflicts++;
					}
				}
			}
		}

		return conflicts;
	}

	@Override
	public int getPositions() {
		return colors.length;
	}

	@Override
	public int getConflictValueForSwap(int color, int nodeNumber) {
		int originalColor = colors[nodeNumber];
		colors[nodeNumber] = color;

		int conflicts = getConflicts();
		colors[nodeNumber] = originalColor;

		return conflicts;
	}

	@Override
	public void swap(int nodeNumber, int color) {
		colors[nodeNumber] = color;
	}
	
	@Override
	public String toString() {
		// Prints the neighbourMatrix
		String s = "";

		for (int i = 0; i < neighbourMatrix.length; i++) {
			for (int j = 0; j < neighbourMatrix.length; j++) {
				if (neighbourMatrix[i][j]) {
					s += "[" + colors[i] + " - " + colors[j] + "]";
				} else {
					s += "[     ]";
				}
			}
			s += "\n";
		}
		s += getConflicts();
		return s;
	}
}
