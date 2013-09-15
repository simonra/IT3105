package quarto;

import java.util.ArrayList;

/**
 * The node class used in the alpha-beta pruning algorithm
 * 
 * @author simon
 * 
 */
public class Node {

	public double value;
	public boolean maximizer;
	public Piece pieceToPlace;
	public Piece pieceToGive;
	public Move move;
	/** The children of this node (they are nodes) */
	private ArrayList<Node> children;
	/** The board corresponding to this node */
	private Board board;
	/** Whether this node is a terminal node or not (won, lost, drawn, etc.) */
	public boolean terminal;

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
	public Node(boolean maximizer, Board board, Piece pieceToPlace,
			Piece pieceToGive) {
		this.pieceToPlace = pieceToPlace;
		this.pieceToGive = pieceToGive;
		this.maximizer = maximizer;
		this.board = new Board(board);
		this.terminal = false;

		terminalCheck();
	}

	public ArrayList<Node> getChildren() {
		if (children == null) {
			generateChildren();
		}
		return children;
	}

	/** Calculates and returns the heuristic of this node (if requested) */
	public double getHeuristic() {
		// The obvious condittion for what value should be returned
		if (Logic.isWon(board)) {
			if (maximizer) {
				return -100;
			} else {
				return 100;
			}
		}

		return someHeuristic();
	}

	/** The current heuristic (the magic part of it) */
	private double someHeuristic() {
		int certainThreeInARow = 0;
		for (int i = 0; i < 4; i++) {
			if (board.getBoard()[0][i] == null ^ board.getBoard()[1][i] == null
					^ board.getBoard()[2][i] == null
					^ board.getBoard()[3][i] == null)
				certainThreeInARow++;
		}

		for (int i = 0; i < 4; i++) {
			if (board.getBoard()[i][0] == null ^ board.getBoard()[i][1] == null
					^ board.getBoard()[i][2] == null
					^ board.getBoard()[i][3] == null)
				certainThreeInARow++;
		}

		if (board.getBoard()[0][0] == null ^ board.getBoard()[1][1] == null
				^ board.getBoard()[2][2] == null
				^ board.getBoard()[3][3] == null)
			certainThreeInARow++;

		if (board.getBoard()[3][0] == null ^ board.getBoard()[2][1] == null
				^ board.getBoard()[1][2] == null
				^ board.getBoard()[0][3] == null)
			certainThreeInARow++;

		if (maximizer)
			return certainThreeInARow;
		else {
			return -certainThreeInARow;
		}
	}

	/** Checks if this node is a terminal node or not, i.e. won or drawn */
	public void terminalCheck() {
		if (board.getPieces().size() == 0)
			terminal = true;
		if (Logic.isWon(board))
			terminal = true;
	}

	/** Generates the children of this node */
	private void generateChildren() {
		// Checks if the node is terminal (Has no children)
		if (terminal)
			return;
		// If not, instanciates the children.
		children = new ArrayList<Node>();
		// Iterates through each place a child can be placed

		if (pieceToGive != null) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (board.getBoard()[i][j] == null) {
						for (Piece pieceChildMustGive : board.getPieces()) {
							// Creates the new board with the piece played
							Board tempBoard = new Board(board);
							tempBoard.PlacePiece(pieceToPlace, i, j);

							// Removes the piece the child must give from the
							// pool
							// of pieces the child can give on
							tempBoard.RemovePieceFromPool(pieceChildMustGive);

							// Checks (and creates if this is the first layer)
							// the
							// first move that would lead to this node
							children.add(new Node(!maximizer, tempBoard,
									pieceToGive, pieceChildMustGive));
						}
					}
				}
			}
		} else {
			ArrayList<Piece> tempList = Logic.CopyArrayList(board.getPieces());
			for (Piece pieceRootGives : tempList) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (board.getBoard()[i][j] == null) {
							for (Piece pieceChildMustGive : board.getPieces()) {
								// Creates the new board with the piece played
								Board tempBoard = new Board(board);
								tempBoard.PlacePiece(pieceToPlace, i, j);

								// Removes the piece the child must give from
								// the pool of pieces the child can give on
								tempBoard
										.RemovePieceFromPool(pieceChildMustGive);

								// Checks (and creates if this is the first
								// layer)
								// the first move that would lead to this node
								children.add(new Node(!maximizer, tempBoard,
										pieceRootGives, pieceChildMustGive));
							}
						}
					}
				}
			}
		}
	}
}
