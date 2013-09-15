package quarto;

public class Move {
	public Piece currentPiece;
	public int x;
	public int y;
	public Piece givePiece;

	public Move(Piece p, int x, int y, Piece givePiece) {
		this.currentPiece = p;
		this.x = x;
		this.y = y;
		this.givePiece = givePiece;
	}
}
