package gps;

import java.util.ArrayList;

public class SAKQStateManager implements SAStateManager {
	private int[] queens;
	private int k;

	public SAKQStateManager(int k) {
		this.k = k;
		queens = new int[k];
		for (int i = 0; i < k; i++) {
			queens[i] = (int) Math.floor(Math.random() * k);
		}
	}

	public SAKQStateManager(int k, int[] queens) {
		this.k = k;
		this.queens = new int[k];
		for (int j = 0; j < k; j++) {
			this.queens[j] = queens[j];
		}

	}

	@Override
	public double objectiveValue() {
		int conflicts = 0;

		for (int i = 0; i < k; i++) {
			for (int j = i + 1; j < k; j++) {
				if (queens[i] == queens[j]
						|| queens[i] == queens[j] + Math.abs(j - i)
						|| queens[i] == queens[j] - Math.abs(j - i))
					conflicts++;
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
		int[] tempQueens = new int[k];

		int randomQueen = (int) Math.floor(Math.random() * k);

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				tempQueens[j] = queens[j];
			}

			tempQueens[randomQueen] = (tempQueens[randomQueen] + i) % k;
			returnList.add(new SAKQStateManager(k, tempQueens));
		}

		return returnList;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if (queens[i] == j) {
					string += "X ";
				} else {
					string += "O ";
				}
			}
			string += "\n";
		}
		string += "Conflicts: " + (int) Math.abs(objectiveValue());
		return string;
	}
}