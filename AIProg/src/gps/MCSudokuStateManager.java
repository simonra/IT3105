package gps;

public class MCSudokuStateManager implements MCStateManager {

	public int[] originalPuzzle;
	public int[] currentPieces;

	public MCSudokuStateManager(String newPuzzle) {
		originalPuzzle = new int[newPuzzle.length()];
		int currentCounter = 0;
		int[] totalNumbers = { 9, 9, 9, 9, 9, 9, 9, 9, 9 };

		for (int i = 0; i < newPuzzle.length(); i++) {
			int temp = (int) Integer.parseInt("" + newPuzzle.charAt(i));
			originalPuzzle[i] = temp;

			if (temp == 0)
				currentCounter++;
			else
				totalNumbers[temp - 1] -= 1;
		}

		currentPieces = new int[currentCounter];

		int temp2 = 0;
		for (int i = 0; i < totalNumbers.length; i++) {
			int temp = totalNumbers[i];
			while (temp > 0) {
				currentPieces[temp2] = i + 1;
				temp--;
				temp2++;
			}

		}
		// System.out.println(toString());
	}

	@Override
	public int getConflicts() {
		int conflicts = 0;

		int[] tempPuzzle = new int[originalPuzzle.length];
		int currentCounter = 0;
		for (int i = 0; i < originalPuzzle.length; i++) {
			if (originalPuzzle[i] == 0) {
				tempPuzzle[i] = currentPieces[currentCounter];
				currentCounter++;
			} else {
				tempPuzzle[i] = originalPuzzle[i];
			}
		}

		int[] totalNumbers = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < 9; i++) {
			int[] rowList = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int[] columnList = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int[] boxList = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

			for (int j = 0; j < 9; j++) {
				int row = (i * 9) + j;
				int numberInTempRow = tempPuzzle[row];
				rowList[numberInTempRow - 1] += 1;
				columnList[tempPuzzle[i + (j * 9)] - 1] += 1;
				boxList[tempPuzzle[(27 * (i / 3)) + ((i % 3) * 3) + j
						+ ((j / 3) * 6)] - 1] += 1;
				totalNumbers[tempPuzzle[i * 9 + j] - 1] += 1;
			}

			for (int j = 0; j < 9; j++) {
				if (rowList[j] > 1) {
					conflicts += Math.pow(rowList[j] - 1, 2);
				}

				if (columnList[j] > 1) {
					conflicts += Math.pow(columnList[j] - 1, 2);
				}

				if (boxList[j] > 1) {
					conflicts += Math.pow(boxList[j] - 1, 2);
				}
			}
		}
		for (int i = 0; i < totalNumbers.length; i++) {
			conflicts += Math.abs(totalNumbers[i] - 9);
		}

		// TODO Auto-generated method stub
		// System.out.println(conflicts);
		return conflicts;
	}

	@Override
	public int getPositions() {
		return currentPieces.length;
	}

	@Override
	public int getConflictValueForSwap(int pos0, int pos1) {
		int conflictsToReturn = 0;
		int originalValuePos0 = currentPieces[pos0];
		int originalValuePos1 = currentPieces[pos1];
		currentPieces[pos0] = originalValuePos1;
		currentPieces[pos1] = originalValuePos0;
		conflictsToReturn = getConflicts();
		currentPieces[pos0] = originalValuePos0;
		currentPieces[pos1] = originalValuePos1;
		return conflictsToReturn;
	}

	@Override
	public void swap(int pos0, int pos1) {
		int tempPiece = currentPieces[pos0];
		currentPieces[pos0] = currentPieces[pos1];
		currentPieces[pos1] = tempPiece;
		// System.out.println(toString());
	}

	@Override
	public String toString() {
		int[] tempPuzzle = new int[originalPuzzle.length];
		int currentCounter = 0;
		for (int i = 0; i < originalPuzzle.length; i++) {
			if (originalPuzzle[i] == 0) {
				tempPuzzle[i] = currentPieces[currentCounter];
				currentCounter++;
			} else {
				tempPuzzle[i] = originalPuzzle[i];
			}
		}

		String s = "";
		for (int i = 0; i < tempPuzzle.length; i++) {
			// if(i)
			// s +="|";
			if (i % 9 == 0)
				s += "\n";
			s += " " + tempPuzzle[i] + " ";
		}
		return s;
	}
}
