package pso;

import java.util.Random;

public class Particle {
	public Double[] positions;
	public Double[] velocity;
	public Double[] bestPositionKnownToMe;
	public double fitness;
	public double bestFitnessKnownToMe;

	public Particle(Random r) {
		positions = new Double[Constants.DIMENSIONS];
		velocity = new Double[Constants.DIMENSIONS];
		bestPositionKnownToMe = new Double[Constants.DIMENSIONS];
		bestFitnessKnownToMe = Double.MAX_VALUE;
		// set it's position randomly within given range
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			double randomNegativity1 = Math.pow(-1,
					Math.floor(r.nextDouble() * 2));
			double randomNegativity2 = Math.pow(-1,
					Math.floor(r.nextDouble() * 2));
			positions[i] = randomNegativity1 * r.nextDouble() * 1000;
			velocity[i] = randomNegativity2 * r.nextDouble() * 1;
			// velocity[i] = 0.0;
		}
		evaluateFitness();
		System.arraycopy(positions, 0, bestPositionKnownToMe, 0,
				Constants.DIMENSIONS);
	}

	void evaluateFitness() {
		fitness = 0;
		for (Double position : positions) {
			fitness += position * position;
		}
	}

	public void updateVelocity(Double[] bestPositionSeenInNeighborhood,
			double R1, double R2) {
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			velocity[i] = Constants.INERTIA
					* velocity[i]
					+ (Constants.C1 * R1 * (bestPositionKnownToMe[i] - positions[i]))
					+ (Constants.C2 * R2 * (bestPositionSeenInNeighborhood[i] - positions[i]));
		}
	}

	public void updatePosition() {
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			positions[i] = positions[i] + velocity[i];
		}
	}
}
