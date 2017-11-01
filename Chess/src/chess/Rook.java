package chess;

public class Rook extends ChessPiece {

    private int lastmove;

    public Rook(Space location, char color) {
        super(location, color);
        lastmove = 0;
    }

    public boolean move(Board chessboard, int destRow, int destCol) {

        Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);
        int x = 0;
        int y = 0;

        //System.out.println("Piece color: " + this.getColor());
        int distanceRow = destRow - loc.getRow(); //x distance between
        int distanceCol = destCol - loc.getCol(); // y distance between
        if (!dest.getOccupied() || dest.getBW() != this.getColor()) {
            if (loc.getRow() == destRow) {
                if (distanceCol>0) {
                    for (int i = 1; i < Math.abs(distanceCol); i++) {
                        if (!chessboard.getSpace(loc.getRow(), loc.getCol() + i).getOccupied()) {
                            x++;
                        }
                    }
                }
                else if (distanceCol<0){
                    for (int i = 1; i<Math.abs(distanceCol); i++){
                        if (!chessboard.getSpace(loc.getRow(), loc.getCol()-i).getOccupied()) {
                            x++;
                        }
                    }
                }
                if (x == Math.abs(distanceCol) - 1) {
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
                        if (!chessboard.getSpace(loc.getRow()-i, loc.getCol()).getOccupied()) {
                            y++;
                        }
                    }
                }
                if (y==Math.abs(distanceRow) - 1) {
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