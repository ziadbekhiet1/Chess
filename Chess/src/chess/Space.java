package chess;

public class Space {

	private char bw;
	private boolean occupied;
	
	private ChessPiece piece;
	
	public Space (char bw) {
		
		this.bw = bw;
	}
	
	// getters & setters
	
	public char getBW() {
		
		return this.bw;
	}
	
	public boolean getOccupied() {
		
		return this.occupied;
	}
	
	public ChessPiece getPiece() {
		
		return this.piece;
	}
	
	public void setBW(char bw) {
		
		this.bw = bw;
	}
	
	public void setOccupied(boolean occupied) {
		
		this.occupied = occupied;
	}
	
	public void setPiece(ChessPiece piece) {
		
		this.piece = piece;
	}
}
