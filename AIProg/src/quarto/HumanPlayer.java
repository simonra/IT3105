package quarto;

public class HumanPlayer implements Player {

	UI ui = new UI();

	public void placePiece() {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectPiece(Board board) {
		return ui.selectPiece();
	}

}
