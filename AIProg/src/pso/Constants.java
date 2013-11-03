package pso;

public interface Constants {
	int NUMBEROFPARTICLES = 100;
	int DIMENSIONS = 1;
	int KNAPSACKSIZE = 2001;
	int MAXITERATIONS = 1000;
	int NCLOSESTNEIGHBOURS = 3;
	int TXTLINES = 2001;
	int KNAPSACKMAXVOLUMEPERITEM = 4;
	double MAXWEIGHT = 1000;
	double GLOBALFITNESSGOAL = 0.001;
	double C1 = 1; // Should be in the range [0,2>
	double C2 = 1; // Should be in the range [0,2>
	double INERTIA = 1;
	double CIRCLEPROBLEMRANGE = 1234;
	String KNAPSACKURL = "src/pso/pso-packages.txt";
}
