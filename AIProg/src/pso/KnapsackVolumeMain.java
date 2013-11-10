package pso;

import java.util.Random;

public class KnapsackVolumeMain {

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

	public static double[] KnapsackMainMethod() {
		KnapsackParticleVolume[] particles = new KnapsackParticleVolume[Constants.NUMBEROFPARTICLES];
		Random random = new Random();
		double[] bestSeenPosition = new double[Constants.KNAPSACKSIZE];
		double bestFitnessSeen = Double.MAX_VALUE;
		int counter = 0;
		
		KnapsackParticleVolume bestParticle = null;
		double bestValue = 0;
		double bestWeight = 0;
		double bestNumberOfItems = 0;
		double bestPFitness = 0;
		double bestVolume = 0;
		
		Double[][] solutionSpace = KnapsackFileReader.standardKnapsack(
				KnapsackFileReader.readFile(Constants.KNAPSACKURL), random);

		for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
			KnapsackParticleVolume p = new KnapsackParticleVolume(random, solutionSpace);
			particles[i] = p;
			if (p.fitness < bestFitnessSeen) {
				System.arraycopy(p.position, 0, bestSeenPosition, 0,
						Constants.KNAPSACKSIZE);
				bestFitnessSeen = p.fitness;
				
				bestParticle = p;
				bestValue = p.objectiveValue;
				bestWeight = p.objectiveWeight;
				bestVolume = p.objectiveVolume;
				bestNumberOfItems = p.numberOfItems;
				bestPFitness = p.fitness;
			}
		}

		boolean conditions = true;
		conditions &= counter < Constants.MAXITERATIONS;
//		System.out.println("while");
		while (conditions) {
			for (KnapsackParticleVolume particle : particles) {
				particle.updateVelocity(bestSeenPosition, random.nextDouble(),
						random.nextDouble());
				particle.updatePosition();
				particle.evaluateFitness();
				if (particle.fitness < particle.bestFitnessKnownToMe) {
					System.arraycopy(particle.position, 0,
							particle.bestPositionKnownToMe, 0,
							Constants.KNAPSACKSIZE);
					particle.bestFitnessKnownToMe = particle.fitness;

					if (particle.bestFitnessKnownToMe < bestFitnessSeen) {
						System.arraycopy(particle.position, 0, bestSeenPosition,
								0, Constants.KNAPSACKSIZE);
						bestFitnessSeen = particle.fitness;
						
						bestParticle = particle;
						bestValue = particle.objectiveValue;
						bestWeight = particle.objectiveWeight;
						bestVolume = particle.objectiveVolume;
						bestNumberOfItems = particle.numberOfItems ;
						bestPFitness = particle.fitness;
					}
				}
			}

			counter++;
			conditions &= counter < Constants.MAXITERATIONS;

			if (!conditions || counter % 500 == 0) {
				System.out.println("This is iteration " + counter
						+ " and the best fitness is " + bestFitnessSeen + ".");
//				System.out.println("Items: " + bestParticle.numberOfItems);
//				System.out.println("Value: " + bestParticle.objectiveValue);
//				System.out.println("Weight: " + bestParticle.objectiveWeight);
//				System.out.println("Fitness: " + bestParticle.fitness);
				
				System.out.println("Items: " + bestNumberOfItems);
				System.out.println("Value: " + bestValue);
				System.out.println("Weight: " + bestWeight);
				System.out.println("Volume: " + bestVolume);
				System.out.println("Fitness: " + bestPFitness);
			}
		}
		return new double[]{bestFitnessSeen, (double)bestNumberOfItems, bestWeight};
	}
}
