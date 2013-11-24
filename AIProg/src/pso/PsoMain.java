package pso;

import java.util.Random;

public class PsoMain {

	/**
	 * Has a swarm of particles in an array, for each particle, updates its
	 * velocity and position, and checks its fitness
	 */
	public static void PsoMainMethod() {
		// The fields making up the swarm
		Particle[] particles = new Particle[Constants.NUMBEROFPARTICLES];
		Random random = new Random();
		Double[] bestSeenPosition = new Double[Constants.DIMENSIONS];
		double bestFitnessSeen = Double.MAX_VALUE;
		int counter = 0;

		// Finds the best fitness
		for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
			Particle p = new Particle(random, i);
			particles[i] = p;
			if (p.fitness < bestFitnessSeen) {
				System.arraycopy(p.positions, 0, bestSeenPosition, 0,
						Constants.DIMENSIONS);
				bestFitnessSeen = p.fitness;
			}
		}

		// Runs the swarm
		boolean conditions = true;
		conditions &= bestFitnessSeen > Constants.GLOBALFITNESSGOAL;
		conditions &= counter < Constants.MAXITERATIONS;

		while (conditions) {
			// For each particle, updates its velocity and position, and updates
			// fitness globally and for each particle
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

			// Updates the condtittions for running
			counter++;
			conditions &= bestFitnessSeen > Constants.GLOBALFITNESSGOAL;
			conditions &= counter < Constants.MAXITERATIONS;

			// For making running larger things less visually boring
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
