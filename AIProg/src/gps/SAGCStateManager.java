package gps;

import java.util.ArrayList;

public class SAGCStateManager implements SAStateManager {
	public GCFileReader fileReader = new GCFileReader();
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
		int conflicts = 0;
		//
		// for (int i = 0; i < k; i++) {
		// for (int j = i + 1; j < k; j++) {
		// if (/*replace with sammenligning av nabomatrise + farger*/)
		// conflicts++;
		// }
		// }
		return 0 - conflicts;
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
