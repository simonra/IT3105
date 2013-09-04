package quarto;

public class Game {
	private Player p1;
	private Player p2;
	private UI ui = new UI();
	private Board board = new Board();
	private Logic logic = new Logic();
	private CurrentPlayer currentPlayer = CurrentPlayer.player1;
	private Piece currentPiece;

	public void gameLoop() {
		playerSelection();
		currentPlayer = CurrentPlayer.player1;

		// print please select piece
		// Første runde
		System.out.println("Please select a piece to give to your opponent");
		board.printPieces();

		currentPiece = board.selectPiece(ui.selectPiece());
		while (true) {
			board.PrintBoard();

			// place piece
			placePiece();

			// Wincheck
			if (winCheck()) {
				board.PrintBoard();
				System.out.println(currentPlayer + " is the winner!");
				break;
			}

			// Drawcheck
			if (board.getPieces().size() == 0) {
				System.out.println("The game is a draw.");
				break;
			}

			System.out
					.println("Please select a piece to give to your opponent");
			board.PrintBoard();
			board.printPieces();

			currentPiece = board.selectPiece(ui.selectPiece());

			switchPlayer();
		}
	}

	private void placePiece() {
		System.out.println("Please place " + currentPiece.pieceString()
				+ " with coordinates xy");
		String xy = ui.readCoordinates();
		board.PlacePiece(currentPiece, Integer.parseInt("" + xy.charAt(0)),
				Integer.parseInt("" + xy.charAt(1)));
	}

	// Player 1 chooses piece for player 2
	// Player 2 places piece
	// Player 2 chooses piece for player 1

	public boolean winCheck() {
		if (logic.isWon(board))
			return true;
		return false;
	}

	public void switchPlayer() {
		if (currentPlayer == CurrentPlayer.player1) {
			currentPlayer = CurrentPlayer.player2;
		} else {
			currentPlayer = CurrentPlayer.player1;
		}
	}

	/** Prints the board and remaining pieces */
	private void printInfo() {
		board.PrintBoard();
		board.printPieces();
	}

	private void playerSelection() {
		System.out.println("Select player 1 (human, random, novice, ai#)");
		String player1String = ui.selectPlayer();
		if (player1String.equals("human"))
			p1 = new HumanPlayer();
		if (player1String.equals("random"))
			p1 = new RandomPlayer();
		if (player1String.equals("novice"))
			p1 = new NovicePlayer();

		System.out.println("Select player 2 (human, random, novice, ai#)");
		String player2String = ui.selectPlayer();
		if (player2String.equals("human"))
			p2 = new HumanPlayer();
		if (player2String.equals("random"))
			p2 = new RandomPlayer();
		if (player2String.equals("novice"))
			p2 = new NovicePlayer();
	}
}
