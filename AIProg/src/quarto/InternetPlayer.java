package quarto;

public class InternetPlayer implements MeteorGameObserver {
	private static final String PLAYER_NAME = "detteErUnikt";
	private static final String GAME_ID = "testGame";
	private String name = "internetPlayer";
	private AlphaBetaPruning ab = new AlphaBetaPruning();
	private MeteorGame game;
	private Board board = new Board();
	private Piece currentPiece;
	Player aiForThisGame;
	private Move move;
	private NovicePlayer novicePlayer = new NovicePlayer();

	public InternetPlayer() {
		game = new MeteorGame(this);
		game.connect();

		aiForThisGame = new NovicePlayer();

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
		// System.out.println("Din tur til å gjøre et move");
		// Scanner s = new Scanner(System.in);
		// System.out.println("Velg board position: ");
		// int selectedPos = s.nextInt();
		// System.out.println("Velg neste piece: ");
		// int selectedPiece = s.nextInt();

		int selectedPos = placePiece(board, currentPiece);
		int selectPieceIndex = selectPiece(board);
		currentPiece = board.selectPiece(selectPieceIndex);

		int selectedPiece = InternetConvert
				.selectPieceToNetCommand(currentPiece);

		game.doMove(selectedPos, selectedPiece);
	}

	@Override
	public void moveDone(int positionIndex, int pieceIndex) {
		System.out.println(positionIndex);
		int x = InternetConvert.getXY(positionIndex, 0);
		int y = InternetConvert.getXY(positionIndex, 1);

		board.PlacePiece(currentPiece, x, y);
		board.RemovePieceFromPool(currentPiece);

		currentPiece = InternetConvert.getPieceFromNet(pieceIndex);
	}

	public String getName() {
		return name;
	}

	public int placePiece(Board board, Piece currentPiece) {
		if (board.getPieces().size() > 0) {
			while (true) {
				int x = (int) (Math.floor(Math.random() * 4));
				int y = (int) (Math.floor(Math.random() * 4));
				if (board.getBoard()[x][y] == null) {
					board.PlacePiece(currentPiece, x, y);
					return x + y * 4;
				}
			}
		}

		move = ab.getNextMove(board, currentPiece, 4);
		board.PlacePiece(currentPiece, move.x, move.y);
		return move.x + move.y * 4;
	}

	public int selectPiece(Board board) {
		int pieceIndex = (int) Math.floor(Math.random()
				* board.getPieces().size());
		return pieceIndex;
		// return board.getPieces().indexOf(move.givePiece);

	}

}
