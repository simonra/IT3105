package quarto;

public class AIPlayer implements Player {

	private Move move;
	private AlphaBetaPruning ab = new AlphaBetaPruning();
	private Player novicePlayer = new NovicePlayer();
	private int depth;
	private String name = "ai";

	public AIPlayer(int depth) {
		this.depth = depth;
		name += depth;
	}

	@Override
	public void placePiece(Board board, Piece currentPiece) {

		// Hvis det er de første 8 trekkene, spill som novice
		if (board.getPieces().size() > 8) {
			novicePlayer.placePiece(board, currentPiece);
			return;
		}

		move = ab.getNextMove(board, currentPiece, depth);
		board.PlacePiece(currentPiece, move.x, move.y);
	}

	@Override
	public int selectPiece(Board board) {
		if (board.getPieces().size() > 8) {
			return novicePlayer.selectPiece(board);
		}

		return board.getPieces().indexOf(move.givePiece);
	}

	@Override
	public String getName() {
		return name;
	}
}
