package pso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Particle {
	public ArrayList<Double> positions;
	public ArrayList<Double> velocity;
	public ArrayList<Double> bestPositionKnownToMe;
	public double fitness;
	public double bestFitnessKnownToMe;
	
	public Particle(){
		positions = new ArrayList<Double>();
		velocity = new ArrayList<Double>();
		bestPositionKnownToMe = new ArrayList<Double>();
		bestFitnessKnownToMe = Double.MAX_VALUE;
		//set it's position randomly within given range
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			double random1 = Math.random();
			double random2 = Math.random();
			double randomNegativity1 = Math.pow(Math.floor(random1 * 2), -1);
			double randomNegativity2 = Math.pow(Math.floor(random2 * 2), -1);
			positions.add(randomNegativity1 * random2 * 10000000000.0);
			velocity.add(randomNegativity2 * random1 * 10000000);
		}
		evaluateFitness();
		Collections.copy(bestPositionKnownToMe, positions);
	}
	
	void evaluateFitness(){
		for (Double position : positions) {
			fitness += position * position;
		}
		fitness = Math.sqrt(fitness);
	}
	
	public void updateVelocity(ArrayList<Double> bestPositionSeenInNeighborhood, double R1, double R2){
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			velocity.set(i,
						 velocity.get(i) + 
						 (Constants.C1 * R1 * (bestPositionKnownToMe.get(i) - positions.get(i)) ) + 
						 (Constants.C2 * R2 * (bestPositionSeenInNeighborhood.get(i) - positions.get(i)) ) 
					);
		}
	}
	
	public void updatePosition(){
		for (int i = 0; i < Constants.DIMENSIONS; i++) {
			positions.set(i, positions.get(i) + velocity.get(i));
		}
	}
}























