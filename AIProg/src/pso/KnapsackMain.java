package pso;

import java.util.Random;

public class KnapsackMain {

	/*
	 * A small container has a weight limit of 1000kg. A package has a weight
	 * and a value. You have a sett of 2000 packages to select from, as defined
	 * in the file packages.txt The file packages.txt has two parameters per
	 * line, first parameter is value, second parameter is weight Select
	 * packages to get fill your container with the most value while still being
	 * under the weight limit.
	 * 
	 * Demonstrate a run of your algorithm that terminates after 500 iterations.
	 */

	public static void KnapsackMainMethod() {
		Particle[] particles = new Particle[Constants.NUMBEROFPARTICLES];
		Random random = new Random();
		Double[] bestSeenPosition = new Double[Constants.DIMENSIONS];
		double bestFitnessSeen = Double.MAX_VALUE;
		int counter = 0;

		for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
			Particle p = new Particle(random, i);
			particles[i] = p;
			if (p.fitness < bestFitnessSeen) {
				System.arraycopy(p.positions, 0, bestSeenPosition, 0,
						Constants.DIMENSIONS);
				bestFitnessSeen = p.fitness;
			}
		}

		boolean conditions = true;
		conditions &= bestFitnessSeen > Constants.GLOBALFITNESSGOAL;
		conditions &= counter < Constants.MAXITERATIONS;

		while (conditions) {
			for (Particle particle : particles) {
				particle.updateVelocity(bestSeenPosition, random.nextDouble(),
						random.nextDouble());
				particle.updatePosition();
				particle.evaluateFitness();
				if (particle.fitness < particle.bestFitnessKnownToMe) {
					System.arraycopy(particle.positions, 0,
							particle.bestPositionKnownToMe, 0,
							Constants.DIMENSIONS);
					particle.bestFitnessKnownToMe = particle.fitness;

					if (particle.bestFitnessKnownToMe < bestFitnessSeen) {
						System.arraycopy(particle.positions, 0,
								bestSeenPosition, 0, Constants.DIMENSIONS);
						bestFitnessSeen = particle.fitness;
					}
				}
			}

			counter++;
			conditions &= bestFitnessSeen > Constants.GLOBALFITNESSGOAL;
			conditions &= counter < Constants.MAXITERATIONS;

			if (!conditions || counter % 10 == 0) {
				System.out.println("This is iteration " + counter
						+ " and the best fitness is " + bestFitnessSeen + ".");
				System.out.println("	The first particles position is: "
						+ particles[0].positions[0] + ", and its velocity is: "
						+ particles[0].velocity[0]);
			}
		}
	}
}
