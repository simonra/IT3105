package quarto;

public class CopyOfAlphaBetaPruning {

	// Old fields
	private int originalDepth;
	private Node bestNodeForNextMove;
	private Move bestNextMove;
	private double bestAlphaSeenSoFar;
	Logic logic;

	// New fields:
	Board workingBoardDepth0;
	Board originalBoardDepth0;
	Board workingBoardDepth1;
	Board originalBoardDepth1;
	Board workingBoardDepth2;
	Board originalBoardDepth2;
	Board workingBoardDepth3;
	Board originalBoardDepth3;

	Player novicePlayer = new NovicePlayer();

	/**
	 * Returns the best move for the board passed to it, and the depth desired
	 * to search. Node.firstmoveToThisNode contains the move which the player
	 * should make. Node.pieceGiven contains the piece which the player should
	 * give.
	 */
	public Node getBestNodeForNextMove(Board board, Piece givenPiece, int depth) {
		originalDepth = depth;
		bestAlphaSeenSoFar = Double.NEGATIVE_INFINITY;
		logic = new Logic();
		Node node = new Node(logic, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY, null, true, board, givenPiece, null);
		double ab = alphabeta(node, depth, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY, true);
		return bestNodeForNextMove;
	}

	/*
	 * Algorithm as given on
	 * http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning#Pseudocode
	 */
	// Gir alphaBeta verdier, men returnerer ikke noden man burde velge. Trenger
	// funksjonalitet for dette
	private double alphabeta(Node node, int depth, double alpha, double beta,
			boolean maximizingPlayer) {

		if (depth == 0 || node.terminal)
			return node.getHeuristic();

		if (maximizingPlayer) {

			for (Node childNode : node.getChildren()) {
				alpha = Math.max(
						alpha,
						alphabeta(childNode, depth - 1, alpha, beta,
								!maximizingPlayer));

				if (depth == originalDepth && alpha > bestAlphaSeenSoFar) {
					bestAlphaSeenSoFar = alpha;
					bestNodeForNextMove = childNode;
				}
				if (beta <= alpha)
					return alpha;

			}
			return alpha;
		}

		else {

			for (Node childNode : node.getChildren()) {
				beta = Math.min(
						beta,
						alphabeta(childNode, depth - 1, alpha, beta,
								!maximizingPlayer));
				if (beta <= alpha)
					return beta;
			}
			return beta;
		}
	}

}
