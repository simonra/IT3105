package quarto;

import java.util.ArrayList;

public class Node {
	
	public double value;
	public double alpha;
	public double beta;
	public boolean maximizer;
	
	private ArrayList<Node> children;
	private Board board;
	public boolean terminal;
	
	public ArrayList<Node> getChildren(){
		//TODO
		return null;
	}
	public double getHeuristic() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void generateChildren(){
		children = new ArrayList<Node>();
		//TODO: populate children with every possible next move (One child = one new node with a new board with one piece placed
	}
	

}
