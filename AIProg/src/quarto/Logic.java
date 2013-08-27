package quarto;

public class Logic {
	
	//Sjekker om spillet er vunnet
	public boolean isWon(Board board){
		//Sjekker rader:
		if(comparePieces(board.getBoard()[0][0], board.getBoard()[0][1], board.getBoard()[0][2], board.getBoard()[0][3]))
			return true;
		else if(comparePieces(board.getBoard()[1][0], board.getBoard()[1][1], board.getBoard()[1][2], board.getBoard()[1][3]))
			return true;
		else if(comparePieces(board.getBoard()[2][0], board.getBoard()[2][1], board.getBoard()[2][2], board.getBoard()[2][3]))
			return true;
		else if(comparePieces(board.getBoard()[3][0], board.getBoard()[3][1], board.getBoard()[3][2], board.getBoard()[3][3]))
			return true;
		
		//Sjekker kolonner:
		if(comparePieces(board.getBoard()[0][0], board.getBoard()[1][0], board.getBoard()[2][0], board.getBoard()[3][0]))
			return true;
		else if(comparePieces(board.getBoard()[0][1], board.getBoard()[1][1], board.getBoard()[2][1], board.getBoard()[3][1]))
			return true;
		else if(comparePieces(board.getBoard()[0][2], board.getBoard()[1][2], board.getBoard()[2][2], board.getBoard()[3][2]))
			return true;
		else if(comparePieces(board.getBoard()[0][3], board.getBoard()[1][3], board.getBoard()[2][3], board.getBoard()[3][3]))
			return true;
		
		//Sjekker diagonaler:
		if(comparePieces(board.getBoard()[0][0], board.getBoard()[1][1], board.getBoard()[2][2], board.getBoard()[3][3]))
			return true;
		else if(comparePieces(board.getBoard()[3][0], board.getBoard()[2][1], board.getBoard()[1][2], board.getBoard()[0][3]))
			return true;
		
		//Hvis det ikke var noen vinning-condittions oppfylt:
		return false;
	}
	
	//Sjekker om 4 pieces er like
	public boolean comparePieces(Piece p1, Piece p2, Piece p3, Piece p4){
		if(p1.color == p2.color && p2.color == p3.color && p3.color == p4.color)
			return true;
		else if(p1.height == p2.height && p2.height == p3.height && p3.height == p4.height)
			return true;
		else if(p1.shape == p2.shape && p2.shape == p3.shape && p3.shape== p4.shape)
			return true;
		else if(p1.solidity == p2.solidity && p2.solidity == p3.solidity && p3.solidity == p4.solidity)
			return true;
		else
			return false;
	}
	
	//Sjekker om tre pieces er like (til bruk i heurestikk)
	public boolean comparePieces(Piece p1, Piece p2, Piece p3){
		if(p1.color == p2.color && p2.color == p3.color)
			return true;
		else if(p1.height == p2.height && p2.height == p3.height)
			return true;
		else if(p1.shape == p2.shape && p2.shape == p3.shape)
			return true;
		else if(p1.solidity == p2.solidity && p2.solidity == p3.solidity)
			return true;
		else
			return false;
	}
	
	//Sjekker om 2Pieces er like (til bruk i heurestikk)
	public boolean comparePieces(Piece p1, Piece p2){
		if(p1.color == p2.color)
			return true;
		else if(p1.height == p2.height)
			return true;
		else if(p1.shape == p2.shape)
			return true;
		else if(p1.solidity == p2.solidity)
			return true;
		else
			return false;
	}
	
	
}
