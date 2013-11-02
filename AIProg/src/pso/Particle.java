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
			double randomNegativity1 = Math.pow(Math.floor(r.nextDouble() * 2),
					-1);
			double randomNegativity2 = Math.pow(Math.floor(r.nextDouble() * 2),
					-1);
			positions[i] = randomNegativity1 * r.nextDouble() * 10000000000.0;
			velocity[i] = randomNegativity2 * r.nextDouble() * 10000000;
		}
		evaluateFitness();
		// Collections.copy(bestPositionKnownToMe, positions);
		//
	}

	void evaluateFitness() {
		for (Double position : positions) {
			fitness += position * position;
		}
		fitness = Math.sqrt(fitness);
	}

	public void updateVelocity(Double[] bestPositionSeenInNeighborhood,
			double R1, double R2) {
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			velocity[i] = (velocity[i]
					+ (Constants.C1 * R1 * (bestPositionKnownToMe[i] - positions[i])) + (Constants.C2
					* R2 * (bestPositionSeenInNeighborhood[i] - positions[i])));
		}
	}

	public void updatePosition() {
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			positions[i] = (positions[i] + velocity[i]);
		}
	}
}
