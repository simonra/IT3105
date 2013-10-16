package gps;

import java.util.ArrayList;

public class SAGCStateManager implements SAStateManager {

	boolean[][] neighbourMatrix;

	/** The index is the nodes ID, with the color being the value */
	int[] colors;

	public SAGCStateManager(String fileName) {
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

	/**
	 * Takes all the necessary parameters from a previous state manager to
	 * duplicate it with a single node having a different color
	 */
	public SAGCStateManager(boolean[][] neighbourMatrix, int[] colors,
			int colorChange) {

		this.neighbourMatrix = new boolean[neighbourMatrix.length][neighbourMatrix.length];
		this.colors = new int[neighbourMatrix.length];

		for (int i = 0; i < neighbourMatrix.length; i++) {
			for (int j = 0; j < neighbourMatrix.length; j++) {
				this.neighbourMatrix[i][j] = neighbourMatrix[i][j];
			}
			this.colors[i] = colors[i];
		}

		// Randomly changes the color of a node
		while (true) {
			int number = (int) Math.floor(Math.random() * 4);
			if (number != this.colors[colorChange]) {
				this.colors[colorChange] = number;
				break;
			}
		}
	}

	@Override
	public double objectiveValue() {
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
		return 0 - conflicts;
	}

	@Override
	public double getTargetObjectiveFunctionValue() {
		return 0;
	}

	@Override
	public ArrayList<SAStateManager> getNeighbors() {
		ArrayList<SAStateManager> returnList = new ArrayList<>();

		for (int i = 0; i < neighbourMatrix.length; i++) {
			returnList.add(new SAGCStateManager(neighbourMatrix, colors, i));
		}

		return returnList;
	}

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
		s += objectiveValue();
		return s;
	}
}
