package pso;

import java.util.Random;

public class KnapsackParticleVolume {
	public double[] position;
	public Double[][] solutionSpace;
	public double[] velocity;
	public double[] bestPositionKnownToMe;
	public double fitness;
	public double bestFitnessKnownToMe;

	public int numberOfItems;
	public double objectiveValue;
	public double objectiveWeight;
	public double objectiveVolume;

	public KnapsackParticleVolume(Random r, Double[][] solutionSpace) {
		this.solutionSpace = solutionSpace;
		position = new double[Constants.KNAPSACKSIZE];
		// velocity = r.nextInt();
		velocity = new double[Constants.KNAPSACKSIZE];
		bestPositionKnownToMe = new double[Constants.KNAPSACKSIZE];
		bestFitnessKnownToMe = Double.MAX_VALUE;

		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			position[i] = r.nextDouble();
			velocity[i] = r.nextDouble();
		}

		System.arraycopy(position, 0, bestPositionKnownToMe, 0,
				Constants.KNAPSACKSIZE);

		evaluateFitness();
	}

	void evaluateFitness() {
		fitness = 0;
		numberOfItems = 0;

		double value = 0;
		double weight = 0;
		double volume = 0;

		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			if (position[i] > Constants.KNAPSACKVALUETRESHOLD) {
				value += solutionSpace[i][0];
				weight += solutionSpace[i][1];
				volume += solutionSpace[i][2];
				numberOfItems += 1;
			}
		}

		objectiveValue = value;
		objectiveWeight = weight;
		objectiveVolume = volume;

		if(weight > Constants.MAXWEIGHT || volume > Constants.MAXVOLUME){
			if (weight > Constants.MAXWEIGHT)
				fitness = 1000 + weight;
			if(volume > Constants.MAXVOLUME)
				fitness += 1000 + volume;
		}
		else {
			fitness = -value;
		}
	}

	public void updateVelocity(double[] bestPositionSeenInNeighborhood,
			double R1, double R2) {
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			velocity[i] = Constants.INERTIA
					* velocity[i]
					+ (Constants.C1 * R1 * (bestPositionKnownToMe[i] - position[i]))
					+ (Constants.C2 * R2 * (bestPositionSeenInNeighborhood[i] - position[i]));

			if (velocity[i] > 0.005) {
				velocity[i] = 0.005;
			}

			if (velocity[i] < -0.005) {
				velocity[i] = -0.005;
			}
		}

	}

	public void updatePosition() {
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			position[i] = position[i] + velocity[i];
			if(position[i] > 1) position[i] = 1;
			if(position[i] < 0) position[i] = 0;
		}
	}
}
