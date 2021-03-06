package quarto;

public class InternetPlayer implements MeteorGameObserver {
	private static final String PLAYER_NAME = "detteErUnikt";
	private static final String GAME_ID = "password";
	private String name = "internetPlayer";
	private AlphaBetaPruning ab;
	private MeteorGame game;
	private Board board;
	private Piece currentPiece;
	Player aiForThisGame;
	private Move move;
	private NovicePlayer novicePlayer = new NovicePlayer();
	private AIPlayer ai2 = new AIPlayer(2);

	public InternetPlayer() {

		aiForThisGame = new NovicePlayer();
		game = new MeteorGame(this);
		game.connect();

	}

	@Override
	public String getGameId() {
		return GAME_ID;
	}

	@Override
	public String getPlayerId() {
		return PLAYER_NAME;
	}

	@Override
	public void searchingOponent() {
		System.out.println("Searching for oponent");

	}

	@Override
	public void startGame() {
		ab = new AlphaBetaPruning();
		currentPiece = null;
		board = new Board();
		System.out
				.println("Server sier fra at spillet kan startes, init spill brett her");
	}

	@Override
	public void restart() {
		System.out
				.println("Server sier fra at spillet blir resatt nå, kaller start game strx");
	}

	@Override
	public void doMove() {
		System.out.println("Din tur til å gjøre et move");
		// Scanner s = new Scanner(System.in);
		// System.out.println("Velg board position: ");
		// int selectedPos = s.nextInt();
		// System.out.println("Velg neste piece: ");
		// int selectedPiece = s.nextInt();
		int selectedPos;
		int selectedPiece;
		if (currentPiece == null) {
			selectedPos = -1;
		} else {
			selectedPos = placePiece(board, currentPiece);

		}
		
		int selectPieceIndex = selectPiece(board);
		currentPiece = board.selectPiece(selectPieceIndex); //Kr�sjer p� -1. N�r f�r man -1?

		if (Logic.isWon(board)) {
			System.out.println("I won with this board:");
			board.PrintBoard();
			System.out.println("The piece I placed was:");
			System.out.println(currentPiece.pieceString());
			selectedPiece = -1;
		} else {
			selectedPiece = InternetConvert
					.selectPieceToNetCommand(currentPiece);
		}
		System.out.println("Do Move");
		board.PrintBoard();
		game.doMove(selectedPos, selectedPiece);
	}

	@Override
	public void moveDone(int positionIndex, int pieceIndex) {
		System.out.println("Position Index:" + positionIndex);
		System.out.println("Piece Index: " + pieceIndex);
		if (positionIndex == -1) {
			currentPiece = InternetConvert.getPieceFromNet(pieceIndex);
			return;
		}
		int y = positionIndex % 4;
		int x = (int) Math.floor(positionIndex / 4);

		System.out.println("Position index: " + positionIndex);
		board.PlacePiece(currentPiece, x, y);
		board.RemovePieceFromPool(currentPiece);

		System.out.println("Move done");
		board.PrintBoard();
		currentPiece = InternetConvert.getPieceFromNet(pieceIndex);
	}

	public String getName() {
		return name;
	}

	public int placePiece(Board board, Piece currentPiece) {
		// Hvis det er de f�rste 8 trekkene, spill som novice
		if (board.getPieces().size() > 13) {
			while (true) {
				int x = (int) (Math.floor(Math.random() * 4));
				int y = (int) (Math.floor(Math.random() * 4));
				if (board.getBoard()[x][y] == null) {
					board.PlacePiece(currentPiece, x, y);
					return x + y * 4;
				}
			}
		}

		move = ab.getNextMove(board, currentPiece, 2);
		board.PlacePiece(currentPiece, move.x, move.y);
		return move.x  * 4 + move.y;
	}

	public int selectPiece(Board board) {
		if (board.getPieces().size() > 13) {
			return novicePlayer.selectPiece(board);
		}

		return board.getPieces().indexOf(move.givePiece);
		// return board.getPieces().indexOf(move.givePiece);

		// int pieceIndex = (int) Math.floor(Math.random()
		// * board.getPieces().size());
		// return pieceIndex;
		// return board.getPieces().indexOf(move.givePiece);

	}

}
