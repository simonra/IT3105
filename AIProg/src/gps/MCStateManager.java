package gps;

public interface MCStateManager {

	/** Calculate and return the number of conflicts in the current state */
	int getConflicts();

	/** The number of positions this mc-puzzle operates with */
	int getPositions();

	/**
	 * The conflict vale for the state if the element on pos0 and pos1 are
	 * swapped. Should not alter the state/revert the state to what it
	 * originally was if it needs to be altered in the process.
	 */
	int getConflictValueForSwap(int pos0, int pos1);

	/** Swaps the elements in pos0 and pos1. */
	void swap(int pos0, int pos1);
	
	/**Should be customized to generate the string that best represents the implemented mc-state*/
	String toString();
}
