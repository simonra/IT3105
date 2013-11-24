package pso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		System.out.println("Starting:");
		// PsoMain.PsoMainMethod();
		// NearestThreePsoMain.NearestThreePsoMainMethod();
		// KnapsackMain.KnapsackMainMethod();

		// KnapsackMain.KnapsackMainMethod();

		System.out
				.println("Enter particleG, particle3, knapsack, or knapsackV:");
		String a = readInput();
		if (a.equals("particleG"))
			PsoMain.PsoMainMethod();
		if (a.equals("particle3"))
			NearestThreePsoMain.NearestThreePsoMainMethod();
		if (a.equals("knapsack"))
			KnapsackMain.KnapsackMainMethod();
		if (a.equals("knapsackV"))
			KnapsackVolumeMain.KnapsackMainMethod();
	}

	public static String readInput() {
		String s = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			s = br.readLine();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return s;
	}
}
