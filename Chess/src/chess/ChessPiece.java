package chess;

/**
 * Represents a standard chess piece, and is the superclass
 * for the subclass pieces.
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public abstract class ChessPiece {

	/**
	 * The color of the chess piece
	 */
    private char color;
    
    /*
     * the Space object that represents the location of the piece
     */
    private Space location;

    /**
   	 * Creates a new instance of the piece
   	 * 
   	 * @param location the space where the piece is currently located
   	 * @param color the color of the piece
   	 */
    public ChessPiece(Space location, char color) {

        this.location = location;
        this.color = color;
    }

    /**
     * Returns the color of the piece
     * @return the color of the piece
     */
    public char getColor() {

        return this.color;
    }

    /**
     * Returns the location of the piece
     * @return the space object location of the piece
     */
    public Space getLocation() {

        return this.location;
    }

    /** 
     * sets the location of the piece to a space
     * @param location a space where the piece is going to go
     */
    public void setLocation(Space location) {

        this.location = location;
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
    public abstract boolean move(Board chessboard, int destRow, int destCol);

    /**
     * sets the last move
     * @param x the last move
     */
    public abstract void setLastMove(int x);

    /**
     * returns the last move
     * @return the last move
     */
    public abstract int getLastMove();

}