package pso;

import java.util.Random;

public class KnapsackParticle {
	public double[] position;
	public Double[][] solutionSpace;
	public double[] velocity;
	public double[] bestItemsKnownToMe;
	public double fitness;
	public double bestFitnessKnownToMe;
	
	public int numberOfItems;
	public double objectiveValue;
	public double objectiveWeight;

	public KnapsackParticle(Random r, Double[][] solutionSpace) {
		this.solutionSpace = solutionSpace;
		position = new double[Constants.KNAPSACKSIZE];
		// velocity = r.nextInt();
		velocity = new double[Constants.KNAPSACKSIZE];
		bestItemsKnownToMe = new double[Constants.KNAPSACKSIZE];
		bestFitnessKnownToMe = Double.MAX_VALUE;

		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			position[i] = r.nextDouble();
			velocity[i] = r.nextDouble();
		}

		System.arraycopy(position, 0, bestItemsKnownToMe, 0,
				Constants.KNAPSACKSIZE);

		evaluateFitness();
	}

	void evaluateFitness() {
		fitness = 0;
		numberOfItems = 0;

		double value = 0;
		double weight = 0;

		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			if (position[i] > Constants.KNAPSACKVALUETRESHOLD) {
				value += solutionSpace[i][0];
				weight += solutionSpace[i][1];
				numberOfItems += 1;
			}
		}

		objectiveValue = value;
		objectiveWeight = weight;

		if (weight > Constants.MAXWEIGHT) {

			value = -10;
		}

		fitness = Math.pow(2, -value);
	}

	public void updateVelocity(boolean[] bestItemsSeenInNeighborhood,
			double R1, double R2) {

		double local = (Constants.C1 * R1 * (BinaryMath.findDifference(
				bestItemsKnownToMe, position)));

		double global = (Constants.C2 * R2 * (BinaryMath.findDifference(
				bestItemsSeenInNeighborhood, position)));

		int bounds = 100000000;

		if (local > bounds) {
			local = bounds;
		}

		if (local < -bounds) {
			local = -bounds;
		}

		if (global > bounds) {
			global = bounds;
		}

		if (global < -bounds) {
			global = -bounds;
		}

		velocity = (int) (Constants.INERTIA * velocity + local + global);

		if (velocity > bounds) {
			velocity = bounds;
		}

		if (velocity < -bounds) {
			velocity = -bounds;
		}

	}

	public void updatePosition() {
		if (velocity > 0) {
			BinaryMath.addBinary(position, velocity);
		}

		else {
			BinaryMath.subtractBinary(position, -velocity);
		}
	}
}
