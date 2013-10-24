package gps;

import java.util.ArrayList;

public class EveryLineCommentedMinConflictsBest {
	public int iterations = 0;	//Couts how many iterations there have been, used to cut of the algorithm if a limit is reached
	/**
	 * 1 - Randomly chose any variable, V, that is involved in at least one
	 * conflict (i.e., violated constaint) 2 - Assign V the new value a, where a
	 * is the value that produces the fewest number of conflicts
	 * */

	public MCStateManager mcSearch(MCStateManager localStateManager) {
		MCStateManager lsm = localStateManager;	//The state-manager for the puzzle, acts like a node
		int positions;	//Stores the number of swappable items in the puzzle that min-conflicts is allowed to work on
		ArrayList<int[]> swaps = new ArrayList<>();	//The list of swaps being considered this iteration
		int randomPos;	//The random position the algorithm considers new elemts for in this iteration
		int selectedSwapPos;	//The best found swap position in this iteration that will be used at the end
		int selectedSwapConflicts;	//The ammount of conflicts for the swap above, used to determine wheter a new position we consider in this iteration is better than the current best
		int testCounter = 0;	//Used for debugging and printing progress for those puzzles where you wonder whether it has died or is actually still doing something. Currently inactive. 
		int amountOfSameNumberOfConflicts = 0;	//Used to make sure the algorithm doesn't get stuck in a local optiumum
		int numberOfConflictsLastRound = -1;	//Used to evaluate what to do with amountOfSameNumberOfConflicts
		int conflicts = Integer.MAX_VALUE;	//Sotres the number of conflicts in the current state 
		while (true) {
			conflicts = lsm.getConflicts();	//Gets the conflicts from the state manager
			if (conflicts == 0 || iterations > 10000)	//Checks if the puzzle is solved or the maximal allowed number of iterations is reached 
				return lsm;
			positions = lsm.getPositions();	//Gets the number of items the algorithm is allowed to manipulate this iteration
			randomPos = (int) Math.floor(positions * Math.random());	//Chooses a random position
			selectedSwapPos = -1;	//Initializes the chosen swap for this iteration to be unchosen
			selectedSwapConflicts = Integer.MAX_VALUE;	//Makes sure all swaps are good swaps in the beginning of the iteration
			swaps.clear();	//Clears the considered swaps from the previous iteration
			for (int i = 0; i < positions; i++) {	//Iterates through all posible elements and evaluates what's the best swap
				swaps.add(new int[] { i,
						lsm.getConflictValueForSwap(i, randomPos) });	//Adds the conflict value for the currently considered element and the chosen random position, and its index to swaps
				if (swaps.get(swaps.size() - 1)[1] < selectedSwapConflicts) {	//If the newly added swap is the best swap seen so far, update the apropriate values
					selectedSwapPos = swaps.get(swaps.size() - 1)[0];
					selectedSwapConflicts = swaps.get(swaps.size() - 1)[1];
					if (selectedSwapConflicts == 0)	//Breaks if it has found a solution
						break;
				}
			}
			if (numberOfConflictsLastRound == conflicts)	//Checks for local optima
				amountOfSameNumberOfConflicts++;
			else
				amountOfSameNumberOfConflicts = 0; //If moved since last itaration reset local optimum stuck checking
			if (amountOfSameNumberOfConflicts >= 100) {	//If the algorithm can be considered stuck, forces a new direction
				selectedSwapPos = (int) Math.floor(positions * Math.random());
				amountOfSameNumberOfConflicts = 0;
			}
			lsm.swap(randomPos, selectedSwapPos);	//Perofrms the swap opteration, updating the state manager
			testCounter++;	//Increments the debugging counter
			// if (testCounter % 2 == 0)
			// System.out.println(selectedSwapConflicts);
			numberOfConflictsLastRound = conflicts;	//Saved the number of conflicst from last round to be used for local optimum checking
			iterations++;	//Increments the count of iterations used
		}
	}
}
