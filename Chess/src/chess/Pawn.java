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
        Space dest = chessboard.getSpace(destRow, destCol);

        System.out.println("Piece color: " + this.getColor());

        if(this.getColor() == 'w') {
            if(destCol == loc.getCol() && (destRow == loc.getRow() - 1 || destRow == loc.getRow() - 2) && !dest.getOccupied()) {
                return true;
            }
            else if (destRow == loc.getRow() - 1 && (destCol == loc.getCol() - 1 || destCol == loc.getCol() - 1)) {
                if(chessboard.castling(this.getLocation().getRow(), this.getLocation().getCol()) && this.getCanEmp())
                    return true;
                else if (dest.getOccupied())
                    return true;
            }

        }
        else {
            if (destCol == loc.getCol() && (destRow == loc.getRow() + 1 || destRow == loc.getRow() + 2) && !dest.getOccupied()) {
                return true;
            }
            else if (destRow == loc.getRow() + 1 && (destCol == loc.getCol() + 1 || destCol == loc.getCol() - 1)) {
                if(chessboard.castling(this.getLocation().getRow(), this.getLocation().getCol()) && this.getCanEmp())
                    return true;
                else if (dest.getOccupied())
                    return true;
                }
            }

        return false;
    }
    public void incrementMoved() {
        moved++;
    }
    public int getMoved() {
        return moved;
    }

    public void setLastMove(int x) {
        this.lastmove = x;
    }
    public int getLastMove() {
        return this.lastmove;
    }
    public void setCanEmp(boolean x) {
        this.canEmp = x;
    }
    public boolean getCanEmp() {
        return canEmp;
    }





}