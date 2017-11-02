package chess;

public class Knight extends ChessPiece {

    private int lastmove;

    public Knight(Space location, char color) {
        super(location, color);

        lastmove = 0;
    }

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
    public void setLastMove(int x) {
        this.lastmove = x;
    }
    public int getLastMove() {
        return this.lastmove;
    }
}