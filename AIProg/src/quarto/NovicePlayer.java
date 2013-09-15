package quarto;

import java.util.ArrayList;

public class NovicePlayer implements Player {
	private String name = "novice";

	@Override
	// Prøver å finne et vinnende trekk
	public void placePiece(Board board, Piece currentPiece) {
		Board tempBoard = new Board(board);

		// Sjekker om det finnes et trekk hvor man vinner
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board.getBoard()[i][j] == null) {
					tempBoard.PlacePiece(currentPiece, i, j);
					if (Logic.isWon(tempBoard)) {
						board.PlacePiece(currentPiece, i, j);
						return;
					}
					tempBoard.getBoard()[i][j] = null;
					tempBoard.getPieces().add(currentPiece);
				}
			}
		}

		// Hvis det ikke finnes en trekk som vinner, gjør et random trekk
		while (true) {
			int x = (int) (Math.floor(Math.random() * 4));
			int y = (int) (Math.floor(Math.random() * 4));
			if (board.getBoard()[x][y] == null) {
				board.PlacePiece(currentPiece, x, y);
				return;
			}
		}
	}

	@Override
	public int selectPiece(Board board) {
		ArrayList<Piece> keepPieces = Logic.PiecesThatWinOnNextMove(board);

		for (Piece piece : board.getPieces()) {
			if (keepPieces != null && !keepPieces.contains(piece)) {
				return board.getPieces().indexOf(piece);
			}
		}

		return (int) Math.floor(Math.random() * board.getPieces().size());
	}

	@Override
	public String getName() {
		return name;
	}
}
