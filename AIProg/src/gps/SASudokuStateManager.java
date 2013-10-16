package gps;

import java.util.ArrayList;

public class SASudokuStateManager implements SAStateManager {
	/** The original sudoku problem */
	public int[] originalPuzzle;

	/** Numbers that needs to be assigned to solve the puzzle */
	public int[] changeableNumbers;

	public SASudokuStateManager(String newPuzzle) {
		originalPuzzle = new int[newPuzzle.length()];
		int changeableNumbersCounter = 0;

		// Iterates over the original puzzle, parsing the string read from the
		// file into originalPuzzle, in addition to counting how many zeroes
		// there are.
		for (int i = 0; i < newPuzzle.length(); i++) {
			int temp = (int) Integer.parseInt("" + newPuzzle.charAt(i));
			originalPuzzle[i] = temp;

			if (temp == 0)
				changeableNumbersCounter++;
		}

		// Fills changeableNumbers with the numbers from totalNumbers
		changeableNumbers = new int[changeableNumbersCounter];
		for (int i = 0; i < changeableNumbers.length; i++) {
			changeableNumbers[i] = ((int) Math.floor(Math.random() * 9)) + 1;
		}
	}

	public SASudokuStateManager(int[] originalPuzzle, int[] currentPieces) {
		this.originalPuzzle = new int[originalPuzzle.length];
		this.changeableNumbers = new int[currentPieces.length];

		for (int i = 0; i < originalPuzzle.length; i++) {
			this.originalPuzzle[i] = originalPuzzle[i];
		}

		for (int i = 0; i < currentPieces.length; i++) {
			this.changeableNumbers[i] = currentPieces[i];
		}
	}

	@Override
	public double objectiveValue() {
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

		return 0 - conflicts;
	}

	@Override
	public double getTargetObjectiveFunctionValue() {
		return 0;
	}

	@Override
	public ArrayList<SAStateManager> getNeighbors() {
		ArrayList<SAStateManager> returnList = new ArrayList<>();
		int randomNumber = (int) Math.floor(Math.random() * 9) + 1;
		for (int i = 0; i < changeableNumbers.length; i++) {
			int tempPiece = changeableNumbers[i];
			changeableNumbers[i] = randomNumber;
			returnList.add(new SASudokuStateManager(originalPuzzle,
					changeableNumbers));
			changeableNumbers[i] = tempPiece;
		}
		return returnList;
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
