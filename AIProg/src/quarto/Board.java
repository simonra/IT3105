package quarto;

import java.util.ArrayList;

public class Board {
	private ArrayList<Piece> Pieces;
	private Piece[][] board;
	
	public Board(ArrayList<Piece> pieces, Piece[][] board) {
		Pieces = pieces;
		this.board = board;
	}
	
	public void PlacePiece(Piece piece, int x, int y){
		board[x][y]	= piece;	
	}
}
