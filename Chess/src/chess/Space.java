package chess;

public class Space {

	private char bw;
	private boolean occupied;
	
	private ChessPiece piece;
	
	private int row, col;
	
	public Space (int row, int col, char bw) {
		
		this.row = row;
		this.col = col;
		this.bw = bw;
	}
	
	// getters & setters
	
	public int getRow() {
		
		return this.row;
	}
	
	public int getCol() {
		
		return this.col;
	}
	
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
