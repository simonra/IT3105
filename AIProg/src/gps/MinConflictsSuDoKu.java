package gps;

import java.util.ArrayList;

public class MinConflictsSuDoKu {
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
		int ammountOfSameNumberOfConflicts = 0;
		int numberOfConflictsLastRound = -1;
		int conflicts = Integer.MAX_VALUE;
		while (true) {
			conflicts = lsm.getConflicts();
			if (conflicts == 0)
				return lsm;
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
			if(numberOfConflictsLastRound == conflicts)
				ammountOfSameNumberOfConflicts++;
			else
				ammountOfSameNumberOfConflicts = 0;
			if(ammountOfSameNumberOfConflicts >= 100){
				selectedSwapPos = (int) Math.floor(positions * Math.random());
				ammountOfSameNumberOfConflicts = 0;
			}
			lsm.swap(randomPos, selectedSwapPos);
			testCounter++;
			if (testCounter % 50 == 0)
				System.out.println(selectedSwapConflicts);
			numberOfConflictsLastRound = conflicts;
		}
	}
}
