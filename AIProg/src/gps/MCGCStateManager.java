package gps;

public class MCGCStateManager implements MCStateManager {

	boolean[][] neighbourMatrix;

	int[] colorOfNodeN;

	public MCGCStateManager(String fileName) {
		neighbourMatrix = GCFileReader.getNeighborMatrix(GCFileReader
				.readFile(fileName));
		colorOfNodeN = new int[neighbourMatrix.length];

		for (int i = 0; i < neighbourMatrix.length; i++) {
			int randomNumber = (int) Math.floor(Math.random() * 4);
			colorOfNodeN[i] = randomNumber;
		}
	}

	@Override
	public int getConflicts() {
		int conflicts = 0;
		for (int i = 0; i < neighbourMatrix.length - 1; i++) {
			for (int j = i + 1; j < neighbourMatrix.length; j++) {
				if (neighbourMatrix[i][j]) {
					if (colorOfNodeN[i] == colorOfNodeN[j]) {
						conflicts++;
					}
				}
			}
		}

		return conflicts;
	}

	@Override
	public int getPositions() {
		return colorOfNodeN.length;
	}

	@Override
	public int getConflictValueForSwap(int color, int nodeNumber) {
		int originalColor = colorOfNodeN[nodeNumber];
		colorOfNodeN[nodeNumber] = color;

		int conflicts = getConflicts();
		colorOfNodeN[nodeNumber] = originalColor;

		return conflicts;
	}

	@Override
	public void swap(int nodeNumber, int color) {
		colorOfNodeN[nodeNumber] = color;
	}

	public String toString() {
		String s = "";

		for (int i = 0; i < neighbourMatrix.length; i++) {
			for (int j = 0; j < neighbourMatrix.length; j++) {
				if (neighbourMatrix[i][j]) {
					s += "[" + colorOfNodeN[i] + " - " + colorOfNodeN[j] + "]";
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
