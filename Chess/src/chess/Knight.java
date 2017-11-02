package chess;

/**
 * Represents a standard chess knight.
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public class Knight extends ChessPiece {

    private int lastmove;

    /**
   	 * Creates a new instance of the piece
   	 * 
   	 * @param location the space where the piece is currently located
   	 * @param color the color of the piece
   	 */
    public Knight(Space location, char color) {
        super(location, color);

        lastmove = 0;
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

        Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        //System.out.println("Piece color: " + this.getColor());

        int distanceRow = loc.getRow() - destRow; //x distance between
        int distanceCol = loc.getCol() - destCol;
        int locRow = loc.getRow(); //x distance between
        int locCol = loc.getCol(); // y distance between
        if (!dest.getOccupied() || dest.getPiece().getColor() != this.getColor()) {
            if (Math.abs(distanceRow) + Math.abs(distanceCol) == 3) {
                if (locRow != destRow || locCol != destCol) {
                    return true;
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