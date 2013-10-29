package pso;

public class PolynomialTerm {
	private double coefficient;
	private String variables;
	
	public void PolynomialTerm(int coefficient, String variables){
		this.coefficient = coefficient;
		this.variables = variables;
	}
	
	public void PolynomialTerm(PolynomialTerm oldTerm){
		this.coefficient = oldTerm.coefficient();
		this.variables = oldTerm.variables();
	}
	
	public double coefficient(){
		return coefficient;
	}
	public String variables(){
		return variables;
	}
}
