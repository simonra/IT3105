package quarto;

public class Piece {

	public Color color;
	public Height height;
	public Shape shape;
	public Solidity solidity;

	private boolean red;
	private boolean big;
	private boolean bracket;
	private boolean star;

	public Piece(boolean red, boolean big, boolean bracket, boolean star) {
		this.red = red;
		this.big = big;
		this.bracket = bracket;
		this.star = star;
	}

	public Piece(Color color, Height height, Shape shape, Solidity solidity) {
		this.color = color;
		this.height = height;
		this.shape = shape;
		this.solidity = solidity;
	}

	public boolean isRed() {
		return red;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public boolean isBig() {
		return big;
	}

	public void setBig(boolean big) {
		this.big = big;
	}

	public boolean isBracket() {
		return bracket;
	}

	public void setBracket(boolean bracket) {
		this.bracket = bracket;
	}

	public boolean isStar() {
		return star;
	}

	public void setStar(boolean star) {
		this.star = star;
	}

	public String pieceString() {
		String pieceString = "";
		if (color == Color.RED)
			pieceString += "r";
		else
			pieceString += "b";
		if (solidity == Solidity.HOLLOW)
			pieceString += "¤";
		else
			pieceString += "#";
		if (height == Height.TALL)
			pieceString = pieceString.toUpperCase();
		if (shape == Shape.SQUARE)
			pieceString = "[" + pieceString + "]";
		else
			pieceString = "(" + pieceString + ")";
		return pieceString;
	}

}
