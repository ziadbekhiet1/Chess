package chess;

/**
 * Represents a space on our chess board
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public class Space {

	/**
	 * b if the space is black, w if the space is white
	 */
    private char bw;
    
    /**
     * true if the space is occupied, false if not
     */
    private boolean occupied;

    /**
     * holds a ChessPiece object if the space is non-null, null if no piece
     * is on the space.
     */
    private ChessPiece piece;

    /**
     * The row and column coordinates of the space
     */
    private int row, col;

    /**
     * Constructs a new Space object.
     * 
     * @param row the row of the space
     * @param col the column of the space
     * @param bw b if the space is black, w if the space is white
     */
    public Space (int row, int col, char bw) {

        this.row = row;
        this.col = col;
        this.bw = bw;
    }

    // getters & setters

    /**
     * Returns the row of the space
     * @return the row of the space
     */
    public int getRow() {

        return this.row;
    }

    /**
     * Returns the column of the space
     * @return the column of the space
     */
    public int getCol() {

        return this.col;
    }

    /**
     * Returns the color of the space
     * @return the color of the space
     */
    public char getBW() {

        return this.bw;
    }

    /**
     * Returns if the space is occupied
     * @return if the space is occupied
     */
    public boolean getOccupied() {

        return this.occupied;
    }

    /**
     * Returns the chess piece of the space
     * @return the chess piece on the space, null if none
     */
    public ChessPiece getPiece() {

        return this.piece;
    }

    /**
     * Sets the color of the space
     * @param bw the color of the space
     */
    public void setBW(char bw) {

        this.bw = bw;
    }

    /**
     * sets if the space is occupied or not
     * @param occupied true if occupied, false if not
     */
    public void setOccupied(boolean occupied) {

        this.occupied = occupied;
    }

    /** 
     * sets the piece on the space
     * @param piece the piece that is on the space, null if none
     */
    public void setPiece(ChessPiece piece) {

        this.piece = piece;
    }
}