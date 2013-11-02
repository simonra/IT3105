package pso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PsoMain {
	
	
	
	public static void main(String[] args) {
		ArrayList<Particle> particles = new ArrayList<Particle>();
		Random random = new Random();
		double averageFitness = 0;
		ArrayList<Double> bestSeenPosition = new ArrayList<Double>();
		int counter = 0;

		for (int i = 0; i < Constants.NUMBEROFPARTICLES; i++) {
			Particle p = new Particle(random);
			particles.add(p);
			averageFitness += p.fitness;
		}
		averageFitness /= Constants.NUMBEROFPARTICLES;
		System.out.println(averageFitness);
		
		boolean conditions = true;
		conditions &= averageFitness < Constants.GLOBALFITNESSGOAL;
		conditions &= counter > Constants.MAXITERATIONS;			
		while(conditions){
			averageFitness = 0;
			for (Particle particle : particles) {
				particle.updateVelocity(bestSeenPosition, random.nextDouble(), random.nextDouble());
				particle.updatePosition();
				particle.evaluateFitness();
				if(particle.fitness < particle.bestFitnessKnownToMe)
					Collections.copy(particle.bestPositionKnownToMe, particle.positions);
				
			}
			
			counter++;
			conditions &= averageFitness < Constants.GLOBALFITNESSGOAL;
			conditions &= counter > Constants.MAXITERATIONS;			
		}
	}
	

}
