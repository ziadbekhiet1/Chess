package chess;

/**
 * Represents a standard chess board, which is populated
 * by numerous spaces from the Space class.
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public class Board {

	/**
	 * Array representation of the chessboard
	 */
    // Array representation of the chess board
    private Space[][] chessboard;
    
    /**
     * number of moves total in the game so far
     */
    private int totalmoves;

    /**
     * Creates a new instance of board, and sets up the chess board format
     */
    // Constructor
    public Board () {
        chessboard = new Space[8][8];
        totalmoves = 0;
        initializeBoard();
    }

    /**
     * Returns a space of the board given the row and column
     * @param row the row of the space to be returned
     * @param col the column of the space to be returned
     * @return the space at (row, col)
     */
    public Space getSpace(int row, int col) {

        return chessboard[row][col];
    }

    /**
     * returns the piece on a board given a string of rowcol
     * @param boardCoords a string that represents the coordinates of the space
     * @return the piece on the space
     */
    public ChessPiece getPiece(String boardCoords) {

        int row = 0;
        int col = 0;

        // Assign the row
        switch(boardCoords.charAt(1))
        {
            case '1':
                row = 7;
                break;
            case '2':
                row = 6;
                break;
            case '3':
                row = 5;
                break;
            case '4':
                row = 4;
                break;
            case '5':
                row = 3;
                break;
            case '6':
                row = 2;
                break;
            case '7':
                row = 1;
                break;
            case '8':
                row = 0;
                break;
        }

        switch(boardCoords.charAt(0))
        {
            case 'a':
                col = 0;
                break;
            case 'b':
                col = 1;
                break;
            case 'c':
                col = 2;
                break;
            case 'd':
                col = 3;
                break;
            case 'e':
                col = 4;
                break;
            case 'f':
                col = 5;
                break;
            case 'g':
                col = 6;
                break;
            case 'h':
                col = 7;
                break;
        }

        return chessboard[row][col].getPiece();
    }

    /**
     * Initializes the chess board to it's initial state
     */
    // Initializes the chess board to it's initial state
    public final void initializeBoard() {

        boolean bw = true;

        for(int row = 0; row < 8; row++) {

            for(int col = 0; col < 8; col++) {

                // Initialize space with black or white

                if(bw) {

                    chessboard[row][col] = new Space(row, col, 'w');
                }
                else
                {
                    chessboard[row][col] = new Space(row, col, 'b');
                }

                bw = !bw;

                // Check to see if a piece belongs on space when the game starts

                // Black pieces
                if(row == 0 || row == 1) {

                    if(row == 1) {

                        //pawn
                        chessboard[row][col].setPiece(new Pawn(chessboard[row][col], 'b'));
                    } else if(col == 0 || col == 7) {

                        // rook
                        chessboard[row][col].setPiece(new Rook(chessboard[row][col], 'b'));
                    }
                    else if(col == 1 || col == 6) {

                        // knight
                        chessboard[row][col].setPiece(new Knight(chessboard[row][col], 'b'));
                    }
                    else if(col == 2 || col == 5) {

                        // bishop
                        chessboard[row][col].setPiece(new Bishop(chessboard[row][col], 'b'));
                    }
                    else if(col == 3) {

                        // queen
                        chessboard[row][col].setPiece(new Queen(chessboard[row][col], 'b'));
                    }
                    else if(col == 4) {

                        // king
                        chessboard[row][col].setPiece(new King(chessboard[row][col], 'b'));
                    }

                    chessboard[row][col].setOccupied(true);
                }
                // White pieces
                else if(row == 6 || row == 7) {

                    if(row == 6) {

                        // Pawn
                        chessboard[row][col].setPiece(new Pawn(chessboard[row][col], 'w'));
                    }
                    else if(col == 0 || col == 7) {

                        // rook
                        chessboard[row][col].setPiece(new Rook(chessboard[row][col], 'w'));
                    }
                    else if(col == 1 || col == 6) {

                        // knight
                        chessboard[row][col].setPiece(new Knight(chessboard[row][col], 'w'));
                    }
                    else if(col == 2 || col == 5) {

                        // bishop
                        chessboard[row][col].setPiece(new Bishop(chessboard[row][col], 'w'));
                    }
                    else if(col == 3) {

                        // queen
                        chessboard[row][col].setPiece(new Queen(chessboard[row][col], 'w'));
                    }
                    else if(col == 4) {

                        // king
                        chessboard[row][col].setPiece(new King(chessboard[row][col], 'w'));
                    }

                    chessboard[row][col].setOccupied(true);
                }
                else
                {
                    chessboard[row][col].setPiece(null);
                }
            }

            bw = !bw;
        }
    }

    /**
     * Checks for check for white
     * @param board the chessboard at a given check
     * @return 1 or 0, 0 if not in check
     */
    public int checkWhite(Board board) {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board.getSpace(x,y).getPiece() instanceof King) {
                    //System.out.println("White King is at  " + x + " " + y);
                    if (board.getSpace(x,y).getPiece().getColor() == 'w') {
                        //System.out.print(1);
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                                    ChessPiece sourcepiece = board.getSpace(i, j).getPiece();
                                    if (sourcepiece.move(board, x, y)) {
                                        //System.out.println(2);
                                        //System.out.println("White King is at:  " + x + " " + y);
                                        //System.out.println("Black pieces are at" + i + " " + j);
                                        return 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * Checks for check for black
     * @param board the chessboard at a given check
     * @return 1 or 0, 0 if not in check
     */
    public int checkBlack(Board board) {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board.getSpace(x,y).getPiece() instanceof King) {
                     if (board.getSpace(x,y).getPiece().getColor() == 'b') {
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                if (board.getSpace(i,j).getPiece() != null && board.getSpace(i,j).getPiece().getColor() == 'w') {
                                    ChessPiece sourcepiece = board.getSpace(i,j).getPiece();
                                    if (sourcepiece.move(board, x, y)) {
                                       // System.out.print(4);
                                        //System.out.println("White King is at:  " + x + " " + y);
                                        //System.out.println("Black pieces are at" + " " + j);
                                        return 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * Checks for checkmate conditions for white
     * @param board the instance of the board at the check
     * @return 1 or 0, 0 if not checkmate
     */
    public int checkMateforWhite(Board board) {
        int a = 0;
        int b = 0;
        for(int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board.getSpace(x, y).getPiece() instanceof King) {
                    if (board.getSpace(x, y).getPiece().getColor() == 'w') {
                        a = x;
                        b = y;
                    }
                }
            }
        }
        if (a>0 && a<7 && b>0 && b<7){
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a+1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a-1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }

            if (countWhite == 8){
                return 1;
            }
        }
        else if (a==0 && b==0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 3) {
                return 1;
            }

        }
        else if (a==0 && b==7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b- 1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }

            if (countWhite == 3) {
                return 1;
            }

        }
        else if (a==7 && b==0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        } else if (board.getSpace(a - 1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 3) {
                return 1;
            }
        }
        else if (a==7 && b==7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 3) {
                return 1;
            }

        }
        else if (a==7 && b!=7 && b!=0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a , b-1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }

            }
            if (countWhite == 5){
                return 1;
            }

        }
        else if (a==0 && b!=0 && b!=7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;

                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            //System.out.println(countWhite);
            if (countWhite == 5) {
                return 1;
            }

        }
        else if (b==0 && a!=0 && a!=7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 5){
                return 1;
            }


        }
        else if (b==7 && a!=7 && a!=0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'b') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 5){
                return 1;
            }
        }


        return 0;
    }

    /**
     * Checks for checkmate conditions for black
     * @param board the instance of the board at the check
     * @return 1 or 0, 0 if not checkmate
     */
    public int checkMateforBlack(Board board) {
        int a = 0;
        int b = 0;
        for(int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board.getSpace(x, y).getPiece() instanceof King) {
                    if (board.getSpace(x, y).getPiece().getColor() == 'b') {
                        a = x;
                        b = y;
                    }
                }
            }
        }
        if (a>0 && a<7 && b>0 && b<7){
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a+1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a-1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }

            if (countWhite == 8){
                return 1;
            }
        }
        else if (a==0 && b==0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 3) {
                return 1;
            }

        }
        else if (a==0 && b==7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b- 1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }

            if (countWhite == 3) {
                return 1;
            }

        }
        else if (a==7 && b==0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        } else if (board.getSpace(a - 1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 3) {
                return 1;
            }
        }
        else if (a==7 && b==7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 3) {
                return 1;
            }

        }
        else if (a==7 && b!=7 && b!=0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a , b-1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }

            }
            if (countWhite == 5){
                return 1;
            }

        }
        else if (a==0 && b!=0 && b!=7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;

                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                             break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            //System.out.println(countWhite);
            if (countWhite == 5) {
                return 1;
            }

        }
        else if (b==0 && a!=0 && a!=7) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b + 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b+1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 5){
                return 1;
            }


        }
        else if (b==7 && a!=7 && a!=0) {
            int countWhite = 0;
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a + 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a+1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            outerloop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getSpace(i, j).getPiece() != null && board.getSpace(i, j).getPiece().getColor() == 'w') {
                        if (board.getSpace(i, j).getPiece().move(board, a - 1, b - 1)) {
                            countWhite++;
                            break outerloop;
                        }
                        else if (board.getSpace(a-1, b-1).getOccupied()) {
                            countWhite++;
                            break outerloop;
                        }
                    }
                }
            }
            if (countWhite == 5){
                return 1;
            }
        }


        return 0;
    }

    /**
     * Prints the board out to the console
     */
    // Prints out the board to the console
    public void printBoard() {

        char letter = 'a';
        int number = 8;

        for(int x = 0; x < 9; x++) {

            for (int y = 0; y < 9; y++) {

                if(x == 8)
                {
                    if(y != 8)
                    {
                        System.out.print(" " + letter + " ");
                        letter++;
                    }
                }
                else if(y == 8)
                {
                    System.out.print(number);
                    number--;
                }
                else if(chessboard[x][y].getPiece() instanceof Pawn) {

                    if(chessboard[x][y].getPiece().getColor() == 'w') {

                        System.out.print("wP ");
                    }
                    else {

                        System.out.print("bP ");
                    }
                }
                else if(chessboard[x][y].getPiece() instanceof Bishop) {

                    if(chessboard[x][y].getPiece().getColor() == 'w') {

                        System.out.print("wB ");
                    }
                    else {

                        System.out.print("bB ");
                    }

                }
                else if(chessboard[x][y].getPiece() instanceof Knight) {

                    if(chessboard[x][y].getPiece().getColor() == 'w') {

                        System.out.print("wN ");
                    }
                    else {

                        System.out.print("bN ");
                    }

                }
                else if(chessboard[x][y].getPiece() instanceof Rook) {
                    Rook piece = (Rook) chessboard[x][y].getPiece();


                    if(chessboard[x][y].getPiece().getColor() == 'w') {

                        System.out.print("wR ");
                    }
                    else {

                        System.out.print("bR ");
                    }

                }
                else if(chessboard[x][y].getPiece() instanceof Queen) {

                    if(chessboard[x][y].getPiece().getColor() == 'w') {

                        System.out.print("wQ ");
                    }
                    else {

                        System.out.print("bQ ");
                    }

                }
                else if(chessboard[x][y].getPiece() instanceof King) {

                    if(chessboard[x][y].getPiece().getColor() == 'w') {

                        System.out.print("wK ");
                    }
                    else {

                        System.out.print("bK ");
                    }

                }
                else {

                    if(chessboard[x][y].getBW() == 'b') {

                        System.out.print("## ");
                    }
                    else {

                        System.out.print("   ");
                    }
                }
            }

            System.out.println();
        }
    }
    
    /**
     * Returns the total number of moves
     * @return the total number of moves
     */
    public int getTotalmoves() {
        return totalmoves;
    }
    
    /**
     * Increments the total number of moves by 1
     * @param board takes the current state of the chessboard
     */
    public void incrementTotalMoves(Board board) {
        this.totalmoves++;
    }
}