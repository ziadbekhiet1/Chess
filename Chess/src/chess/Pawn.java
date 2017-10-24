package chess;

public class Pawn extends ChessPiece {

	public Pawn(Space location, char color) {
		
		super(location, color);
	}
	
	public boolean move(Board chessboard, int destRow, int destCol) {
		
		Space loc = this.getLocation();
		Space dest = chessboard.getSpace(destRow, destCol);
		
		System.out.println("Piece color: " + this.getColor());
		
		if(this.getColor() == 'w') {
			
			// white piece
			
			// check basic pawn forward movement constraints
			if(destRow != loc.getRow() - 1 || destRow != loc.getRow() -2) {
				
				System.out.println("ERROR: Pawn can't move more than 2 spaces forward.");
				return false;
			}
			
		}
		else {
			
			// black piece
		}
		
		return false;
	}
}
