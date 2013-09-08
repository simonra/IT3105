package quarto;

import java.util.ArrayList;

/**
 * The node class used in the alpha-beta pruning algorithm
 * 
 * @author simon
 * 
 */
public class Node {

	/**
	 * The constructor for the node. Takes parameters, initializes the logic
	 * used in this node, and checks whether this node is (a) terminal (node).
	 * 
	 * @param alpha
	 *            This nodes alpha value
	 * @param beta
	 *            This nodes beta value
	 * @param firstMoveToThisState
	 *            The initial move in the search tree that would lead to this
	 *            node. Usefull for determining which move is the best move in
	 *            the search
	 * @param maximizer
	 *            Boolean, tells if this is a max or min node in the ab-search
	 * @param board
	 *            The board/gamestate for this movenode
	 * @param pieceToPlace
	 *            The piece that has to be played in this node. The
	 *            generateChildren method expects this piece to be removed from
	 *            the passed boards list of available pieces.
	 * @param pieceToGive
	 *            The piece that one gives to the children of this node to place
	 */
	public Node(Logic logic, double alpha, double beta, Move firstMoveToThisState,
			boolean maximizer, Board board, Piece pieceToPlace,
			Piece pieceToGive) {
		this.logic = logic;

		this.alpha = alpha;
		this.beta = beta;
		this.firstMoveToThisState = firstMoveToThisState;
		this.pieceToPlace = pieceToPlace;
		this.pieceToGive = pieceToGive;
		this.maximizer = maximizer;
		this.board = new Board(board);

		terminal = false;
		terminalCheck();

	}

	public double value;
	public double alpha;
	public double beta;
	public boolean maximizer;
	public Move firstMoveToThisState;
	public Piece pieceToPlace;
	public Piece pieceToGive;
	Logic logic;

	/** The children of this node (they are nodes) */
	private ArrayList<Node> children;
	/** The board corresponding to this node */
	private Board board;
	/** Whether this node is a terminal node or not (won, lost, drawn, etc.) */
	public boolean terminal;

	public ArrayList<Node> getChildren() {
		if (children == null) {
			generateChildren();
		}
		return children;
	}

	public double getHeuristic() {
		if (logic.isWon(board) && maximizer)
			return 100;
		if (logic.isWon(board) && !maximizer)
			return -100;
		return someHeuristic();
	}

	/** The current heuristic */
	private double someHeuristic() {
		logic = new Logic();
		// Total number of lines you can win on = 10
		ArrayList<Piece> nextWinPieces = logic.PiecesThatWinOnNextMove(board);
		int numberOfUnspentPieces = board.getPieces().size();
		if (nextWinPieces != null && nextWinPieces.size() == 0) {
			// TODO funkyMath
			return 0;
		}

		// Novice-heuristic
		if (nextWinPieces != null
				&& nextWinPieces.size() == numberOfUnspentPieces && maximizer)
			return -100;
		if (nextWinPieces != null
				&& nextWinPieces.size() == numberOfUnspentPieces && !maximizer)
			return 100;
		/*
		 * If there is an odd number of non-vinning pieces I can hand over, I
		 * can guarantee that if the opponen plays optimaly (and can't create
		 * new vinning options), I can force him to hand me a winning piece.
		 * Similairly, he can do the same if I have an even number of
		 * non-vinning pieces at disposition.
		 */
		if (nextWinPieces != null
				&& (numberOfUnspentPieces - nextWinPieces.size()) % 2 == 0
				&& maximizer)
			return -50;
		else
			return 50;
	}

	public void terminalCheck() {
		if (logic.isWon(board))
			terminal = true;
		if (board.getPieces().size() == 0)
			terminal = true;
	}

	/** Generates the children of this node */
	private void generateChildren() {
		// Checks if the node is terminal (Has no children)
		if (terminal)
			return;
		// If not, instanciates the children.
		children = new ArrayList<Node>();
		// Itterates through each place a child can be placed
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				// If a boardtile is empty, begins the magic
				if (board.getBoard()[i][j] == null) {
					/*
					 * For each leftover piece, generate a node with the current
					 * piece to place placed at [i,j] and the piecetogive as the
					 * piece given to the next node to place, and generate a
					 * piece it must say to its child that it must give
					 */
					for (Piece pieceChildMustGive : board.getPieces()) {
						// Creates the new board with the piece played
						Board tempBoard = new Board(board);
						tempBoard.PlacePiece(pieceToPlace, i, j);

						// Removes the piece the child must give from the pool
						// of pieces the child can give on
						tempBoard.RemovePieceFromPool(pieceChildMustGive);

						// Checks (and creates if this is the first layer) the
						// first move that would lead to this node
						Move tempMove;
						if (firstMoveToThisState == null)
							tempMove = new Move(pieceToPlace, i, j);
						else
							tempMove = firstMoveToThisState;

						/*
						 * If this is a child of the root node, the piece to
						 * give is not specified. We therefore make children
						 * with all of them
						 */

						if (pieceToGive == null) {
							ArrayList<Piece> tempPieces = new ArrayList<Piece>();
							for (Piece piece : board.getPieces()) {
								tempPieces.add(piece);
							}
							for (Piece pieceRootGivesToChildToGive : tempPieces) {
								/*
								 * pieceChildMustGive now placed as the piece
								 * the child must play, because it is removed
								 * from the potential pool in the outside loop
								 */
								// tempBoard
								// .RemovePieceFromPool(pieceRootGivesToChildToGive);

								// TODO vi kommenterte ut denne
								// tempPieces.remove(pieceRootGivesToChildToGive);
								Board tempBoard2 = new Board(tempBoard);
								tempBoard2.setPieces(tempPieces);

								children.add(new Node(logic, alpha, beta, tempMove,
										!maximizer, tempBoard2,
										pieceChildMustGive,
										pieceRootGivesToChildToGive));
							}
						} else
							children.add(new Node(logic, alpha, beta, tempMove,
									!maximizer, tempBoard, pieceToGive,
									pieceChildMustGive));
					}
				}
			}
		}
	}
}
