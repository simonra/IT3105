package gps;

import java.util.ArrayList;

public class MinConflicts {
	public int iterations = 0;
	/**
	 * 1 - Randomly chose any variable, V, that is involved in at least one
	 * conflict (i.e., violated constaint) 2 - Assign V the new value a, where a
	 * is the value that produces the fewest number of conflicts
	 * */
	public MCStateManager mcSearch(MCStateManager localStateManager) {
		MCStateManager lsm = localStateManager;
		int positions;
		ArrayList<int[]> swaps = new ArrayList<>();
		int randomPos;
		int selectedSwapPos;
		int selectedSwapConflicts;
		int testCounter = 0;
		while (true) {
			if (lsm.getConflicts() == 0 || iterations > 500000){
//				System.out.println("Iterations: " + iterations);
				return lsm;
			}
			positions = lsm.getPositions();
			randomPos = (int) Math.floor(positions * Math.random());
			selectedSwapPos = -1;
			selectedSwapConflicts = Integer.MAX_VALUE;
			swaps.clear();
			for (int i = 0; i < positions; i++) {
				swaps.add(new int[] { i,
						lsm.getConflictValueForSwap(i, randomPos) });
				if (swaps.get(swaps.size() - 1)[1] < selectedSwapConflicts) {
					selectedSwapPos = swaps.get(swaps.size() - 1)[0];
					selectedSwapConflicts = swaps.get(swaps.size() - 1)[1];
					if (selectedSwapConflicts == 0)
						break;
				}
			}
			lsm.swap(randomPos, selectedSwapPos);
//			testCounter++;
//			if (testCounter % 2 == 0)
//				System.out.println(selectedSwapConflicts);
			// System.out.println(lsm.toString());
			iterations++;
		}
	}
}
