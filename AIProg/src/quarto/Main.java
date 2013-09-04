package quarto;

public class Main {
	public static void main(String[] args) {
		UI ui = new UI();

		// Bestemme hva slags spillere man skal ha
		System.out.println("Welcome to Quarto!");

		System.out.println("Select player 1 (human, random, novice, ai#)");
		String player1 = ui.selectPlayer();

		System.out.println("Select player 2 (human, random, novice, ai#)");
		String player2 = ui.selectPlayer();

		// Oppsett av brett

		Board board = new Board();
		System.out.println("asdf");
		board.PrintBoard();
		System.out.println("asdf");

		board.PlacePiece(board.getPieces().get(0), 0, 0);
		board.PlacePiece(board.getPieces().get(13), 3, 3);
		board.PrintBoard();
	}
}