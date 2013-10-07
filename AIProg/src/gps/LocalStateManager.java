package gps;

import java.util.ArrayList;

public interface LocalStateManager {

	public double objectiveValue();

	public double getTargetObjectiveFunctionValue();

	public ArrayList<LocalStateManager> getNeighbors();
	
	@Override
	public String toString();
}
