package quarto;

public class Tournament {
	private Player p1;
	private Player p2;
	private UI ui = new UI();
	private Board board = new Board();
	private Logic logic = new Logic();
	private Player currentPlayer;
	private Piece currentPiece;
	private int p1wins = 0;
	private int p2wins = 0;

	public void tournamet() {
		playerSelection();

		for (int i = 0; i < 100; i++) {
			gameLoop();
		}
	}

	public void gameLoop() {
		currentPlayer = p2;

		currentPiece = board.selectPiece(currentPlayer.selectPiece(board));

		switchPlayer();

		while (true) {

			// Wincheck
			if (winCheck()) {
				if (currentPlayer == p1) {
					p1wins++;
				} else {
					p2wins++;
				}
				break;
			}

			// Drawcheck
			if (board.getPieces().size() == 0) {
				break;
			}

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
		if (player1String.equals("ai2"))
			p1 = new AIPlayer();

		System.out.println("Select player 2 (human, random, novice, ai#)");
		String player2String = ui.selectPlayer();
		if (player2String.equals("human"))
			p2 = new HumanPlayer();
		if (player2String.equals("random"))
			p2 = new RandomPlayer();
		if (player2String.equals("novice"))
			p2 = new NovicePlayer();
		if (player2String.equals("ai2"))
			p2 = new AIPlayer();
	}
}
