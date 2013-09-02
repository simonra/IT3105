package quarto;

import java.util.ArrayList;

public class Node {
	
	public Node(double alpha, double beta, Move firstMoveToThisState, boolean maximizer, Board board){
		logic = new Logic();
		
		this.alpha = alpha;
		this.beta = beta;
		this.firstMoveToThisState = firstMoveToThisState;
		this.maximizer = maximizer;
		this.board = board;
		
		terminal = false;
		terminalCheck();
		
	}
	
	public double value;
	public double alpha;
	public double beta;
	public boolean maximizer;
	public Move firstMoveToThisState;
	Logic logic;
	
	private ArrayList<Node> children;
	private Board board;
	public boolean terminal;
	
	public ArrayList<Node> getChildren(){
		//TODO
		if(children == null){
			generateChildren();
		}
		return children;
	}
	public double getHeuristic() {
		// TODO Auto-generated method stub
		if(logic.isWon(board) && terminal)
			return 100;
		if(logic.isWon(board) && !terminal)
			return -100;
		return someHeuristic();
	}
	
	private double someHeuristic() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void terminalCheck(){
		if(logic.isWon(board))
			terminal = true;
		if(board.getPieces().size() == 0)
			terminal = true;
	}
	
	private void generateChildren(){
		if(terminal)
			return;
		children = new ArrayList<Node>();
		//TODO: populate children with every possible next move (One child = one new node with a new board with one piece placed
		//TODO: add first move here if previous first move is null (i.e. from the root node)
	}
	

}
