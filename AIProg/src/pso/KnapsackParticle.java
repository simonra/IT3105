package pso;

import java.util.Random;

public class KnapsackParticle {
	public boolean[] items;
	public boolean[] velocity;
	public Double[][] solutionSpace;
	public boolean[] bestItemsKnownToMe;
	public double fitness;
	public double bestFitnessKnownToMe;

	public KnapsackParticle(Random r, Double[][] solutionSpace) {
		this.solutionSpace = solutionSpace;
		items = new boolean[Constants.KNAPSACKSIZE];
		velocity = new boolean[Constants.KNAPSACKSIZE];
		bestItemsKnownToMe = new boolean[Constants.DIMENSIONS];
		bestFitnessKnownToMe = Double.MAX_VALUE;

		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			items[i] = r.nextBoolean();
			velocity[i] = r.nextBoolean();
		}

		System.arraycopy(items, 0, bestItemsKnownToMe, 0,
				Constants.KNAPSACKSIZE);

		evaluateFitness();
	}

	void evaluateFitness() {
		fitness = 0;
		double value = 0;
		double weight = 0;

		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			if (items[i]) {
				value += solutionSpace[i][0];
				weight += solutionSpace[i][1];
			}
		}

		if (weight > Constants.MAXWEIGHT) {
			value *= -weight;
		}

		fitness = Math.pow(2, -value);
	}

	public void updateVelocity(boolean[] bestItemsSeenInNeighborhood,
			double R1, double R2) {

		boolean b1 = Constants.C1 * R1 < 1;
		boolean b2 = Constants.C2 * R2 < 1;

		boolean var1;
		boolean var2;

		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			var1 = b1 && bestItemsKnownToMe[i];
			var2 = b2 && bestItemsSeenInNeighborhood[i];
			velocity[i] = velocity[i] || var1 || var2;

			if (!var1 && !var2) {
				velocity[i] = false;
			}
		}
	}

	public void updatePosition() {

	}
}
