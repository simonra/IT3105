package quarto;

public class HumanPlayer implements Player {

	UI ui = new UI();

	public void placePiece(Board board, Piece currentPiece) {
		String xy = ui.readCoordinates();
		board.PlacePiece(currentPiece, Integer.parseInt("" + xy.charAt(0)),
				Integer.parseInt("" + xy.charAt(1)));
	}

	@Override
	public int selectPiece(Board board) {
		return ui.selectPiece();
	}

}
