package pso;

import java.util.Arrays;
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
				int[] neighboursIndex = new int[Constants.NCLOSESTNEIGHBOURS];
				double[] distanceIndex = new double[Constants.NUMBEROFPARTICLES];
				double[] sortedDistances = new double[Constants.NUMBEROFPARTICLES];
				localBestFitnessSeen = Double.MAX_VALUE;

				// N nearest neighbors
				for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
					double distance = 0;
					for (int j = 0; j < Constants.DIMENSIONS; j++) {
						distance += (particles[i].positions[j] - particle.positions[j])
								* (particles[i].positions[j] - particle.positions[j]);
					}
					distanceIndex[i] = distance;
				}
				System.arraycopy(distanceIndex, 0, sortedDistances, 0,
						Constants.NUMBEROFPARTICLES);
				Arrays.sort(sortedDistances);
				int bestNeighbor = -1;
				for (int i = 0; i < Constants.NCLOSESTNEIGHBOURS; i++) {
					neighboursIndex[i] = Arrays.asList(distanceIndex).indexOf(
							sortedDistances[i + 1]);
					if (particles[neighboursIndex[i]].fitness < localBestFitnessSeen) {
						bestNeighbor = i;
						localBestFitnessSeen = particles[neighboursIndex[i]].fitness;
					}
				}
				System.arraycopy(particles[bestNeighbor].positions, 0,
						localBestSeenPosition, 0, Constants.DIMENSIONS);

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
