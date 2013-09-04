package quarto;

import java.util.ArrayList;

public class Logic {

	// Sjekker om spillet er vunnet
	public boolean isWon(Board board) {
		// Sjekker rader:
		if (comparePieces(board.getBoard()[0][0], board.getBoard()[0][1],
				board.getBoard()[0][2], board.getBoard()[0][3]))
			return true;
		else if (comparePieces(board.getBoard()[1][0], board.getBoard()[1][1],
				board.getBoard()[1][2], board.getBoard()[1][3]))
			return true;
		else if (comparePieces(board.getBoard()[2][0], board.getBoard()[2][1],
				board.getBoard()[2][2], board.getBoard()[2][3]))
			return true;
		else if (comparePieces(board.getBoard()[3][0], board.getBoard()[3][1],
				board.getBoard()[3][2], board.getBoard()[3][3]))
			return true;

		// Sjekker kolonner:
		if (comparePieces(board.getBoard()[0][0], board.getBoard()[1][0],
				board.getBoard()[2][0], board.getBoard()[3][0]))
			return true;
		else if (comparePieces(board.getBoard()[0][1], board.getBoard()[1][1],
				board.getBoard()[2][1], board.getBoard()[3][1]))
			return true;
		else if (comparePieces(board.getBoard()[0][2], board.getBoard()[1][2],
				board.getBoard()[2][2], board.getBoard()[3][2]))
			return true;
		else if (comparePieces(board.getBoard()[0][3], board.getBoard()[1][3],
				board.getBoard()[2][3], board.getBoard()[3][3]))
			return true;

		// Sjekker diagonaler:
		if (comparePieces(board.getBoard()[0][0], board.getBoard()[1][1],
				board.getBoard()[2][2], board.getBoard()[3][3]))
			return true;
		else if (comparePieces(board.getBoard()[3][0], board.getBoard()[2][1],
				board.getBoard()[1][2], board.getBoard()[0][3]))
			return true;

		// Hvis det ikke var noen vinning-condittions oppfylt:
		return false;
	}

	// Sjekker om 4 pieces er like
	public boolean comparePieces(Piece p1, Piece p2, Piece p3, Piece p4) {
		if (p1 == null || p2 == null || p3 == null || p4 == null)
			return false;
		if (p1.color == p2.color && p2.color == p3.color
				&& p3.color == p4.color)
			return true;
		else if (p1.height == p2.height && p2.height == p3.height
				&& p3.height == p4.height)
			return true;
		else if (p1.shape == p2.shape && p2.shape == p3.shape
				&& p3.shape == p4.shape)
			return true;
		else if (p1.solidity == p2.solidity && p2.solidity == p3.solidity
				&& p3.solidity == p4.solidity)
			return true;
		else
			return false;
	}

	// Sjekker om tre pieces er like (til bruk i heurestikk)
	public boolean comparePieces(Piece p1, Piece p2, Piece p3) {
		if (p1 == null || p2 == null || p3 == null)
			return false;
		if (p1.color == p2.color && p2.color == p3.color)
			return true;
		else if (p1.height == p2.height && p2.height == p3.height)
			return true;
		else if (p1.shape == p2.shape && p2.shape == p3.shape)
			return true;
		else if (p1.solidity == p2.solidity && p2.solidity == p3.solidity)
			return true;
		else
			return false;
	}

	public ArrayList<Enum> CommonFeatures(Piece p1, Piece p2, Piece p3) {
		ArrayList<Enum> enumsToReturn = new ArrayList<Enum>();
		if (p1 == null || p2 == null || p3 == null)
			return null;
		if (p1.color == p2.color && p2.color == p3.color)
			enumsToReturn.add(p1.color);
		if (p1.height == p2.height && p2.height == p3.height)
			enumsToReturn.add(p1.height);
		if (p1.shape == p2.shape && p2.shape == p3.shape)
			enumsToReturn.add(p1.shape);
		if (p1.solidity == p2.solidity && p2.solidity == p3.solidity)
			enumsToReturn.add(p1.solidity);
		return enumsToReturn;
	}

	// Sjekker om 2Pieces er like (til bruk i heurestikk)
	public boolean comparePieces(Piece p1, Piece p2) {
		if (p1 == null || p2 == null)
			return false;
		if (p1.color == p2.color)
			return true;
		else if (p1.height == p2.height)
			return true;
		else if (p1.shape == p2.shape)
			return true;
		else if (p1.solidity == p2.solidity)
			return true;
		else
			return false;
	}

	// TODO: Metode som finner seire i neste trekk (i.e. hvilke brikker
	// motstanderen for enhver pris ikke må få neste trekk) (Burkes til
	// novice-heurestikken)
	public ArrayList<Piece> PiecesThatWinOnNextMove(Board board) {
		/**
		 * Arraylisten som inneholder brikkene som ikke kan gies til den andre
		 * siden.
		 */
		ArrayList<Piece> piecesThatShouldntBeHandedOver = new ArrayList<Piece>();

		// Sjekk hver rad for om den inneholder 3 pieces
		for (int i = 0; i < board.getBoard().length; i++) {

			ArrayList<Piece> tempList = new ArrayList<Piece>();
			if (board.getBoard()[i][0] != null)
				tempList.add(board.getBoard()[i][0]);
			if (board.getBoard()[i][1] != null)
				tempList.add(board.getBoard()[i][1]);
			if (board.getBoard()[i][2] != null)
				tempList.add(board.getBoard()[i][2]);
			if (board.getBoard()[i][3] != null)
				tempList.add(board.getBoard()[i][3]);

			if (tempList.size() == 3) {
				ArrayList<Enum> features = CommonFeatures(tempList.get(0),
						tempList.get(1), tempList.get(2));
				// ^få den til å returnere hva som matchet
				// nedenfor: søk igjennom brikker som er igjenn for å se hva det
				// er de matcher
				for (Piece p : board.getPieces()) {
					if (features.contains(p.color))
						piecesThatShouldntBeHandedOver.add(p);
					else if (features.contains(p.height))
						piecesThatShouldntBeHandedOver.add(p);
					else if (features.contains(p.shape))
						piecesThatShouldntBeHandedOver.add(p);
					else if (features.contains(p.solidity))
						piecesThatShouldntBeHandedOver.add(p);
				}
			}
		}

		// Sjekk hver kolonne for om den inneholder 3 pieces
		// Hvis så, finn om det finnes en gjennværende piece som ikke kan handes
		// over
		for (int i = 0; i < board.getBoard().length; i++) {

			ArrayList<Piece> tempList = new ArrayList<Piece>();
			if (board.getBoard()[0][i] != null)
				tempList.add(board.getBoard()[0][i]);
			if (board.getBoard()[1][i] != null)
				tempList.add(board.getBoard()[1][i]);
			if (board.getBoard()[2][i] != null)
				tempList.add(board.getBoard()[2][i]);
			if (board.getBoard()[3][i] != null)
				tempList.add(board.getBoard()[3][i]);

			if (tempList.size() == 3) {
				ArrayList<Enum> features = CommonFeatures(tempList.get(0),
						tempList.get(1), tempList.get(2));
				// ^få den til å returnere hva som matchet
				// nedenfor: søk igjennom brikker som er igjenn for å se hva det
				// er de matcher
				for (Piece p : board.getPieces()) {
					if (features.contains(p.color))
						piecesThatShouldntBeHandedOver.add(p);
					else if (features.contains(p.height))
						piecesThatShouldntBeHandedOver.add(p);
					else if (features.contains(p.shape))
						piecesThatShouldntBeHandedOver.add(p);
					else if (features.contains(p.solidity))
						piecesThatShouldntBeHandedOver.add(p);
				}
			}
		}

		// Sjekk hver diagonal for om den inneholder 3 pieces
		// Hvis så, finn om det finnes en gjennværende piece som ikke kan handes
		// over
		ArrayList<Piece> tempList = new ArrayList<Piece>();
		if (board.getBoard()[0][0] != null)
			tempList.add(board.getBoard()[0][0]);
		if (board.getBoard()[1][1] != null)
			tempList.add(board.getBoard()[1][1]);
		if (board.getBoard()[2][2] != null)
			tempList.add(board.getBoard()[2][2]);
		if (board.getBoard()[3][3] != null)
			tempList.add(board.getBoard()[3][3]);

		if (tempList.size() == 3) {
			ArrayList<Enum> features = CommonFeatures(tempList.get(0),
					tempList.get(1), tempList.get(2));
			// ^få den til å returnere hva som matchet
			// nedenfor: søk igjennom brikker som er igjenn for å se hva det er
			// de matcher
			for (Piece p : board.getPieces()) {
				if (features.contains(p.color))
					piecesThatShouldntBeHandedOver.add(p);
				else if (features.contains(p.height))
					piecesThatShouldntBeHandedOver.add(p);
				else if (features.contains(p.shape))
					piecesThatShouldntBeHandedOver.add(p);
				else if (features.contains(p.solidity))
					piecesThatShouldntBeHandedOver.add(p);
			}
		}

		tempList = new ArrayList<Piece>();
		if (board.getBoard()[0][0] != null)
			tempList.add(board.getBoard()[0][3]);
		if (board.getBoard()[1][1] != null)
			tempList.add(board.getBoard()[1][2]);
		if (board.getBoard()[2][2] != null)
			tempList.add(board.getBoard()[2][1]);
		if (board.getBoard()[3][3] != null)
			tempList.add(board.getBoard()[3][0]);

		if (tempList.size() == 3) {
			ArrayList<Enum> features = new ArrayList<Enum>();
			features = CommonFeatures(tempList.get(0), tempList.get(1),
					tempList.get(2));
			// ^få den til å returnere hva som matchet
			// nedenfor: søk igjennom brikker som er igjenn for å se hva det er
			// de matcher
			for (Piece p : board.getPieces()) {
				if (features.contains(p.color))
					piecesThatShouldntBeHandedOver.add(p);
				else if (features.contains(p.height))
					piecesThatShouldntBeHandedOver.add(p);
				else if (features.contains(p.shape))
					piecesThatShouldntBeHandedOver.add(p);
				else if (features.contains(p.solidity))
					piecesThatShouldntBeHandedOver.add(p);
			}
		}

		if (piecesThatShouldntBeHandedOver.size() > 0)
			return piecesThatShouldntBeHandedOver;
		else
			return null;
	}
}
