package quarto;

public class NewNode {
	public NewNode childNode;
	public boolean isMaximizingNode;
	public int depth;
	public Board board;
	public Move move;
	public boolean isTerminal;
	public Move lastMoveUsedToGenerateAChild;
	int lastNullIndex = 0;

	/*
	 * Last move used to generate a child: currentpiece is the piece the child
	 * places, givePiece is the piece it in turn is supposed to hand its
	 * children.
	 */

	public NewNode(NewNode childNode, boolean isMax, int depth, Board board,
			Move move) {
		this.childNode = childNode;
		this.isMaximizingNode = isMax;
		this.depth = depth;
		this.board = new Board(board);
		this.move = move;
		this.lastMoveUsedToGenerateAChild = null;
		this.lastNullIndex = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(this.board.getBoard()[i][j] == null)
					this.lastNullIndex = i + 4 * j;
			}
		}
		terminalCheck();
	}

	private void terminalCheck() {
		if(this.board.getPieces().size() == 0)
			this.isTerminal = true;
		else if(Logic.isWon(this.board))
			this.isTerminal = true;
	}

	/**
	 * Makes a taken node equal to this node, nullifies its choice of current
	 * and next child
	 */
	public void assimilate(NewNode node) {
		node.isMaximizingNode = this.isMaximizingNode;
		node.depth = this.depth;
		node.copyBoard(this.board);
		node.lastNullIndex = this.lastNullIndex;
		node.clearMove(move);
		node.clearMove(lastMoveUsedToGenerateAChild);
	}

	private void clearMove(Move move) {
		move.currentPiece = null;
		move.x = -1;
		move.y = -1;
		move.givePiece = null;
	}

	private void copyMove(Move original, Move copy) {
		copy.currentPiece = original.currentPiece;
		copy.givePiece = original.givePiece;
		copy.x = original.x;
		copy.y = original.y;
	}

	/** Makes this nodes board a copy of the supplied board */
	private void copyBoard(Board board2) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.board.getBoard()[i][j] = board2.getBoard()[i][j];
			}
		}
		this.board.getPieces().clear();
		for (Piece p : board2.getPieces()) {
			this.board.getPieces().add(p);
		}

	}

	/**
	 * Sees if this node has a next child, based on that we iterate forward
	 * through free pieces
	 */
	public boolean hasNextChild() {
		// TODO: Må fiskes slik at current = siste OG give = nest siste
		
		if(this.isTerminal)
			return false;
		
		
		if(lastNullIndex == this.lastMoveUsedToGenerateAChild.x + 4 * this.lastMoveUsedToGenerateAChild.x)
			return false;
		return true;
	}

	/** Selects the new piece(s) for lastMoveUsedToGenerateAChils */
	public void selectNextPieces() {

		// If this is a brand new node or we've reached a new square
		if (lastMoveUsedToGenerateAChild.currentPiece == null) {
			lastMoveUsedToGenerateAChild.currentPiece = this.move.givePiece;
			if (this.board.getPieces().indexOf(this.move.givePiece) == 0)
				lastMoveUsedToGenerateAChild.givePiece = this.board.getPieces()
						.get(1);
			else
				lastMoveUsedToGenerateAChild.givePiece = this.board.getPieces()
						.get(0);
		}

		// Piece to hand over for giving:
		// Checks whether the next piece's still within
		if (this.board.getPieces().indexOf(
				lastMoveUsedToGenerateAChild.givePiece) + 1 < this.board
				.getPieces().size() - 1) {
			// if it's not the same update it and return
			if (this.board.getPieces().indexOf(
					lastMoveUsedToGenerateAChild.givePiece) + 1 != this.board
					.getPieces().indexOf(
							lastMoveUsedToGenerateAChild.currentPiece)) {
				lastMoveUsedToGenerateAChild.givePiece = this.board.getPieces()
						.get(this.board.getPieces().indexOf(
								lastMoveUsedToGenerateAChild.givePiece) + 1);
				return;
			}
			// else check whether the next ones within, update it, and return
			else if (this.board.getPieces().indexOf(
					lastMoveUsedToGenerateAChild.givePiece) + 2 != this.board
					.getPieces().size() - 1) {
				lastMoveUsedToGenerateAChild.givePiece = this.board.getPieces()
						.get(this.board.getPieces().indexOf(
								lastMoveUsedToGenerateAChild.givePiece) + 2);
				return;
			}

		}

	}

	/** Generates the next child */
	public NewNode getNextChild(NewNode node) {
		assimilate(node);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4.; j++) {
				// Checks usability of tile
				if (i + 4 * j >= lastMoveUsedToGenerateAChild.x + 4
						* lastMoveUsedToGenerateAChild.y) {
					if (this.board.getBoard()[i][j] == null) {
						// Checks whether we've exhausted this tile
						// The last give we iterate through
						if (this.board.getPieces().indexOf(
								lastMoveUsedToGenerateAChild.givePiece) == this.board
								.getPieces().size() - 1) {
							lastMoveUsedToGenerateAChild.currentPiece = null;
							selectNextPieces();
							lastMoveUsedToGenerateAChild.x = i;
							lastMoveUsedToGenerateAChild.y = j;
							node.board.PlacePiece(lastMoveUsedToGenerateAChild);
							node.depth = this.depth + 1;
							node.isMaximizingNode = !this.isMaximizingNode;
							copyMove(lastMoveUsedToGenerateAChild, node.move);
							for (int k = 0; i < 4; i++) {
								for (int l = 0; j < 4; j++) {
									if(this.board.getBoard()[k][l] == null)
										node.lastNullIndex = k + 4 * l;
								}
							}
							node.terminalCheck();
							return node;
						}
						// If we haven't continues exploiting this square
						selectNextPieces();
						node.board.PlacePiece(lastMoveUsedToGenerateAChild);
						node.depth = this.depth + 1;
						node.isMaximizingNode = !this.isMaximizingNode;
						copyMove(lastMoveUsedToGenerateAChild, node.move);
						return node;
					}
				}
			}
		}

		return null;
	}

	public double getHeuristic() {
		// TODO Auto-generated method stub
		if(isTerminal)
			return 100;
		int certainThreeInARow = 0;
		for (int i = 0; i < 4; i++) {
			if (board.getBoard()[0][i] == null ^ board.getBoard()[1][i] == null
					^ board.getBoard()[2][i] == null
					^ board.getBoard()[3][i] == null)
				certainThreeInARow++;
		}

		for (int i = 0; i < 4; i++) {
			if (board.getBoard()[i][0] == null ^ board.getBoard()[i][1] == null
					^ board.getBoard()[i][2] == null
					^ board.getBoard()[i][3] == null)
				certainThreeInARow++;
		}

		if (board.getBoard()[0][0] == null ^ board.getBoard()[1][1] == null
				^ board.getBoard()[2][2] == null
				^ board.getBoard()[3][3] == null)
			certainThreeInARow++;

		if (board.getBoard()[3][0] == null ^ board.getBoard()[2][1] == null
				^ board.getBoard()[1][2] == null
				^ board.getBoard()[0][3] == null)
			certainThreeInARow++;

		return certainThreeInARow * 10;
	}
}

















