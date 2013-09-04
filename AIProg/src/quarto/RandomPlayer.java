package quarto;

public class RandomPlayer implements Player {

	@Override
	public void placePiece() {
		// TODO Auto-generated method stub
	}

	@Override
	public int selectPiece(Board board) {
		return (int) Math.floor(Math.random() * board.getPieces().size());
	}
}
