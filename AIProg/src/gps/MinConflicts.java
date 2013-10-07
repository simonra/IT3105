package gps;

import java.util.ArrayList;

public class MinConflicts {
/**
 * 1 - Randomly chose any variable, V, that is involved in at least one conflict (i.e., violated constaint)
 * 2 - Assign V the new value a, where a is the value that produces the fewest number of conflicts
 * */
	
	public MinConflictsStateManager mcSearch(MinConflictsStateManager localStateManager){
		MinConflictsStateManager lsm = localStateManager;
		int positions;
		ArrayList<int[]> swaps = new ArrayList<>();
		int randomPos;
		int selectedSwapPos;
		while(true){
			if(lsm.getConflicts() == 0)
				return lsm;
			positions = lsm.getPositions();
			randomPos = (int) Math.floor( positions * Math.random());
			selectedSwapPos = Integer.MAX_VALUE;
			swaps.clear();
			for (int i = 0; i < positions; i++) {
				swaps.add(new int[]{i, lsm.getConflictValueForSwap(i, randomPos)} );
				if(swaps.get( swaps.size() - 1 )[1] < selectedSwapPos)
					selectedSwapPos = swaps.get( swaps.size() - 1 )[1]; 
			}
			lsm.swap(randomPos, selectedSwapPos);
		}
	}
}
