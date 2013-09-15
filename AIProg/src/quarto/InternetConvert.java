package quarto;

public class InternetConvert {
	public static int selectPosToNetCommand(Move move) {
		String s = "" + move.x + move.y;
		return Integer.parseInt(s);
	}

	public static int selectPieceToNetCommand(Move move) {
		Piece p = move.givePiece;
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
}
