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
		KnapsackParticle[] particles = new KnapsackParticle[Constants.NUMBEROFPARTICLES];
		Random random = new Random();
		boolean[] bestSeenPosition = new boolean[Constants.KNAPSACKSIZE];
		double bestFitnessSeen = Double.MAX_VALUE;
		int counter = 0;

		Double[][] solutionSpace = KnapsackFileReader.standardKnapsack(
				KnapsackFileReader.readFile(Constants.KNAPSACKURL), random);

		for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
			KnapsackParticle p = new KnapsackParticle(random, solutionSpace);
			particles[i] = p;
			if (p.fitness < bestFitnessSeen) {
				System.arraycopy(p.position, 0, bestSeenPosition, 0,
						Constants.KNAPSACKSIZE);
				bestFitnessSeen = p.fitness;
			}
		}

		boolean conditions = true;
		conditions &= counter < Constants.MAXITERATIONS;
		System.out.println("while");
		while (conditions) {
			for (KnapsackParticle particle : particles) {
				particle.updateVelocity(bestSeenPosition, random.nextDouble(),
						random.nextDouble());
				particle.updatePosition();
				particle.evaluateFitness();
				if (particle.fitness < particle.bestFitnessKnownToMe) {
					System.arraycopy(particle.position, 0,
							particle.bestItemsKnownToMe, 0,
							Constants.DIMENSIONS);
					particle.bestFitnessKnownToMe = particle.fitness;

					if (particle.bestFitnessKnownToMe < bestFitnessSeen) {
						System.arraycopy(particle.position, 0, bestSeenPosition,
								0, Constants.DIMENSIONS);
						bestFitnessSeen = particle.fitness;
					}
				}
			}

			counter++;
			conditions &= counter < Constants.MAXITERATIONS;

			if (!conditions || counter % 1 == 0) {
				System.out.println("This is iteration " + counter
						+ " and the best fitness is " + bestFitnessSeen + ".");
				System.out.println("Items: " + particles[0].numberOfItems);
				System.out.println("Value: " + particles[0].objectiveValue);
				System.out.println("Weight: " + particles[0].objectiveWeight);
				System.out.println("Fitness: " + particles[0].fitness);
				System.out.println("Velocity: " + particles[0].velocity);
			}
		}
	}
}
