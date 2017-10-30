package chess;
public class Board {

    // Array representation of the chess board
    private Space[][] chessboard;
    private int totalmoves;

    // Constructor
    public Board () {
        chessboard = new Space[8][8];
        totalmoves = 0;
        initializeBoard();
    }

    public Space getSpace(int row, int col) {

        return chessboard[row][col];
    }

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

    public boolean check() {

        for(int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if(chessboard[x][y].getPiece() instanceof King)
                    if(chessboard[x][y].getPiece().getColor() == 'w') {
                        for (int i = 0; i < 8; i++)
                            for (int j = 0; j < 8; j++)
                                if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                                    if (chessboard[i][j].getPiece().move(this, x, y))
                                        return true;
                    }
                    else if (chessboard[x][y].getPiece().getColor() == 'b') {
                        for (int i = 0; i < 8; i++)
                            for (int j = 0; j < 8; j++)
                                if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'w')
                                    if (chessboard[i][j].getPiece().move(this, x, y))
                                        return true;

                    }

        return false;
    }

    public boolean castling(int pawnRow, int pawnCol) {

        if (chessboard[pawnRow+1][pawnCol].getPiece() instanceof Pawn && chessboard[pawnRow+1][pawnCol].getPiece().getMoved() == 1) {
            ((Pawn) chessboard[pawnRow+1][pawnCol].getPiece()).setCanEmp(true);
            return true;
        }
        if (chessboard[pawnRow-1][pawnCol].getPiece() instanceof Pawn && chessboard[pawnRow-1][pawnCol].getPiece().getMoved() == 1) {
            ((Pawn) chessboard[pawnRow-1][pawnCol].getPiece()).setCanEmp(true);
            return true;
        }
        return false;
    }

    public boolean checkMate() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int countWhite = 0;
        int countBlack = 0;
        for(int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if (chessboard[x][y].getPiece() instanceof King) {
                    if (chessboard[x][y].getPiece().getColor() == 'w') {
                        a = x;
                        b = y;
                    } else if (chessboard[x][y].getPiece().getColor() == 'b') {
                        c = x;
                        d = y;
                    }
                }
        if ((a>0 && a<7 && b>0 && b<7) || (c>0 && c<7 && d>0 && d<7)) {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a + 1, b))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a - 1, b))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a, b + 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a, b - 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a + 1, b + 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a + 1, b - 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a - 1, b + 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, a - 1, b - 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c + 1, d))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c - 1, d))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c, d + 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c, d - 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c + 1, d + 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c + 1, d - 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c - 1, d + 1))
                            countWhite++;
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (chessboard[i][j].getPiece() != null && chessboard[i][j].getPiece().getColor() == 'b')
                        if (chessboard[i][j].getPiece().move(this, c - 1, d - 1))
                            countWhite++;
            if (countWhite == 8 || countBlack == 8) {
                return true;
            }
        }
        return false;
    }

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
        totalmoves++;
    }
}