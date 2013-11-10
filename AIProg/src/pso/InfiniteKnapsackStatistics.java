package pso;

public class InfiniteKnapsackStatistics {

	
	
	
	public static void main(String[] args) {
		Constants.NUMBEROFPARTICLES = 2000;
		Constants.C1 = 1; // Should be in the range [0,2>	//Personal
		Constants.C2 = 1; // Should be in the range [0,2>	//Neighborhood
		Constants.INERTIA = 20;
		Constants.KNAPSACKVALUETRESHOLD = 0.7;
		
		double[] bestValues = new double[3];
		double[] tempArray = new double[3];
		
		for (int i = 0; i < 20; i++) {
			System.arraycopy(KnapsackMain.KnapsackMainMethod(), 0, tempArray, 0, 3);
			if(tempArray[0] < bestValues[0]){
				System.arraycopy(tempArray, 0, bestValues, 0, 3);
			}
			System.out.println("Done with knapsack " + i);
		}
		for (int i = 0; i < tempArray.length; i++) {
			System.out.println(tempArray[i]);
		}
	}

}
