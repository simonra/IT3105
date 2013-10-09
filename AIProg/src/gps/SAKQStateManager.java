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
		this.queens = queens;

	}

	@Override
	public double objectiveValue() {
		int conflicts = 0;

		for (int i = 0; i < k; i++) {
			for (int j = i; j < k; j++) {
				if (queens[i] == queens[j])
					conflicts++;

				if (queens[i] == queens[j] + Math.abs(j - i))
					conflicts++;

				if (queens[i] == queens[j] - Math.abs(j - i))
					conflicts++;
			}
		}
		return conflicts;
	}

	@Override
	public double getTargetObjectiveFunctionValue() {
		return 0;
	}

	@Override
	public ArrayList<SAStateManager> getNeighbors() {
		ArrayList<SAStateManager> returnList = new ArrayList<>();
		int[] tempQueens = new int[k];

		int iterable = k;

		if (k > 100)
			iterable = 100;

		for (int i = 0; i < iterable; i++) {
			for (int j = 0; j < k; j++) {
				tempQueens[j] = queens[j];
			}

			tempQueens[(int) Math.floor(Math.random() * k)] = (int) Math
					.floor(Math.random() * k);

			returnList.add(new SAKQStateManager(k, tempQueens));
		}
		return returnList;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if (queens[i] == j) {
					string += "Q";
				} else {
					string += " ";
				}
			}
			string += "\n";
		}
		return string;
	}
}