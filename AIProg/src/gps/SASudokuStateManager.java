package gps;

import java.util.ArrayList;

public class SASudokuStateManager implements SAStateManager {

	public int[] originalPuzzle;
	public int[] currentPieces;

	public SASudokuStateManager(String newPuzzle) {
		originalPuzzle = new int[newPuzzle.length() - 1];
		int currentCounter = 0;
		for (int i = 0; i < newPuzzle.length() - 1; i++) {
			int temp = (int) Integer.parseInt("" + newPuzzle.charAt(i));
			originalPuzzle[i] = temp;

			if (temp == 0)
				currentCounter++;
		}

		currentPieces = new int[currentCounter];

		for (int i = 0; i < currentPieces.length; i++) {
			currentPieces[i] = ((int) Math.floor(Math.random() * 9)) + 1;
		}
	}

	public SASudokuStateManager(int[] originalPuzzle, int[] currentPuzzle) {
		for (int i = 0; i < originalPuzzle.length; i++) {
			this.originalPuzzle[i] = originalPuzzle[i];
		}

		for (int i = 0; i < currentPuzzle.length; i++) {
			this.currentPieces[i] = currentPuzzle[i];
		}
	}

	@Override
	public double objectiveValue() {
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

		for (int i = 0; i < 9; i++) {
			int[] rowList = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int[] columnList = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int[] boxList = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

			for (int j = 0; j < 9; j++) {
				rowList[tempPuzzle[(i * 9) + j]] += 1;
				columnList[tempPuzzle[i + (j * 9)]] += 1;
				boxList[tempPuzzle[(27 * (i / 3)) + ((i % 3) * 3) + j
						+ ((j / 3) * 6)]] += 1;
			}

			for (int j = 0; j < 9; j++) {
				if (rowList[j] > 1) {
					conflicts += rowList[j];
				}

				if (columnList[j] > 1) {
					conflicts += columnList[j];
				}

				if (boxList[j] > 1) {
					conflicts += boxList[j];
				}
			}
		}

		// TODO Auto-generated method stub
		return conflicts;
	}

	@Override
	public double getTargetObjectiveFunctionValue() {
		return 0;
	}

	@Override
	public ArrayList<SAStateManager> getNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}

}
