package gps;

import java.util.ArrayList;

public class SAGCStateManager implements SAStateManager {
	/** The name says it all */
	boolean[][] neighbourMatrix;

	/**
	 * The int color value of the node of the index value (ex: colorOfNode[nodes
	 * number/id] = nodes color value)
	 */
	int[] colorOfNodeN;

	/** Instansierer matrisene, tar inn filer, lager matrisestuff */
	public SAGCStateManager(String fileName) {
		neighbourMatrix = GCFileReader.getNeighborMatrix(GCFileReader
				.readFile(fileName));
		colorOfNodeN = new int[neighbourMatrix.length];

		for (int i = 0; i < neighbourMatrix.length; i++) {
			int randomNumber = (int) Math.floor(Math.random() * 4);
			colorOfNodeN[i] = randomNumber;
		}
	}

	public SAGCStateManager(boolean[][] neighbourMatrix, int[] colorOfNodeN,
			int colorChange) {

		this.neighbourMatrix = new boolean[neighbourMatrix.length][neighbourMatrix.length];
		this.colorOfNodeN = new int[neighbourMatrix.length];

		for (int i = 0; i < neighbourMatrix.length; i++) {
			for (int j = 0; j < neighbourMatrix.length; j++) {
				this.neighbourMatrix[i][j] = neighbourMatrix[i][j];
			}
			this.colorOfNodeN[i] = colorOfNodeN[i];
		}

		while (true) {
			int number = (int) Math.floor(Math.random() * 4);
			if (number != this.colorOfNodeN[colorChange]) {
				this.colorOfNodeN[colorChange] = number;
				break;
			}
		}
	}

	@Override
	public double objectiveValue() {
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
			returnList.add(new SAGCStateManager(neighbourMatrix, colorOfNodeN,
					i));
		}

		return returnList;
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
		s += objectiveValue();
		return s;
	}
}
