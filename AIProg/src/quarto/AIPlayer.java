package quarto;

public class AIPlayer implements Player {

	private Node node = new Node(new Logic(), 0, 0, null, false, new Board(),
			null, null);
	private AlphaBetaPruning ab = new AlphaBetaPruning();
	private Player novicePlayer = new NovicePlayer();
	private int depth;

	public AIPlayer(int depth) {
		this.depth = depth;
	}

	@Override
	public void placePiece(Board board, Piece currentPiece) {
		// TODO fikse depth
		if (board.getPieces().size() > 8) {
			novicePlayer.placePiece(board, currentPiece);
			return;
		}

		node = ab.getBestNodeForNextMove(board, currentPiece, depth);
		board.PlacePiece(currentPiece, node.firstMoveToThisState.x,
				node.firstMoveToThisState.y);
	}

	@Override
	public int selectPiece(Board board) {
		if (board.getPieces().size() > 8) {
			return novicePlayer.selectPiece(board);
		}

		return board.getPieces().indexOf(node.pieceToGive);
	}
}
