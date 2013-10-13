package gps;

import java.util.ArrayList;

public class SAGCStateManager implements SAStateManager {

	/** The name says it all */
	boolean[][] neighbourMatrix;
	/**
	 * The int color value of the node of the index value (ex: colorOfNode[nodes
	 * number/id] = nodes color value)
	 */
	int[] colorOfNodeN;

	/** Instansierer matrisene, tar inn filer, lager matrisestuff */
	public SAGCStateManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double objectiveValue() {
		// Regne totalt antall konflikter
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTargetObjectiveFunctionValue() {
		return 0;
	}

	@Override
	public ArrayList<SAStateManager> getNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}

}
