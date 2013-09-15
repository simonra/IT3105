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
			board = new Board();

			gameLoop();
		}

		System.out.println(p1.getName() + ": " + p1wins);
		System.out.println(p2.getName() + ": " + p2wins);
	}

	public void gameLoop() {
		currentPlayer = p2;

		currentPiece = board.selectPiece(currentPlayer.selectPiece(board));

		switchPlayer();

		while (true) {

			currentPlayer.placePiece(board, currentPiece);

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
		if (player1String.equals("ai1"))
			p1 = new AIPlayer(1);
		if (player1String.equals("ai2"))
			p1 = new AIPlayer(2);
		if (player1String.equals("ai3"))
			p1 = new AIPlayer(3);
		if (player1String.equals("ai4"))
			p1 = new AIPlayer(4);

		System.out.println("Select player 2 (human, random, novice, ai#)");
		String player2String = ui.selectPlayer();
		if (player2String.equals("human"))
			p2 = new HumanPlayer();
		if (player2String.equals("random"))
			p2 = new RandomPlayer();
		if (player2String.equals("novice"))
			p2 = new NovicePlayer();
		if (player2String.equals("ai1"))
			p2 = new AIPlayer(1);
		if (player2String.equals("ai2"))
			p2 = new AIPlayer(2);
		if (player2String.equals("ai3"))
			p2 = new AIPlayer(3);
		if (player2String.equals("ai4"))
			p2 = new AIPlayer(4);
	}
}
