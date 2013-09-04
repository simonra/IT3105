package quarto;

public class Game {
	private Player player1;
	private Player player2;
	private UI ui = new UI();
	private Board board = new Board();
	private Logic logic = new Logic();
	private CurrentPlayer currentPlayer;

	public void gameLoop() {
		playerSelection();
		printInfo();

		// print please select piece

		while (true) {
			// printInfo: board + pieces

			// place piece

			// Wincheck
			if (winCheck()) {
				System.out.println(currentPlayer + " is the winner!");
				break;
			}

			// Drawcheck

			// select piece for opponent

			switchPlayer();

			break;
		}
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
			player1 = new HumanPlayer();
		if (player1String.equals("random"))
			player1 = new RandomPlayer();
		if (player1String.equals("novice"))
			player1 = new NovicePlayer();

		System.out.println("Select player 2 (human, random, novice, ai#)");
		String player2String = ui.selectPlayer();
		if (player2String.equals("human"))
			player2 = new HumanPlayer();
		if (player2String.equals("random"))
			player2 = new RandomPlayer();
		if (player2String.equals("novice"))
			player2 = new NovicePlayer();
	}
}
