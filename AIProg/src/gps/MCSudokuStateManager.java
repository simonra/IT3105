package gps;

public class MCSudokuStateManager implements MCStateManager {
	/** The original sudoku problem */
	public int[] originalPuzzle;

	/** Numbers that needs to be assigned to solve the puzzle */
	public int[] changeableNumbers;

	public MCSudokuStateManager(String newPuzzle) {
		originalPuzzle = new int[newPuzzle.length()];

		int changableNumbersCounter = 0;

		// The index + 1 represents each number (1-9) that can occur in the
		// puzzle. The value represents the amount of those numbers we need to
		// complete the puzzle.
		int[] totalNumbers = { 9, 9, 9, 9, 9, 9, 9, 9, 9 };

		// Iterates over the original puzzle, parsing the string read from the
		// file into originalPuzzle, in addition to counting how many zeroes
		// there are. It also subtracts the numbers read from totalNumbers, for
		// use later.
		for (int i = 0; i < newPuzzle.length(); i++) {
			int temp = Integer.parseInt("" + newPuzzle.charAt(i));
			originalPuzzle[i] = temp;

			if (temp == 0)
				changableNumbersCounter++;
			else
				totalNumbers[temp - 1] -= 1;
		}

		changeableNumbers = new int[changableNumbersCounter];

		// Fills changeableNumbers with the numbers from totalNumbers
		int changeableNumbersIndex = 0;
		for (int i = 0; i < totalNumbers.length; i++) {
			int remainingTotalNumbers = totalNumbers[i];
			while (remainingTotalNumbers > 0) {
				changeableNumbers[changeableNumbersIndex] = i + 1;
				remainingTotalNumbers--;
				changeableNumbersIndex++;
			}
		}
	}

	@Override
	public int getConflicts() {
		int conflicts = 0;

		// Used to fill the original puzzle with the remaining numbers in
		// changeableNumbers
		int[] tempPuzzle = new int[originalPuzzle.length];
		int currentCounter = 0;
		for (int i = 0; i < originalPuzzle.length; i++) {
			if (originalPuzzle[i] == 0) {
				tempPuzzle[i] = changeableNumbers[currentCounter];
				currentCounter++;
			} else {
				tempPuzzle[i] = originalPuzzle[i];
			}
		}

		// For each row, column and box; Checks how many numbers are in
		// conflicts.
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
		return conflicts;
	}

	@Override
	public int getPositions() {
		return changeableNumbers.length;
	}

	@Override
	public int getConflictValueForSwap(int pos0, int pos1) {
		int conflictsToReturn = 0;
		int originalValuePos0 = changeableNumbers[pos0];
		int originalValuePos1 = changeableNumbers[pos1];
		changeableNumbers[pos0] = originalValuePos1;
		changeableNumbers[pos1] = originalValuePos0;
		conflictsToReturn = getConflicts();
		changeableNumbers[pos0] = originalValuePos0;
		changeableNumbers[pos1] = originalValuePos1;
		return conflictsToReturn;
	}

	@Override
	public void swap(int pos0, int pos1) {
		int tempPiece = changeableNumbers[pos0];
		changeableNumbers[pos0] = changeableNumbers[pos1];
		changeableNumbers[pos1] = tempPiece;
	}

	@Override
	public String toString() {
		int[] tempPuzzle = new int[originalPuzzle.length];
		int currentCounter = 0;
		for (int i = 0; i < originalPuzzle.length; i++) {
			if (originalPuzzle[i] == 0) {
				tempPuzzle[i] = changeableNumbers[currentCounter];
				currentCounter++;
			} else {
				tempPuzzle[i] = originalPuzzle[i];
			}
		}

		String s = "";
		for (int i = 0; i < tempPuzzle.length; i++) {
			if (i % 9 == 0)
				s += "\n";
			s += " " + tempPuzzle[i] + " ";
		}
		return s;
	}
}
