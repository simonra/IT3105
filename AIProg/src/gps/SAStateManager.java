package gps;

import java.util.ArrayList;

public interface SAStateManager {

	public double objectiveValue();

	public double getTargetObjectiveFunctionValue();

	public ArrayList<SAStateManager> getNeighbors();

	public String toString();
}