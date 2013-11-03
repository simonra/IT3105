package pso;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		// PsoMain.PsoMainMethod();
		// NearestThreePsoMain.NearestThreePsoMainMethod();

		Double[][] a = KnapsackFileReader.standardKnapsack(
				KnapsackFileReader.readFile(Constants.KNAPSACKURL),
				new Random());
		for (int i = 0; i < a.length; i++) {
			System.out.println("value: " + a[i][0] + ", weight: " + a[i][1]
					+ ", volume: " + a[i][2]);
		}
	}
}
