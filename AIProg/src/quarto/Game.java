package quarto;

public class Game {
	private Player p1;
	private Player p2;
	private UI ui = new UI();
	private Board board = new Board();
	private Logic logic = new Logic();
	private Player currentPlayer;
	private Piece currentPiece;

	public void gameLoop() {
		playerSelection();
		currentPlayer = p1;

		// Første runde
		System.out.println("Please select a piece to give to your opponent");
		board.printPieces();

		currentPiece = board.selectPiece(currentPlayer.selectPiece(board));

		while (true) {
			board.PrintBoard();

			// place piece
			System.out.println("Please place " + currentPiece.pieceString()
					+ " with coordinates xy");
			currentPlayer.placePiece(board, currentPiece);

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
			currentPiece = board.selectPiece(currentPlayer.selectPiece(board));

			switchPlayer();
		}
	}

	public boolean winCheck() {
		if (logic.isWon(board))
			return true;
		return false;
	}

	public void switchPlayer() {
		if (currentPlayer == p1) {
			currentPlayer = p2;
		} else {
			currentPlayer = p1;
		}
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
