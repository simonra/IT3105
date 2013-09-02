package quarto;

public class Move {
	public Piece piece;
	public int x;
	public int y;
	
	public Move(Piece p, int x, int y){
		this.piece = p;
		this.x = x;
		this.y = y;
	}
}
