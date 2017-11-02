package chess;

/**
 * Represents a standard chess king.
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public class King extends ChessPiece {

	/**
	 * Shows if the King has been moved
	 */
    private int moved;
    
    /**
     * Represents the king's last move
     */
    private int lastmove;
    
    /**
   	 * Creates a new instance of the piece
   	 * 
   	 * @param location the space where the piece is currently located
   	 * @param color the color of the piece
   	 */
    public King(Space location, char color) {
        super(location, color);
        lastmove = 0;
        moved = 0;
    }

    /**
     * The move method attempts to move the piece to a new location, based
     * on the specific piece movement behaviors. 
     * 
     * @param  chessboard  an instance of the chessboard at the given time of the move
     * @param  destRow the row number of the destination space
     * @param  destCol the column number of the destination space
     * @return      true if the move is possible, false if the move is not possible
     */
    public boolean move(Board chessboard, int destRow, int destCol) {

        Space start = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        //System.out.println("Piece color: " + this.getColor());
        int distanceRow = start.getRow() - destRow; //x distance between
        int distanceCol = start.getCol() - destCol; // y distance between
        if (!dest.getOccupied() || dest.getPiece().getColor() != start.getPiece().getColor()) {
            if (distanceRow == 0 || Math.abs(distanceRow) == 1) {
                if (distanceCol == 0 || Math.abs(distanceCol) == 1) {
                    return true;
                }
            }
            if (((King) start.getPiece()).getLastMove() == 0) {
                if (destRow == start.getRow() && (Math.abs(distanceCol) == 2) && start.getBW() == 'b') {
                    if (!dest.getOccupied() && chessboard.getSpace(0, 0).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0 && chessboard.getSpace(0, 7).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0) {
                        return true;
                    }
                } else if (destCol == start.getCol() && (Math.abs(distanceRow) == 2) && start.getBW() == 'w') {
                    if (!dest.getOccupied() && chessboard.getSpace(7, 0).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0 && chessboard.getSpace(7, 7).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    
    /**
     * sets the last move
     * @param x the last move
     */
    public void setLastMove(int x) {
        this.lastmove = x;

    }
    
    /**
     * returns the last move
     * @return the last move
     */
    public int getLastMove() {
        return this.lastmove;
    }

}