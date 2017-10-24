package chess;

public abstract class ChessPiece {

	private char color;
	
	public ChessPiece(char color) {
		
		this.color = color;
	}
	
	public char getColor() {
		
		return this.color;
	}
	
	public abstract boolean move(int destRow, int destCol);
}
