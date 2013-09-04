package quarto;

public class Game {
	private Player player1;
	private Player player2;
	private UI ui = new UI();

	public void gameLoop() {
		playerSelection();

		while (true) {
			break;
		}
	}

	// Player 1 chooses piece for player 2
	// Player 2 places piece
	// Player 2 chooses piece for player 1

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
