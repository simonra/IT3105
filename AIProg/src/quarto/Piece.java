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
}
