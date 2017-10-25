package chess;

public class King extends ChessPiece {

	public King(Space location, char color) {
		
		super(location, color);
	}
	
	public boolean move(Board chessboard, int destRow, int destCol) {
		
		Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        System.out.println("Piece color: " + this.getColor());

        int distanceRow = loc.getRow() - destRow; //x distance between
        int distanceCol = loc.getCol() - destCol; // y distance between
        if (distanceRow == 0 || distanceRow == 1) {
            if (distanceCol == 0 || distanceCol == 1) {
                return true;
            }
        }
        return false;
	}
}
