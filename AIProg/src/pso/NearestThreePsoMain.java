package pso;

import java.util.Random;

public class NearestThreePsoMain {

	public static void NearestThreePsoMainMethod() {
		Particle[] particles = new Particle[Constants.NUMBEROFPARTICLES];
		Random random = new Random();
		Double[] localBestSeenPosition = new Double[Constants.DIMENSIONS];
		Double[] globalBestSeenPosition = new Double[Constants.DIMENSIONS];
		double localBestFitnessSeen = Double.MAX_VALUE;
		double globalBestFitnessSeen = Double.MAX_VALUE;
		int counter = 0;

		for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
			Particle p = new Particle(random, i);
			particles[i] = p;
			if (p.fitness < globalBestFitnessSeen) {
				System.arraycopy(p.positions, 0, globalBestSeenPosition, 0,
						Constants.DIMENSIONS);
				globalBestFitnessSeen = p.fitness;
			}
		}

		boolean conditions = true;
		conditions &= globalBestFitnessSeen > Constants.GLOBALFITNESSGOAL;
		conditions &= counter < Constants.MAXITERATIONS;

		while (conditions) {
			for (Particle particle : particles) {
				int firstIndex = -1;
				int secondIndex = -1;
				int thirdIndex = -1;

				double firstDistance = Double.MAX_VALUE;
				double secondDistance = Double.MAX_VALUE;
				double thirdDistance = Double.MAX_VALUE;

				localBestFitnessSeen = Double.MAX_VALUE;

				for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
					double distance = 0;
					for (int j = 0; j < Constants.DIMENSIONS; j++) {
						distance += (particles[i].positions[j] - particle.positions[j])
								* (particles[i].positions[j] - particle.positions[j]);
					}

					if (particles[i].equals(particle))
						continue;

					if (firstDistance > distance) {
						thirdDistance = secondDistance;
						thirdIndex = secondIndex;

						secondDistance = firstDistance;
						secondIndex = firstIndex;

						firstDistance = distance;
						firstIndex = i;
					}

					if (secondDistance > distance) {
						thirdDistance = secondDistance;
						thirdIndex = secondIndex;

						secondDistance = distance;
						secondIndex = i;
					}

					if (thirdDistance > distance) {
						thirdDistance = distance;
						thirdIndex = i;
					}
				}

				if (particles[firstIndex].fitness < localBestFitnessSeen) {
					localBestFitnessSeen = particles[firstIndex].fitness;
					System.arraycopy(particles[firstIndex].positions, 0,
							localBestSeenPosition, 0, Constants.DIMENSIONS);
				}

				if (particles[secondIndex].fitness < localBestFitnessSeen) {
					localBestFitnessSeen = particles[secondIndex].fitness;
					System.arraycopy(particles[secondIndex].positions, 0,
							localBestSeenPosition, 0, Constants.DIMENSIONS);
				}

				if (particles[thirdIndex].fitness < localBestFitnessSeen) {
					localBestFitnessSeen = particles[thirdIndex].fitness;
					System.arraycopy(particles[thirdIndex].positions, 0,
							localBestSeenPosition, 0, Constants.DIMENSIONS);
				}

				// Position and velocity:
				particle.updateVelocity(localBestSeenPosition,
						random.nextDouble(), random.nextDouble());
				particle.updatePosition();
				particle.evaluateFitness();
				if (particle.fitness < particle.bestFitnessKnownToMe) {
					System.arraycopy(particle.positions, 0,
							particle.bestPositionKnownToMe, 0,
							Constants.DIMENSIONS);
					particle.bestFitnessKnownToMe = particle.fitness;

					if (particle.bestFitnessKnownToMe < globalBestFitnessSeen) {
						System.arraycopy(particle.positions, 0,
								globalBestSeenPosition, 0, Constants.DIMENSIONS);
						globalBestFitnessSeen = particle.fitness;
					}
				}
			}

			counter++;
			conditions &= globalBestFitnessSeen > Constants.GLOBALFITNESSGOAL;
			conditions &= counter < Constants.MAXITERATIONS;

			if (!conditions || counter % 10 == 0) {
				System.out.println("This is iteration " + counter
						+ " and the (global) best fitness is "
						+ globalBestFitnessSeen + ".");
				System.out.println("	The first particles position is: "
						+ particles[0].positions[0] + ", and its velocity is: "
						+ particles[0].velocity[0]);
			}
		}
	}
}
