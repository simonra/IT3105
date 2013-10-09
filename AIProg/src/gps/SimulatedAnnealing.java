package gps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SimulatedAnnealing {
	ArrayList<SAStateManager> neighbors;

	public SAStateManager saSearch(SAStateManager lsm,
			double initialTemperature, double deltaTemp) {
		SAStateManager localStateManager = lsm;
		double temperature = initialTemperature;
		double deltaTemperature = deltaTemp;
		double targetOFuncValue = localStateManager
				.getTargetObjectiveFunctionValue();
		while (true) {
			double objectiveFunctionValue = localStateManager.objectiveValue();
			if (objectiveFunctionValue >= targetOFuncValue)
				return localStateManager;
			neighbors = localStateManager.getNeighbors();
			SAStateManager bestNeighbor = Collections.max(neighbors,
					new saLocalStateComparator());
			double q = (bestNeighbor.objectiveValue() - objectiveFunctionValue)
					/ objectiveFunctionValue;
			double p = Math.min(1, Math.pow(Math.E, (-q) / temperature));
			double x = Math.random();
			if (x > p)
				localStateManager = bestNeighbor;
			else
				localStateManager = neighbors.get((int) Math.floor(Math
						.random() * neighbors.size()));
			temperature -= deltaTemperature;
		}
	}
}

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
