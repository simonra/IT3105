package quarto;

public class RandomPlayer implements Player {
	private String name = "random";

	@Override
	public void placePiece(Board board, Piece currentPiece) {
		while (true) {
			int x = (int) (Math.floor(Math.random() * 4));
			int y = (int) (Math.floor(Math.random() * 4));
			if (board.getBoard()[x][y] == null) {
				board.PlacePiece(currentPiece, x, y);
				return;
			}
		}
	}

	@Override
	public int selectPiece(Board board) {
		return (int) Math.floor(Math.random() * board.getPieces().size());
	}

	@Override
	public String getName() {
		return name;
	}
}
