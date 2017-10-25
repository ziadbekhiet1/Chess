package chess;

public class Rook extends ChessPiece {

	public Rook(Space location, char color) {
		
		super(location, color);
	}
	
	public boolean move(Board chessboard, int destRow, int destCol) {
		
        Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        System.out.println("Piece color: " + this.getColor());
        int locRow = loc.getRow(); //x distance between
        int locCol = loc.getCol(); // y distance between
        if (!dest.getOccupied() || dest.getBW() != this.getColor()) {
            if (locRow == destRow || locCol == destCol) {
                return true;
            }
        }

        return false;
	}
}
