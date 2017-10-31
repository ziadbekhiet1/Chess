package chess;

public class King extends ChessPiece {

    private int moved;
    private int lastmove;
    public King(Space location, char color) {
        super(location, color);
        lastmove = 0;
        moved = 0;
    }

    public boolean move(Board chessboard, int destRow, int destCol) {

        Space start = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);

        System.out.println("Piece color: " + this.getColor());
        int distanceRow = start.getRow() - destRow; //x distance between
        int distanceCol = start.getCol() - destCol; // y distance between
        if (dest.getBW() != this.getColor()) {
            if (distanceRow == 0 || distanceRow == 1) {
                if (distanceCol == 0 || distanceCol == 1) {
                    return true;
                }
            }
            if (((King) start.getPiece()).getLastMove() == 0) {
                if (destRow == start.getRow() && (Math.abs(distanceCol) == 2) && start.getBW() == 'b') {
                    if (chessboard.getSpace(0, 0).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0 && chessboard.getSpace(0, 7).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0) {
                        return true;
                    }
                } else if (destCol == start.getCol() && (Math.abs(distanceRow) == 2) && start.getBW() == 'w') {
                    if (chessboard.getSpace(7, 0).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0 && chessboard.getSpace(7, 7).getPiece() instanceof Rook && chessboard.getSpace(0, 0).getPiece().getLastMove() == 0) {
                        return true;
                    }
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