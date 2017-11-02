package chess;

/**
 * Represents a standard chess queen.
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public class Queen extends ChessPiece {

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
    public Queen(Space location, char color) {

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
        int x = 0;
        int y = 0;
        int z = 0;

        //System.out.println("Piece color: " + this.getColor());
        int distanceRow = destRow - loc.getRow(); //x distance between
        int distanceCol = destCol - loc.getCol(); // y distance between
        int locRow = loc.getRow(); //x distance between
        int locCol = loc.getCol(); // y distance between
        if (!dest.getOccupied() || dest.getPiece().getColor() != this.getColor()) {
            if (loc.getRow() == destRow) {
                if (distanceCol>0) {
                    for (int i = 1; i < Math.abs(distanceCol); i++) {
                        if (!chessboard.getSpace(loc.getRow(), loc.getCol() + i).getOccupied()) {
                            z++;
                        }
                    }
                }
                else if (distanceCol<0){
                    for (int i = 1; i<Math.abs(distanceCol); i++){
                        if (!chessboard.getSpace(loc.getRow(), loc.getCol()-i).getOccupied()) {
                            z++;
                        }
                    }
                }
                if (z == Math.abs(distanceCol) - 1) {
                    return true;
                }

            }
            else if (loc.getCol() == destCol) {

                if (distanceRow>0) {
                    for (int i = 1; i < Math.abs(distanceRow); i++) {
                        if (!chessboard.getSpace(loc.getRow()+i, loc.getCol()).getOccupied()) {
                            y++;
                        }
                    }
                }
                else if (distanceRow<0){
                    for (int i = 1; i<Math.abs(distanceRow); i++){
                        if (chessboard.getSpace(locRow -i, locCol).getOccupied()) {
                            return false;
                        }
                        else{
                            y = 0;
                        }
                    }
                }
                if (y==Math.abs(distanceRow) - 1) {
                    return true;
                }
            }
            if (Math.abs(distanceRow) - Math.abs(distanceCol) == 0) {// y=x line so abs of both have to be 0
                if  (distanceRow>0 && distanceCol>0) {
                    for (int i = 1;i<Math.abs(distanceRow) ; i++) {
                        if (!chessboard.getSpace(loc.getRow()+i, loc.getCol()+i).getOccupied()) {
                            x++;
                        }
                    }
                }
                else if  (distanceRow>0 && distanceCol<0) {
                    for (int i = 1; i<Math.abs(distanceRow); i++) {
                        if (!chessboard.getSpace(loc.getRow()+i, loc.getCol()-i).getOccupied()) {
                            x++;
                        }
                    }
                }
                else if  (distanceRow<0 && distanceCol>0) {
                    for (int i = 1; i<Math.abs(distanceRow); i++) {
                        if (!chessboard.getSpace(loc.getRow()-i, loc.getCol()+i).getOccupied()) {
                            x++;
                        }
                    }

                }
                else if  (distanceRow<0 && distanceCol<0) {
                    for (int i = 1; i<Math.abs(distanceRow); i++) {
                        if (!chessboard.getSpace(loc.getRow()-i, loc.getCol()-i).getOccupied()) {
                            x++;
                        }
                    }
                }
                if (x == Math.abs(distanceRow)-1) {
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