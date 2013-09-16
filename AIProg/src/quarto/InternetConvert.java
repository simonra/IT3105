package quarto;

public class InternetConvert {
	public static int selectPosToNetCommand(Move move) {
		String s = "" + move.x + move.y;
		return Integer.parseInt(s);
	}

	public static int selectPieceToNetCommand(Piece piece) {
		Piece p = piece;
		switch (p.pieceString()) {
		case "(r#)":
			return 0;
		case "(r¤)":
			return 1;
		case "(R#)":
			return 2;
		case "(R¤)":
			return 3;
		case "[r#]":
			return 4;
		case "[r¤]":
			return 5;
		case "[R#]":
			return 6;
		case "[R¤]":
			return 7;
		case "(b#)":
			return 8;
		case "(b¤)":
			return 9;
		case "(B#)":
			return 10;
		case "(B¤)":
			return 11;
		case "[b#]":
			return 12;
		case "[b¤]":
			return 13;
		case "[B#]":
			return 14;
		case "[B¤]":
			return 15;
		default:
			break;
		}
		return 0;
	}

	public static int getXY(int positionIndex, int pos) {
		String s = "" + positionIndex;
		System.out.println(s);
		return Integer.parseInt("" + s.charAt(pos));
	}

	public static Piece getPieceFromNet(int pieceInt) {
		Piece p;
		switch (pieceInt) {
		case 0:
			return p = new Piece(Color.RED, Height.SHORT, Shape.CIRCLE,
					Solidity.SOLID);
		case 1:
			return p = new Piece(Color.RED, Height.SHORT, Shape.CIRCLE,
					Solidity.HOLLOW);
		case 2:
			return p = new Piece(Color.RED, Height.TALL, Shape.CIRCLE,
					Solidity.SOLID);
		case 3:
			return p = new Piece(Color.RED, Height.TALL, Shape.CIRCLE,
					Solidity.HOLLOW);
		case 4:
			return p = new Piece(Color.RED, Height.SHORT, Shape.SQUARE,
					Solidity.SOLID);
		case 5:
			return p = new Piece(Color.RED, Height.SHORT, Shape.SQUARE,
					Solidity.HOLLOW);
		case 6:
			return p = new Piece(Color.RED, Height.TALL, Shape.SQUARE,
					Solidity.SOLID);
		case 7:
			return p = new Piece(Color.RED, Height.TALL, Shape.SQUARE,
					Solidity.HOLLOW);
		case 8:
			return p = new Piece(Color.BLUE, Height.SHORT, Shape.CIRCLE,
					Solidity.SOLID);
		case 9:
			return p = new Piece(Color.BLUE, Height.SHORT, Shape.CIRCLE,
					Solidity.HOLLOW);
		case 10:
			return p = new Piece(Color.BLUE, Height.TALL, Shape.CIRCLE,
					Solidity.SOLID);
		case 11:
			return p = new Piece(Color.BLUE, Height.TALL, Shape.CIRCLE,
					Solidity.HOLLOW);
		case 12:
			return p = new Piece(Color.BLUE, Height.SHORT, Shape.SQUARE,
					Solidity.SOLID);
		case 13:
			return p = new Piece(Color.BLUE, Height.SHORT, Shape.SQUARE,
					Solidity.HOLLOW);
		case 14:
			return p = new Piece(Color.BLUE, Height.TALL, Shape.SQUARE,
					Solidity.SOLID);
		case 15:
			return p = new Piece(Color.BLUE, Height.TALL, Shape.SQUARE,
					Solidity.HOLLOW);
		default:
			return null;
		}
	}
}
