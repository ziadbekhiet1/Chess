package chess;

/**
 * Represents a standard chess pawn.
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public class Pawn extends ChessPiece {

	/**
	 * Tells us if the pawn has moved
	 */
    private int moved;
    
    /**
	 * Boolean variable to store capabilities
	 */
    private boolean canEmp;
    
    /**
	 * Stores the last move of a piece.
	 */
    private int lastmove;

    /**
   	 * Creates a new instance of the piece
   	 * 
   	 * @param location the space where the piece is currently located
   	 * @param color the color of the piece
   	 */
    public Pawn(Space location, char color) {
        super(location, color);

        moved = 0;
        canEmp = false;
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
       // System.out.println(this.getColor());
        Space dest = chessboard.getSpace(destRow, destCol);
        //System.out.println(destRow + " " + destCol);

        //System.out.println("Piece color: " + this.getColor());

        if (this.getColor() == 'w') {
            if (destCol == loc.getCol() && (destRow == loc.getRow() - 1 || destRow == loc.getRow() - 2) && !dest.getOccupied()) {
                return true;
            }
            else if (destRow == loc.getRow() - 1 && (destCol == loc.getCol() - 1 || destCol == loc.getCol() + 1)) {
                if ((chessboard.getSpace(destRow-1 , destCol).getPiece() instanceof Pawn && chessboard.getSpace(destRow-1, destCol).getPiece().getLastMove() == chessboard.getTotalmoves()-1) && !dest.getOccupied()){
                    return true;
                }
                else if (dest.getOccupied() && dest.getPiece().getColor() != this.getColor()) {
                    return true;

                }
            }

        }
        else if(this.getColor() == 'b') {
            if (destCol == loc.getCol() && (destRow == loc.getRow() + 1 || destRow == loc.getRow() + 2) && !dest.getOccupied()) {
                return true;
            } else if (destRow == loc.getRow() + 1 && (destCol == loc.getCol() - 1 || destCol == loc.getCol() + 1)) {
                if ((chessboard.getSpace(destRow + 1, destCol).getPiece() instanceof Pawn && chessboard.getSpace(destRow + 1, destCol).getPiece().getLastMove() == chessboard.getTotalmoves() - 1) && !dest.getOccupied()) {
                    return true;
                } else if (dest.getOccupied() && dest.getPiece().getColor() != this.getColor()) {
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