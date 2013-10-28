package pso;

public class Particle {
	Position position;
	Velocity velocity;
	double fitness;
	
	
	public double getFitness(){
		//TODO: find the fitness
		
		return fitness;
	}
	
	
	
	
	//Setters
	public void setPosition(Position pos){
		this.position = pos;
	}
	public void setVelocity(Velocity v){
		this.velocity = v;
	}
	
	//Getters
	public Position getPosition(){
		return this.position;
	}
	public Velocity getVelocity(){
		return this.velocity;
	}
}
