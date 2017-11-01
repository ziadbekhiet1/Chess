package chess;

public class Bishop extends ChessPiece {

    private int lastmove;
    public Bishop(Space location, char color) { //color
        super(location, color);
        lastmove = 0;
    }

    public boolean move(Board chessboard, int destRow, int destCol) {

        Space loc = this.getLocation();
        Space dest = chessboard.getSpace(destRow, destCol);
        int x = 0;

        //System.out.println("Piece color: " + this.getColor());

        int distanceRow = destRow - loc.getRow(); //x distance between
        int distanceCol = destCol - loc.getCol(); // y distance between
        if (!dest.getOccupied() || dest.getPiece().getColor() != loc.getPiece().getColor()) {
            if (Math.abs(distanceRow) - Math.abs(distanceCol) == 0) {// y=x line so abs of both have to be 0
               if  (distanceRow>0 && distanceCol>0) {
                   for (int i = 1;i<Math.abs(distanceRow) ; i++) {
                       if (!chessboard.getSpace(loc.getRow()+i, loc.getCol()+i).getOccupied()) {
                           x++;
                           if (x == Math.abs(distanceCol)-1) {
                               return true;
                           }
                       }
                   }
               }
               else if  (distanceRow>0 && distanceCol<0) {
                   for (int i = 1; i<Math.abs(distanceRow); i++) {
                       if (!chessboard.getSpace(loc.getRow()+i, loc.getCol()-i).getOccupied()) {
                           x++;
                           if (x == Math.abs(distanceCol)-1) {
                               return true;
                           }
                       }
                   }
                }
               else if  (distanceRow<0 && distanceCol>0) {
                   for (int i = 1; i<Math.abs(distanceRow); i++) {
                       if (!chessboard.getSpace(loc.getRow()-i, loc.getCol()+i).getOccupied()) {
                           x++;
                           if (x == Math.abs(distanceCol)-1) {
                               return true;
                           }
                       }
                   }

                }
               else if  (distanceRow<0 && distanceCol<0) {
                   for (int i = 1; i<Math.abs(distanceRow); i++) {
                       if (!chessboard.getSpace(loc.getRow()-i, loc.getCol()-i).getOccupied()) {
                           x++;
                           if (x == Math.abs(distanceCol)-1) {
                               return true;
                           }
                       }
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