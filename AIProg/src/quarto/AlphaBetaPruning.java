package quarto;

public class AlphaBetaPruning {
	
	private int originalDepth;
	private Node bestNodeForNextMove;
	private Move bestNextMove;
	private double bestAlphaSeenSoFar;
	/**Returns the best move for the board passed to it, and the depth desired to search. Node.firstmoveToThisNode contains the move which the player should make. Node.pieceGiven contains the piece which the player should give.*/
	public Node getBestNodeForNextMove(Board board, Piece givenPiece, int depth){
		originalDepth = depth;
		bestAlphaSeenSoFar = Double.NEGATIVE_INFINITY;
		Node node = new Node(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, null, true, board, givenPiece, null);
		double ab = alphabeta(node, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
		return bestNodeForNextMove;
	}
	
	/*Algorithm as given on http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning#Pseudocode*/
	//Gir alphaBeta verdier, men returnerer ikke noden man burde velge. Trenger funksjonalitet for dette
	private double alphabeta(Node node, int depth, double alpha, double beta, boolean maximizingPlayer){
		//If we have reached the limit of our search-depth or the node is terminal (won, lost, drawn), return the heuristic
		if(depth == 0 || node.terminal)
			return node.getHeuristic();
		//Do alphabeta for maximizing player
		if(maximizingPlayer){
			/*Iterate through each child-node. In each itteration sets the alpha 
			 * to the maximum of the current alpha or the beta value of the child node. 
			 * If the beta is less than or equal to the alpha, we prune the branch. 
			 * (We can guarantee that that this node is certainly better or equal to another one 
			 * of it's siblings, and thus there is no need to check it's children further 
			 * (It's parent minimizer would never choose its branch anyway) )*/
			for (Node childNode : node.getChildren()) {
				alpha = Math.max(alpha, alphabeta(childNode, depth-1, alpha, beta, !maximizingPlayer));
				/*Sjekker om vi er rotnoden og oppdaterer alfaen (beste trekket). 
				 * Hvis man gjør det, oppdater det beste barnet man tar vare på. 
				 * (Å lagre den beste alfaverdien er en legacy-feature jeg ikke vet om brekker noe hvis fjernes)*/
				if (depth == originalDepth && alpha > bestAlphaSeenSoFar){
					bestAlphaSeenSoFar = alpha;
//					saveMoveOrBoardStateThatWouldLeadToThis();
					bestNodeForNextMove = childNode;
				}
				if(beta <= alpha)
					break;
					//Or return alpha
			}
			return alpha;
		}
		//Do alphabeta for minimizing player
		else{
			/* If the beta is less than or equal to the alpha, we prune the branch. 
			 * (We can guarantee that that this node is certainly worse or equal to another one 
			 * of it's siblings, and thus there is no need to check it's children further 
			 * (It's parent maximizer would never choose its branch anyway) )*/
			for (Node childNode : node.getChildren()) {
				beta = Math.min(beta, alphabeta(childNode, depth-1, alpha, beta, !maximizingPlayer));
				if(beta <= alpha)
					break;
			}
			return beta;
		}
	}
	
}
