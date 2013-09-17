package quarto;

import java.util.ArrayList;

public class NewABP {

	private int originalDepth;
	private Move bestNextMove;
	private double bestAlphaSeenSoFar;
	private ArrayList<NewNode> nodes;

	public Move getNextMove(Board board, Piece currentPiece, int depth) {
		this.originalDepth = depth;
		bestAlphaSeenSoFar = Double.NEGATIVE_INFINITY;
		Move rootMove = new Move(currentPiece, 0, 0, null);

		nodes = new ArrayList<>();
		for (int i = 0; i < depth + 1; i++) {
			nodes.add(new NewNode(null, true, 0, new Board(board), rootMove));
		}
		NewNode node = nodes.get(depth);

		alphabeta(node, depth, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY, true);

		return bestNextMove;
	}

	private double alphabeta(NewNode node, int depth, double alpha,
			double beta, boolean maximizingPlayer) {
		// If we have reached the limit of our search-depth or the node is
		// terminal (won, lost, drawn), return the heuristic
//		System.out.println("I have run an itteration of ab-pruning");
		System.out.println("depth: " + depth);
		if (depth == 0 || node.isTerminal){
			System.out.println("And I return at 0");
			return node.getHeuristic();
		}
		// Do alphabeta for maximizing player
		if (maximizingPlayer) {
			/*
			 * Iterate through each child-node. In each itteration sets the
			 * alpha to the maximum of the current alpha or the beta value of
			 * the child node. If the beta is less than or equal to the alpha,
			 * we prune the branch. (We can guarantee that that this node is
			 * certainly better or equal to another one of it's siblings, and
			 * thus there is no need to check it's children further (It's parent
			 * minimizer would never choose its branch anyway) )
			 */
			while (node.hasNextChild()) {
				alpha = Math.max(
						alpha,
						alphabeta(node.getNextChild(nodes.get(depth - 1)),
								depth - 1, alpha, beta, !maximizingPlayer));
				if (depth == originalDepth && alpha > bestAlphaSeenSoFar) {
					bestAlphaSeenSoFar = alpha;
					// saveMoveOrBoardStateThatWouldLeadToThis();
					bestNextMove = nodes.get(originalDepth - 1).move;
				}
				if (beta <= alpha)
					return alpha;
			}
			return alpha;
		}
		// Do alphabeta for minimizing player
		else {
			/*
			 * If the beta is less than or equal to the alpha, we prune the
			 * branch. (We can guarantee that that this node is certainly worse
			 * or equal to another one of it's siblings, and thus there is no
			 * need to check it's children further (It's parent maximizer would
			 * never choose its branch anyway) )
			 */
			while (node.hasNextChild()) {
				beta = Math.min(
						beta,
						alphabeta(node.getNextChild(nodes.get(depth - 1)),
								depth - 1, alpha, beta, !maximizingPlayer));
				if (beta <= alpha)
					return beta;
			}
			return beta;
		}
	}

}
