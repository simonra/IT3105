package pso;

public class Position {
	private double x;
	private double y;
	
	//Setters
	public void setXY(double x, double y){
		this.x = x;
		this.y = y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	
	//Getters
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
}
