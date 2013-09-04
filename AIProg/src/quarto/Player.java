package quarto;

public interface Player {
	public void placePiece(Board board, Piece currentPiece);

	public int selectPiece(Board board);
}
