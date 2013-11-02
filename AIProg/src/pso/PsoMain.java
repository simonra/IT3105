package pso;

import java.util.Random;

public class PsoMain {

	public static void main(String[] args) {
		Particle[] particles = new Particle[Constants.NUMBEROFPARTICLES];
		Random random = new Random();
		double averageFitness = 0;
		Double[] bestSeenPosition = new Double[Constants.DIMENSIONS];
		int counter = 0;

		for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
			Particle p = new Particle(random);
			particles[i] = p;
			averageFitness += p.fitness;
		}
		averageFitness /= Constants.NUMBEROFPARTICLES;
		System.out.println(averageFitness);

		boolean conditions = true;
		conditions &= averageFitness < Constants.GLOBALFITNESSGOAL;
		conditions &= counter > Constants.MAXITERATIONS;
		while (conditions) {
			averageFitness = 0;
			for (Particle particle : particles) {
				particle.updateVelocity(bestSeenPosition, random.nextDouble(),
						random.nextDouble());
				particle.updatePosition();
				particle.evaluateFitness();
				if (particle.fitness < particle.bestFitnessKnownToMe)
					System.arraycopy(particle.positions, 0,
							particle.bestPositionKnownToMe, 0,
							Constants.DIMENSIONS);

			}

			counter++;
			conditions &= averageFitness < Constants.GLOBALFITNESSGOAL;
			conditions &= counter > Constants.MAXITERATIONS;

			if (!conditions || counter % 10 == 0) {
				System.out
						.println("This is iteration " + counter
								+ " and the average fitness is "
								+ averageFitness + ".");
			}
		}
	}
}
