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

	public Board(Board board) {
		// Kopierer listen over pieces
		Pieces = new ArrayList<Piece>();

		for (Piece piece : board.getPieces()) {
			Pieces.add(piece);
		}

		// Kopierer brettet
		this.board = new Piece[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.board[i][j] = board.getBoard()[i][j];
			}
		}
	}

	public Board(ArrayList<Piece> pieces, Piece[][] board) {
		Pieces = pieces;
		this.board = board;
	}

	public void PlacePiece(Piece piece, int i, int j) {
		board[i][j] = piece;
	}

	public void PlacePiece(Move move) {
		PlacePiece(move.currentPiece, move.y, move.x);
		RemovePieceFromPool(move.currentPiece);
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

	public void setPieces(ArrayList<Piece> pieces) {
		this.Pieces = new ArrayList<>();
		for (Piece piece : pieces) {
			this.Pieces.add(piece);
		}
	}

	public void PrintBoard() {
		System.out.println();
		System.out.println("==================");
		for (int i = 0; i < 4; i++) {
			System.out.print("|");
			for (int j = 0; j < 4; j++) {
				String pieceString = "";
				// bruk system.print innenfor samme rad
				// Sjekk om feltet er tomt
				if (board[i][j] == null) {
					pieceString = "    ";
				} else {
					pieceString = board[i][j].pieceString();
				}
				System.out.print(pieceString);
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println("==================");
	}

	public void printPieces() {
		for (Piece piece : Pieces) {
			System.out.print(piece.pieceString() + "  ");
		}
	}

	public Piece selectPiece(int pos) {
		Piece p = Pieces.get(pos);
		RemovePieceFromPool(p);
		return p;
	}
}
