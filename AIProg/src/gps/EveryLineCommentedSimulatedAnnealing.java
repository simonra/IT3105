package gps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EveryLineCommentedSimulatedAnnealing {
	/**Stores the neighbors of the current state manager*/
	ArrayList<SAStateManager> neighbors;
	/**Number of iterations used so far*/
	public int iterations = 0;
	
	/**
	 * Performs the simulated annealing algorithm
	 * 
	 * @param lsm
	 *            The local state manager used as a starting point for this
	 *            search
	 * @param initialTemperature
	 * @param deltaTemp
	 *            The decrease in temperature every iteartion
	 * @return A state manager that is the solution or approximation found
	 *         depending on how long it was allowed to run
	 */
	public SAStateManager saSearch(SAStateManager lsm,
			double initialTemperature, double deltaTemp) {
		SAStateManager localStateManager = lsm;	//Binds the starting-point local state manager
		double temperature = initialTemperature;	//Current temperature
		double deltaTemperature = deltaTemp;	//Ammount to deacrease temperature every iteration
		double targetOFuncValue = localStateManager
				.getTargetObjectiveFunctionValue();	//Target objective fuction value extracted from a state manager for the puzzle
		int maxIterations = 10000;	//Limit on maximal number of iterations to run. To guarantee answers for problem sizes relevat for this exercise it should be set to about 500 000
		while (true) {	//Does the search
			double objectiveFunctionValue = localStateManager.objectiveValue();	//Get the heuristic for this state
			if (objectiveFunctionValue >= targetOFuncValue
					|| maxIterations == 0){
//				System.out.println("Iterations: " + iterations);
				return localStateManager;	//return if a solution has been found or the maximal number of iterations has been reached. 
			}
			neighbors = localStateManager.getNeighbors();	//Generate the neighbors of this state
			SAStateManager bestNeighbor = Collections.max(neighbors,
					new saLocalStateComparator());	//Selects the neighbor with the largest optimal function value as the best neighbor
			double q = (bestNeighbor.objectiveValue() - objectiveFunctionValue)
					/ objectiveFunctionValue;	//Simulated annealing specific variable (see project handout for details)
			double p = Math.min(1, Math.pow(Math.E, (-q) / temperature));	//Simulated annealing specific variable (see project handout for details)
			double x = Math.random();	//Simulated annealing specific variable (see project handout for details)
			if (x > p)
				localStateManager = bestNeighbor;	//Exploit
			else
				localStateManager = neighbors.get((int) Math.floor(Math
						.random() * neighbors.size()));	//Explore
			temperature -= Math.max(deltaTemperature, 0);	//Update the temperature
			maxIterations--;	//Update cutoff iteration count
//			if (maxIterations % 1000 == 0) {
//				System.out.println("Iterations remaining: " + maxIterations
//						+ ", conflicts: " + objectiveFunctionValue);
//			}
			// System.out.println(localStateManager.toString());
			iterations++;	//Increment debug iteration count
		}
	}
}
/*
	// Comparator used to compare two simulated annealing state managers. Compares them based on their objective fuction value
class saLocalStateComparator implements Comparator<SAStateManager> {
	@Override
	public int compare(SAStateManager lsm1, SAStateManager lsm2) {
		if (lsm1.objectiveValue() > lsm2.objectiveValue())
			return 1;
		if (lsm1.objectiveValue() < lsm2.objectiveValue())
			return -1;
		return 0;
	}
}
*/