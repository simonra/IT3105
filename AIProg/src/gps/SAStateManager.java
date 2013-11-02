package gps;

import java.util.ArrayList;

public interface SAStateManager {

	/** Calculates the objective value for this state */
	public double objectiveValue();

	/** Returns the goal objective value */
	public double getTargetObjectiveFunctionValue();

	/** Generates neighbours for this state */
	public ArrayList<SAStateManager> getNeighbors();

	@Override
	public String toString();
}