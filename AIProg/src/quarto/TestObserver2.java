package quarto;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA. User: thaffe Date: 11.09.13 Time: 12:12 To change
 * this template use File | Settings | File Templates.
 */
public class TestObserver2 implements MeteorGameObserver {
	private static final String PLAYER_NAME = "SimonsPC";
	private static final String GAME_ID = "password";

	private MeteorGame game;

	Board board;
	AlphaBetaPruning ab = new AlphaBetaPruning();
	Move currentMove;
	Piece currentPiece;

	public TestObserver2() {
		// Oppretter et nytt spill og connecter til server
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
		System.out
				.println("Server sier fra at spillet kan startes, init spill brett her");
		board = new Board();
		currentPiece = null;
	}

	@Override
	public void restart() {
		System.out
				.println("Server sier fra at spillet blir resatt n�, kaller start game strx");
	}

	@Override
	public void doMove() {
		// System.out.println("Din tur til å gjøre et move");
		// Scanner s = new Scanner(System.in);
		// System.out.println("Velg board position: ");
		// int selectedPos = s.nextInt();
		// System.out.println("Velg neste piece: ");
		// int selectedPiece = s.nextInt();
		int selectedPos;
		int selectedPiece;
		if (currentPiece == null) {
			selectedPos = -1;
			NovicePlayer novicePlayer = new NovicePlayer();
			currentPiece = board.getPieces().get(
					novicePlayer.selectPiece(board));
			selectedPiece = InternetConvert
					.selectPieceToNetCommand(currentPiece);
		} else {
			// TODO:
			currentMove = ab.getNextMove(board, currentPiece, 2);
			// Move er x = i, y = j (verden er omvendt)
			selectedPos = currentMove.x * 4 + currentMove.y;
			currentPiece = currentMove.givePiece;
			selectedPiece = InternetConvert
					.selectPieceToNetCommand(currentPiece);
			board.PlacePiece(currentMove);
		}
		
		if(Logic.isWon(board)){
			game.doMove(selectedPos, -1);
			return;
		}
		board.PrintBoard();
		System.out.println("selectedPos= " + selectedPos + ", and selectedPiece= " +  selectedPiece);
		game.doMove(selectedPos, selectedPiece);
	}

	@Override
	public void moveDone(int positionIndex, int pieceIndex) {
		System.out.println("Du mottok dette movet:" + positionIndex
				+ " og spiller valgte denne brikkken:" + pieceIndex);
		if(positionIndex == -1){
			currentPiece = InternetConvert.getPieceFromNet(pieceIndex);
			return;
		}
		if(pieceIndex == -1){
			System.out.println("The internet won");
			return;
		}
		int i = (int) Math.floor(positionIndex/4.0);
		int j = positionIndex % 4;
		board.PlacePiece(currentPiece, i, j);
		currentPiece = InternetConvert.getPieceFromNet(pieceIndex);
	}
}
