package quarto;

import java.util.ArrayList;

public class Board {
	private ArrayList<Piece> Pieces;
	private Piece[][] board;
	
	public Board(){
		board = new Piece[4][4];
		Pieces = new ArrayList<Piece>();
//		for (int i = 0; i < 16; i++) {
//			Piece p = new Piece(false, false, false, false);
//		}
		Pieces.add(new Piece(false, false, false, false));
		Pieces.add(new Piece(false, false, false, true));
		Pieces.add(new Piece(false, false, true, false));
		Pieces.add(new Piece(false, false, true, true));
		Pieces.add(new Piece(false, true, false, false));
		Pieces.add(new Piece(false, true, false, true));
		Pieces.add(new Piece(false, true, true, false));
		Pieces.add(new Piece(false, true, true, true));
		Pieces.add(new Piece(true, false, false, false));
		Pieces.add(new Piece(true, false, false, true));
		Pieces.add(new Piece(true, false, true, false));
		Pieces.add(new Piece(true, false, true, true));
		Pieces.add(new Piece(true, true, false, false));
		Pieces.add(new Piece(true, true, false, true));
		Pieces.add(new Piece(true, true, true, false));
		Pieces.add(new Piece(true, true, true, true));
	}
	
	public Board(ArrayList<Piece> pieces, Piece[][] board) {
		Pieces = pieces;
		this.board = board;
	}
	
	public void PlacePiece(Piece piece, int x, int y){
		board[x][y]	= piece;	
	}
	
	public void PrintBoard(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				String pieceString = "";
				//bruk system.print innenfor samme rad
				//Sjekk om feltet er tomt
				if(board[i][j] == null){
					pieceString = "    ";
				}else{
					if(board[i][j].isRed()) pieceString += "r";
					else pieceString += "b";
					if(board[i][j].isStar()) pieceString += "¤";
					else pieceString += "#";
					if(board[i][j].isBig()) pieceString.toUpperCase();
					if(board[i][j].isBracket()) pieceString = "[" + pieceString + "]";
					else pieceString = "(" + pieceString + ")";
				}
			}
			System.out.println();
		}
	}
	
}






























