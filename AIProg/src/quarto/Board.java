package quarto;

import java.util.ArrayList;

public class Board {

	/** Ubrukte brikker */
	private ArrayList<Piece> Pieces;

	/** Brettet med brikker */
	private Piece[][] board;

	public Board() {
		board = new Piece[4][4];
		Pieces = new ArrayList<Piece>();
		// for (int i = 0; i < 16; i++) {
		// Piece p = new Piece(false, false, false, false);
		// }
		// Pieces.add(new Piece(false, false, false, false));
		// Pieces.add(new Piece(false, false, false, true));
		// Pieces.add(new Piece(false, false, true, false));
		// Pieces.add(new Piece(false, false, true, true));
		// Pieces.add(new Piece(false, true, false, false));
		// Pieces.add(new Piece(false, true, false, true));
		// Pieces.add(new Piece(false, true, true, false));
		// Pieces.add(new Piece(false, true, true, true));
		// Pieces.add(new Piece(true, false, false, false));
		// Pieces.add(new Piece(true, false, false, true));
		// Pieces.add(new Piece(true, false, true, false));
		// Pieces.add(new Piece(true, false, true, true));
		// Pieces.add(new Piece(true, true, false, false));
		// Pieces.add(new Piece(true, true, false, true));
		// Pieces.add(new Piece(true, true, true, false));
		// Pieces.add(new Piece(true, true, true, true));

		Pieces.add(new Piece(Color.RED, Height.SHORT, Shape.CIRCLE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.RED, Height.SHORT, Shape.CIRCLE,
				Solidity.SOLID));
		Pieces.add(new Piece(Color.RED, Height.SHORT, Shape.SQUARE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.RED, Height.SHORT, Shape.SQUARE,
				Solidity.SOLID));
		Pieces.add(new Piece(Color.RED, Height.TALL, Shape.CIRCLE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.RED, Height.TALL, Shape.CIRCLE,
				Solidity.SOLID));
		Pieces.add(new Piece(Color.RED, Height.TALL, Shape.SQUARE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.RED, Height.TALL, Shape.SQUARE,
				Solidity.SOLID));
		Pieces.add(new Piece(Color.BLUE, Height.SHORT, Shape.CIRCLE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.BLUE, Height.SHORT, Shape.CIRCLE,
				Solidity.SOLID));
		Pieces.add(new Piece(Color.BLUE, Height.SHORT, Shape.SQUARE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.BLUE, Height.SHORT, Shape.SQUARE,
				Solidity.SOLID));
		Pieces.add(new Piece(Color.BLUE, Height.TALL, Shape.CIRCLE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.BLUE, Height.TALL, Shape.CIRCLE,
				Solidity.SOLID));
		Pieces.add(new Piece(Color.BLUE, Height.TALL, Shape.SQUARE,
				Solidity.HOLLOW));
		Pieces.add(new Piece(Color.BLUE, Height.TALL, Shape.SQUARE,
				Solidity.SOLID));
	}

	public Board(ArrayList<Piece> pieces, Piece[][] board) {
		Pieces = pieces;
		this.board = board;
	}

	public void PlacePiece(Piece piece, int x, int y) {
		board[x][y] = piece;
	}

	public void RemovePieceFromPool(Piece piece) {
		Pieces.remove(piece);
	}

	public ArrayList<Piece> getPieces() {
		return Pieces;
	}

	public Piece[][] getBoard() {
		return board;
	}

	public void PrintBoard() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				String pieceString = "";
				// bruk system.print innenfor samme rad
				// Sjekk om feltet er tomt
				if (board[i][j] == null) {
					pieceString = "    ";
				} else {
					// if(board[i][j].isRed()) pieceString += "r";
					// else pieceString += "b";
					// if(board[i][j].isStar()) pieceString += "¤";
					// else pieceString += "#";
					// if(board[i][j].isBig()) pieceString.toUpperCase();
					// if(board[i][j].isBracket()) pieceString = "[" +
					// pieceString + "]";
					// else pieceString = "(" + pieceString + ")";

					if (board[i][j].color == Color.RED)
						pieceString += "r";
					else
						pieceString += "b";
					if (board[i][j].solidity == Solidity.HOLLOW)
						pieceString += "¤";
					else
						pieceString += "#";
					if (board[i][j].height == Height.TALL)
						pieceString = pieceString.toUpperCase();
					if (board[i][j].shape == Shape.SQUARE)
						pieceString = "[" + pieceString + "]";
					else
						pieceString = "(" + pieceString + ")";
				}
				System.out.print(pieceString);
			}
			System.out.println();
		}
	}

}
