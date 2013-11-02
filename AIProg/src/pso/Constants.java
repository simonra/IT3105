package pso;

public interface Constants {
	int NUMBEROFPARTICLES = 5;
	int DIMENSIONS = 1;
	int KNAPSACKSIZE = 0;
	int MAXITERATIONS = 1000;
	double GLOBALFITNESSGOAL = 0.001;
	double C1 = 0.5; // Should be in the range [0,2>
	double C2 = 1.5; // Should be in the range [0,2>
	double INERTIA = 1;
	double CIRCLEPROBLEMRANGE = 100;
}
