package chess;

public class Queen extends ChessPiece {

    private int lastmove;

    public Queen(Space location, char color) {

        super(location, color);

        lastmove = 0;
    }

    public boolean move(Board chessboard, int destRow, int destCol) {

        Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        System.out.println("Piece color: " + this.getColor());
        int distanceRow = loc.getRow() - destRow; //x distance between
        int distanceCol = loc.getCol() - destCol; // y distance between
        int locRow = loc.getRow(); //x distance between
        int locCol = loc.getCol(); // y distance between
        if (!dest.getOccupied() || dest.getBW() != this.getColor()) {
            if (locRow == destRow || locCol == destCol) {
                return true;
            }
            if (Math.abs(distanceRow) - Math.abs(distanceCol) == 0) { // y=x line so abs of both have to be 0
                return true;
            }
        }

        return false;
    }
    public void setLastMove(int x) {
        this.lastmove = x;
    }
    public int getLastMove() {
        return this.lastmove;
    }


}