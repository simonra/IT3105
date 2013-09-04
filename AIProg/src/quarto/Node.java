package quarto;

import java.util.ArrayList;


/** The node class used in the alpha-beta pruning algorithm
 * @author simon
 *
 */
public class Node {
	
	
	/**The constructor for the node. Takes parameters, initializes the logic used in this node, and checks whether this node is (a) terminal (node).
	 * @param alpha This nodes alpha value
	 * @param beta This nodes beta value
	 * @param firstMoveToThisState The initial move in the search tree that would lead to this node. Usefull for determining which move is the best move in the search
	 * @param maximizer Boolean, tells if this is a max or min node in the ab-search
	 * @param board The board/gamestate for this movenode
	 * @param givenPiece The piece that has to be played in this node
	 */
	public Node(double alpha, double beta, Move firstMoveToThisState, boolean maximizer, Board board, Piece givenPiece, Piece pieceToGive){
		logic = new Logic();
		
		this.alpha = alpha;
		this.beta = beta;
		this.firstMoveToThisState = firstMoveToThisState;
		this.givenPiece = givenPiece;
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
	public Piece givenPiece;
	Logic logic;
	
	private ArrayList<Node> children;
	private Board board;
	public boolean terminal;
	
	public ArrayList<Node> getChildren(){
		if(children == null){
			generateChildren();
		}
		return children;
	}
	public double getHeuristic() {
		if(logic.isWon(board) && terminal)
			return 100;
		if(logic.isWon(board) && !terminal)
			return -100;
		return someHeuristic();
	}
	
	/**The current heuristic*/
	private double someHeuristic() {
		Logic logic = new Logic();
		//Total number of lines you can win on = 10
		ArrayList<Piece> nextWinPieces = logic.PiecesThatWinOnNextMove(board);
		int numberOfUnspentPieces = board.getPieces().size();
		if(nextWinPieces.size() == 0){
			//TODO funkyMath
			
		}
		//Novice-heuristic
		if(nextWinPieces.size() == numberOfUnspentPieces && maximizer)
			return -100;
		if(nextWinPieces.size() == numberOfUnspentPieces && maximizer)
			return 100;
		/*If there is an odd number of non-vinning pieces I can hand over, I can guarantee that if the opponen plays optimaly
		 * (and can't create new vinning options), I can force him to hand me a winning piece. Similairly, he can do the same 
		 * if I have an even number of non-vinning pieces at disposition.*/
		if(( numberOfUnspentPieces - nextWinPieces.size() ) % 2 == 0 && maximizer)
			return -50;
		else
			return 50;
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
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if(board.getBoard()[i][j] == null){
					for (Piece pieceToGive : board.getPieces()) {
						//Creates the new board with the piece played
						Board tempBoard = new Board();
						tempBoard = board;
						tempBoard.PlacePiece(givenPiece, i, j);
						tempBoard.RemovePieceFromPool(givenPiece);
						//Checks and creates the first move that would lead to this node
						Move tempMove;
						if(firstMoveToThisState == null)
							tempMove = new Move(givenPiece, i, j);
						else
							tempMove = firstMoveToThisState;
						//Creates the new node
						children.add(new Node(alpha, beta, tempMove, !maximizer, tempBoard, pieceToGive));
					}
				}
			}
		}
	}
	

}
