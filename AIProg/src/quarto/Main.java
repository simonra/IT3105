package quarto;

public class Main {
	public static void main(String[] args) {
		UI ui = new UI();
		Game game = new Game();

		// Bestemme hva slags spillere man skal ha
		System.out.println("Welcome to Quarto!");

		game.gameLoop();

		// Oppsett av brett

		while (true) {
			// Print remaining pieces
			// Print board
			// Print currently held piece

			// Place piece
			// Check win
			// Pick piece for other player

			// Change player

			break;
		}

		// Useless code below
		// board.PlacePiece(board.getPieces().get(0), 0, 0);
		// board.PlacePiece(board.getPieces().get(13), 3, 3);
		// board.PrintBoard();
	}
}