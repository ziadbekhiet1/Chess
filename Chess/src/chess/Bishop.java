package chess;

public class Bishop extends ChessPiece {

	public Bishop(Space location, char color) { //color
		
		super(location, color);
	}
	
	public boolean move(Board chessboard, int destRow, int destCol) {
		
        Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        System.out.println("Piece color: " + this.getColor());

        int distanceRow = loc.getRow() - destRow; //x distance between
        int distanceCol = loc.getCol() - destCol; // y distance between
        if (!dest.getOccupied() || dest.getBW() != this.getColor()) {
            if (Math.abs(distanceRow) - Math.abs(distanceCol) == 0) {// y=x line so abs of both have to be 0
                return true;
            }
        }

        return false;
	}

}
