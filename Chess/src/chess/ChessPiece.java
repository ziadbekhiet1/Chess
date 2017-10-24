package chess;

public abstract class ChessPiece {

	private char color;
	private Space location;
	
	public ChessPiece(Space location, char color) {
		
		this.location = location;
		this.color = color;
	}
	
	public char getColor() {
		
		return this.color;
	}
	
	public Space getLocation() {
		
		return this.location;
	}
	
	public void setLocation(Space location) {
		
		this.location = location;
	}
	
	public abstract boolean move(int destRow, int destCol);
}
