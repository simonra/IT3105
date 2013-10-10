package gps;

public class MCKQStateManager implements MCStateManager {

	int k;
	private int[] queens;

	public MCKQStateManager(int k) {
		this.k = k;
		queens = new int[k];
		for (int i = 0; i < k; i++) {
			queens[i] = i;
		}
	}

	@Override
	public int getConflicts() {
		int conflicts = 0;

		for (int i = 0; i < k; i++) {
			for (int j = i + 1; j < k; j++) {
				if (queens[i] == queens[j]
						|| queens[i] == queens[j] + Math.abs(j - i)
						|| queens[i] == queens[j] - Math.abs(j - i))
					conflicts++;
			}
		}
		return conflicts;
	}

	@Override
	public int getPositions() {
		return k;
	}

	@Override
	public int getConflictValueForSwap(int pos0, int pos1) {
		// TODO Auto-generated method stub
		int conflictsToReturn = 0;
		int originalValuePos0 = queens[pos0];
		int originalValuePos1 = queens[pos1];
		queens[pos0] = originalValuePos1;
		queens[pos1] = originalValuePos0;
		conflictsToReturn = getConflicts();
		queens[pos0] = originalValuePos0;
		queens[pos1] = originalValuePos1;
		return conflictsToReturn;
	}

	@Override
	public void swap(int pos0, int pos1) {
		int originalValuePos0 = queens[pos0];
		queens[pos0] = queens[pos1];
		queens[pos1] = originalValuePos0;
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
		return string;
	}
}
