package quarto;

public class AIPlayer implements Player {

	private Node node = new Node(new Logic(), 0, 0, null, false, new Board(), null, null);
	private AlphaBetaPruning ab = new AlphaBetaPruning();

	@Override
	public void placePiece(Board board, Piece currentPiece) {
		// TODO fikse depth
		node = ab.getBestNodeForNextMove(board, currentPiece, 2);
		board.PlacePiece(currentPiece, node.firstMoveToThisState.x,
				node.firstMoveToThisState.y);

	}

	@Override
	public int selectPiece(Board board) {
		if (board.getPieces().size() > 13) {
			return (int) Math.floor(Math.random() * board.getPieces().size());
		}

		return board.getPieces().indexOf(node.pieceToGive);
	}
}
