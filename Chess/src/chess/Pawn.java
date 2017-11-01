package chess;

public class Pawn extends ChessPiece {

    private int moved;
    private boolean canEmp;
    private int lastmove;

    public Pawn(Space location, char color) {
        super(location, color);

        moved = 0;
        canEmp = false;
        lastmove = 0;
    }

    public boolean move(Board chessboard, int destRow, int destCol) {

        Space loc = this.getLocation();
       // System.out.println(this.getColor());
        Space dest = chessboard.getSpace(destRow, destCol);
        //System.out.println(destRow + " " + destCol);

        //System.out.println("Piece color: " + this.getColor());

        if (this.getColor() == 'w') {
            if (destCol == loc.getCol() && (destRow == loc.getRow() - 1 || destRow == loc.getRow() - 2) && !dest.getOccupied()) {
                return true;
            }
            else if (destRow == loc.getRow() - 1 && (destCol == loc.getCol() - 1 || destCol == loc.getCol() + 1)) {
                if ((chessboard.getSpace(destRow-1 , destCol).getPiece() instanceof Pawn && chessboard.getSpace(destRow-1, destCol).getPiece().getLastMove() == chessboard.getTotalmoves()-1) && !dest.getOccupied()){
                    return true;
                }
                else if (dest.getOccupied() && dest.getPiece().getColor() != this.getColor()) {
                    return true;

                }
            }

        }
        else if(this.getColor() == 'b') {
            if (destCol == loc.getCol() && (destRow == loc.getRow() + 1 || destRow == loc.getRow() + 2) && !dest.getOccupied()) {
                return true;
            } else if (destRow == loc.getRow() + 1 && (destCol == loc.getCol() - 1 || destCol == loc.getCol() + 1)) {
                if ((chessboard.getSpace(destRow + 1, destCol).getPiece() instanceof Pawn && chessboard.getSpace(destRow + 1, destCol).getPiece().getLastMove() == chessboard.getTotalmoves() - 1) && !dest.getOccupied()) {
                    return true;
                } else if (dest.getOccupied() && dest.getPiece().getColor() != this.getColor()) {
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