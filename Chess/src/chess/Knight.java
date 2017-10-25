package chess;

public class Knight extends ChessPiece {

	public Knight(Space location, char color) {
		
		super(location, color);
	}
	
	public boolean move(Board chessboard, int destRow, int destCol) {
		
        Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        System.out.println("Piece color: " + this.getColor());

        int distanceRow = loc.getRow() - destRow; //x distance between
        int distanceCol = loc.getCol() - destCol;
        int locRow = loc.getRow(); //x distance between
        int locCol = loc.getCol(); // y distance between
        if (!dest.getOccupied() || dest.getBW() != this.getColor()) {
            if (Math.abs(distanceRow) + Math.abs(distanceCol) == 3 && locRow != destRow || locCol != destCol) {
                return true;
            }
        }
        return false;
	}
}
