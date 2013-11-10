package pso;

public class Constants {
	static int NUMBEROFPARTICLES = 3000;
	static int DIMENSIONS = 1;
	static int KNAPSACKSIZE = 2001;
	static int MAXITERATIONS = 500;
	static int NCLOSESTNEIGHBOURS = 3;
	static int TXTLINES = 2001;
	static int KNAPSACKMAXVOLUMEPERITEM = 4;
	static double MAXWEIGHT = 1000;
	static double MAXVOLUME = 1000;
	static double GLOBALFITNESSGOAL = 0.001;
	static double C1 = 1; // Should be in the range [0,2>	//Personal
	static double C2 = 2; // Should be in the range [0,2>	//Neighborhood
	static double INERTIA = 20;
	static double CIRCLEPROBLEMRANGE = 1234;
	static double KNAPSACKVALUETRESHOLD = 0.7;
	static String KNAPSACKURL = "src/pso/pso-packages.txt";
}
