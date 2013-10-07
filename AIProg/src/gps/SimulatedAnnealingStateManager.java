package gps;

import java.util.ArrayList;

public interface SimulatedAnnealingStateManager {

	public double objectiveValue();

	public double getTargetObjectiveFunctionValue();

	public ArrayList<SimulatedAnnealingStateManager> getNeighbors();
	
	@Override
	public String toString();
}
