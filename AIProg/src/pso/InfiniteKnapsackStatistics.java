package pso;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InfiniteKnapsackStatistics {

	
	
	
	public static void main(String[] args) {
		int numberOfRuns = 21;
		int satisticsSize = 100;
		writeToFile("\n Number of particles is varied below:\n");
		for (int i = 0; i < numberOfRuns; i++) {
			findStatistics(1000 + i*150, 1, 1, 1, 0.7, satisticsSize);
			System.out.println("Iteration "+i+" done");
		}
		writeToFile("\n C1 is varied below:");
		for (int i = 0; i < numberOfRuns; i++) {
			findStatistics(2000, 0 + i*0.1, 1, 1, 0.7, satisticsSize);
			System.out.println("Iteration "+i+" done");
		}
		writeToFile("\n C2 is varied below:");
		for (int i = 0; i < numberOfRuns; i++) {
			findStatistics(2000, 1, 0 + i*0.1, 1, 0.7, satisticsSize);
			System.out.println("Iteration "+i+" done");
		}
		writeToFile("\n Inertia is varied below:");
		for (int i = 0; i < numberOfRuns; i++) {
			findStatistics(2000, 1, 1, 1, 0.7, satisticsSize);
			System.out.println("Iteration "+i+" done");
		}
		writeToFile("\n treshold is varied below:");
		for (int i = 0; i < numberOfRuns; i++) {
			findStatistics(2000, 1, 1, 1 + i*2.45, 0.4 + i*0.02, satisticsSize);
			System.out.println("Iteration "+i+" done");
		}
	}
	
	static void findStatistics(int NumberOfParicles, double c1, double c2, double inertia, double treshold, int statisticsSize) {
		Constants.NUMBEROFPARTICLES = NumberOfParicles;
		Constants.C1 = c1; // Should be in the range [0,2>	//Personal
		Constants.C2 = c2; // Should be in the range [0,2>	//Neighborhood
		Constants.INERTIA = inertia;
		Constants.KNAPSACKVALUETRESHOLD = treshold;
		
		double[] bestValues = new double[3];
		double[] tempArray = new double[3];
		
		for (int i = 0; i < statisticsSize; i++) {
			System.arraycopy(KnapsackMain.KnapsackMainMethod(), 0, tempArray, 0, 3);
			if(tempArray[0] < bestValues[0]){
				System.arraycopy(tempArray, 0, bestValues, 0, 3);
			}
			writeToFile(makeString(bestValues));
		}
	}
	
	static String makeString(double[] iterationResult){
		String dataEntry = "";
		dataEntry += "\nNumber of particles: " + 
				Constants.NUMBEROFPARTICLES + ", C1: "+ Constants.C1 + ", C2: " + Constants.C2 +
				", Inertia: " + Constants.INERTIA + ", Knapsack treshold: " + Constants.KNAPSACKVALUETRESHOLD + 
				", Weight: " + iterationResult[2] + ", Number of Items: " + iterationResult[1] + ", Value: " + iterationResult[0] +
				"\n";
		return dataEntry;
	}
	
	static void writeToFile(String toBeWritten){
		try {
//			File file = new File("Files/knapsackStatistics.txt");
//			if(!file.exists())
//				file.createNewFile();
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(toBeWritten);
//			bw.close();
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Files/knapsackStatistics.txt", true)));
			out.println(toBeWritten);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}















