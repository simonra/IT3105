package quarto;

public class Main {
	public static void main(String[] args) {
		Board board = new Board();
		System.out.println("asdf");
		board.PrintBoard();
		System.out.println("asdf");
		
		board.PlacePiece(board.getPieces().get(0), 0, 0);
		board.PlacePiece(board.getPieces().get(13), 3, 3);
		board.PrintBoard();
	}
}