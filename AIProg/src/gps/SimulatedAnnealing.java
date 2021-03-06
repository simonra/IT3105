package gps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SimulatedAnnealing {
	/** Stores the neighbors of the current state manager */
	ArrayList<SAStateManager> neighbors;
	/** Number of iterations used so far */
	public int iterations = 0;

	/**
	 * Performs the simulated annealing algorithm
	 * 
	 * @param lsm
	 *            The local state manager used as a starting point for this
	 *            search
	 * @param initialTemperature
	 * @param deltaTemp
	 *            The decrease in temperature every itearation
	 * @return A state manager that is the solution or approximation foundn
	 *         depending on how long it was allowed to run
	 */
	public SAStateManager saSearch(SAStateManager lsm,
			double initialTemperature, double deltaTemp) {
		SAStateManager localStateManager = lsm;
		double temperature = initialTemperature;
		double deltaTemperature = deltaTemp;
		double targetOFuncValue = localStateManager
				.getTargetObjectiveFunctionValue();
		int maxIterations = 500000;
		while (true) {
			double objectiveFunctionValue = localStateManager.objectiveValue();
			if (objectiveFunctionValue >= targetOFuncValue
					|| maxIterations == 0) {
				// System.out.println("Iterations: " + iterations);
				return localStateManager;
			}
			neighbors = localStateManager.getNeighbors();
			SAStateManager bestNeighbor = Collections.max(neighbors,
					new saLocalStateComparator());
			double q = (bestNeighbor.objectiveValue() - objectiveFunctionValue)
					/ objectiveFunctionValue;
			double p = Math.min(1, Math.pow(Math.E, (-q) / temperature));
			double x = Math.random();
			if (x > p)
				localStateManager = bestNeighbor;	//Exploit
			else
				localStateManager = neighbors.get((int) Math.floor(Math
						.random() * neighbors.size()));	//Explore
			temperature -= Math.max(deltaTemperature, 0);
			maxIterations--;
			 if (maxIterations % 1000 == 0) {
			 System.out.println("Iterations remaining: " + maxIterations
			 + ", conflicts: " + objectiveFunctionValue);
			 }
			// System.out.println(localStateManager.toString());
			iterations++;
		}
	}
}

/**
 * Comparator used to compare two simulated annealing state managers. Compares
 * them based on their objective fuction value
 */
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
