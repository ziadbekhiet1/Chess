package chess;

import java.util.Scanner;

/**
 * The main class of our chess application.
 * 
 * @author Michael Allen
 * @author Ziad Bekhiet
 */
public class Chess {

    static Scanner scan = new Scanner(System.in);
    static boolean winner = false;
    static boolean resign = false;
    static boolean draw = false;
    static int drawVal = 0;
    static Board board;

    /**
     * Runs the game, including the turn order and color
     */
    public static void playGame() {

        boolean turn = false; 		// true - white, false - black
        board = new Board();

        do {

            turn = !turn;

            board.printBoard();
            System.out.println("");

            if(turn) {
                if (board.checkMateforWhite(board) == 1 && board.checkWhite(board) == 1) {
                    System.out.println("CheckMate");
                    winner = true;
                }
                else if (board.checkWhite(board) == 1) {
                    System.out.println("Check");
                    System.out.print("White's move: ");
                }
                else {
                    System.out.print("White's move: ");
                }

            }
            else {
                if (board.checkBlack(board) == 1 && board.checkMateforBlack(board) == 1) {
                    System.out.println("CheckMate");
                    winner = true;
                }
                else if (board.checkBlack(board) == 1) {
                    System.out.println("Check");
                    System.out.print("Black's move: ");
                }
                else {
                    System.out.print("Black's move: ");
                }
            }

            turn(turn);

        } while(!winner);

        
        if(draw) {
        	
        	System.out.println("DRAW!");
        	
        } else if(turn) {

        	if(!resign) {
        		 System.out.println("WHITE WINS!");
        	}
        	else {
        		System.out.println("BLACK WINS!");
        	}
           
        }
        else if(!turn) {

        	if(!resign) {
       		 System.out.println("BLACK WINS!");
	       	}
	       	else {
	       		System.out.println("WHITE WINS!");
	       	}
        }
    }

    /**
     * Represents a chess turn, including input validation
     * @param turn the color of the turn (true - white, false - black)
     */
    public static void turn(boolean turn) {

        boolean reinput = false;

        do {

            if(reinput) {

                if(turn) {

                    System.out.print("White - please enter a valid move: ");
                }
                else {

                    System.out.print("Black - please enter a valid move: ");
                }

            }

            String input = scan.nextLine();

            if(input.equalsIgnoreCase("resign")) {

                winner = true;
                resign = true;
                reinput = false;
            }
            
            if(input.equalsIgnoreCase("draw") && drawVal == 1) {
            	
            	draw = true;
            	winner = true;
            } else
            
            if(input.length() == 5 || input.length() == 11) {

            	drawVal = 0;
                String source = input.substring(0, 2);
                String destination = input.substring(3, 5);

                //System.out.println("Source piece: " + source);
                //System.out.println("Destination location: " + destination);

                // Check input
                if((source.charAt(0) != 'a'
                        && source.charAt(0) != 'b'
                        && source.charAt(0) != 'c'
                        && source.charAt(0) != 'd'
                        && source.charAt(0) != 'e'
                        && source.charAt(0) != 'f'
                        && source.charAt(0) != 'g'
                        && source.charAt(0) != 'h')
                        || (source.charAt(1) != '1'
                        && source.charAt(1) != '2'
                        && source.charAt(1) != '3'
                        && source.charAt(1) != '4'
                        && source.charAt(1) != '5'
                        && source.charAt(1) != '6'
                        && source.charAt(1) != '7'
                        && source.charAt(1) != '8')
                        || (destination.charAt(0) != 'a'
                        && destination.charAt(0) != 'b'
                        && destination.charAt(0) != 'c'
                        && destination.charAt(0) != 'd'
                        && destination.charAt(0) != 'e'
                        && destination.charAt(0) != 'f'
                        && destination.charAt(0) != 'g'
                        && destination.charAt(0) != 'h')
                        || (destination.charAt(1) != '1'
                        && destination.charAt(1) != '2'
                        && destination.charAt(1) != '3'
                        && destination.charAt(1) != '4'
                        && destination.charAt(1) != '5'
                        && destination.charAt(1) != '6'
                        && destination.charAt(1) != '7'
                        && destination.charAt(1) != '8')
                        || (source.equalsIgnoreCase(destination))) {

                    System.out.println("ERROR: Invalid input.");
                    reinput = true;
                }
                else {

                    reinput = false;

                    // if input is good... do stuff
                    
                    if(input.length() == 11) {
                    	
                    	if(input.substring(5).equalsIgnoreCase(" draw?")) {
                    		
                    		drawVal = 1;
                    	}
                    }

                    //System.out.println("Valid input");

                    ChessPiece sourcePiece = board.getPiece(source);
                    
                    if(sourcePiece == null)
                    {
                        System.out.println("ERROR: No piece at that source.");
                        reinput = true;
                    } else if((turn && sourcePiece.getColor() == 'b') || (!turn && sourcePiece.getColor() == 'w')){
                    	
                    	System.out.println("ERROR: Piece selected is a different color than your color.");
                    	reinput = true;
                    }
                    else {

                        int row = 0;
                        int col = 0;

                        // Assign the row
                        switch(destination.charAt(1))
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

                        // Assign the column
                        switch(destination.charAt(0))
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

                        if(!sourcePiece.move(board, row, col)) {
                            //System.out.println(board.getSpace(row,col).getPiece());
                            System.out.println("Illegal move, please try again.");
                            reinput = true;
                        }
                        else
                        {
                            //System.out.println("MOVE SUCCESSFUL!");
                            board.incrementTotalMoves(board);
                            sourcePiece.setLastMove(board.getTotalmoves());

                            if (sourcePiece instanceof King) {
                                if (sourcePiece.getLastMove() == 0) {
                                    int distanceCol = col-sourcePiece.getLocation().getCol();
                                    int distanceRow = row-sourcePiece.getLocation().getRow();
                                    if (row == sourcePiece.getLocation().getRow() && (Math.abs(distanceCol) == 2) && sourcePiece.getColor() == 'b') {
                                        if (!board.getSpace(row,col).getOccupied() && board.getSpace(0, 0).getPiece() instanceof Rook && board.getSpace(0, 0).getPiece().getLastMove() == 0 && board.getSpace(0, 7).getPiece() instanceof Rook && board.getSpace(0, 7).getPiece().getLastMove() == 0) {
                                            board.getSpace(sourcePiece.getLocation().getRow(), sourcePiece.getLocation().getCol()).setOccupied(false);
                                            board.getSpace(sourcePiece.getLocation().getRow(), sourcePiece.getLocation().getCol()).setPiece(null);
                                            board.getSpace(row, col).setOccupied(true);
                                            board.getSpace(row, col).setPiece(sourcePiece);
                                            sourcePiece.setLocation(board.getSpace(row,col));
                                            if (distanceCol>0){
                                                Space rook1 = board.getSpace(0,7);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setOccupied(false);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setPiece(null);
                                                board.getSpace(row-2, col).setOccupied(true);
                                                board.getSpace(row-2, col).setPiece(rook1.getPiece());
                                                rook1.getPiece().setLocation(board.getSpace(row-2,col));
                                            }
                                            else if(distanceCol<0){
                                                Space rook1 = board.getSpace(0,7);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setOccupied(false);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setPiece(null);
                                                board.getSpace(row+2, col).setOccupied(true);
                                                board.getSpace(row+2, col).setPiece(rook1.getPiece());
                                                rook1.getPiece().setLocation(board.getSpace(row+2,col));
                                            }

                                        }
                                    } else if (row == sourcePiece.getLocation().getRow() && (Math.abs(distanceCol) == 2) && sourcePiece.getColor() == 'w') {
                                        if (!board.getSpace(row,col).getOccupied() && board.getSpace(0, 0).getPiece() instanceof Rook && board.getSpace(0, 0).getPiece().getLastMove() == 0 && board.getSpace(0, 7).getPiece() instanceof Rook && board.getSpace(0, 7).getPiece().getLastMove() == 0) {
                                            board.getSpace(sourcePiece.getLocation().getRow(), sourcePiece.getLocation().getCol()).setOccupied(false);
                                            board.getSpace(sourcePiece.getLocation().getRow(), sourcePiece.getLocation().getCol()).setPiece(null);
                                            board.getSpace(row, col).setOccupied(true);
                                            board.getSpace(row, col).setPiece(sourcePiece);
                                            sourcePiece.setLocation(board.getSpace(row,col));
                                            if (distanceCol>0){
                                                Space rook1 = board.getSpace(0,7);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setOccupied(false);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setPiece(null);
                                                board.getSpace(row-2, col).setOccupied(true);
                                                board.getSpace(row-2, col).setPiece(rook1.getPiece());
                                                rook1.getPiece().setLocation(board.getSpace(row-2,col));
                                            }
                                            else if(distanceCol<0){
                                                Space rook1 = board.getSpace(0,7);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setOccupied(false);
                                                board.getSpace(rook1.getPiece().getLocation().getRow(), rook1.getPiece().getLocation().getCol()).setPiece(null);
                                                board.getSpace(row+2, col).setOccupied(true);
                                                board.getSpace(row+2, col).setPiece(rook1.getPiece());
                                                rook1.getPiece().setLocation(board.getSpace(row+2,col));
                                            }

                                        }
                                    }
                                }
                            }
                            //System.out.println(sourcePiece.getLastMove());

                            // sets the current space with the piece in question to not occupied
                            board.getSpace(sourcePiece.getLocation().getRow(), sourcePiece.getLocation().getCol()).setOccupied(false);

                            // remove the piece from the space
                            board.getSpace(sourcePiece.getLocation().getRow(), sourcePiece.getLocation().getCol()).setPiece(null);

                            // sets the new space where the piece will move to occupied
                            board.getSpace(row, col).setOccupied(true);

                            // assign the destination space piece to source piece (move the piece)
                            board.getSpace(row, col).setPiece(sourcePiece);
                            sourcePiece.setLocation(board.getSpace(row,col));
                            board.getSpace(row,col).getPiece().setLastMove(board.getTotalmoves());
                            //System.out.println(board.getSpace(row,col).getPiece());
                            //System.out.println("Total moves: " + board.getTotalmoves());
                            if (sourcePiece instanceof Pawn && sourcePiece.getColor() == 'w' && sourcePiece.getLocation().getRow() == 0) {
                                // Assign the column
                                if (input.length() == 7) {
                                    switch (input.charAt(6)) {
                                        case 'N':
                                            board.getSpace(row, col).setPiece(new Knight(board.getSpace(row, col), 'w'));
                                            break;
                                        case 'B':
                                            board.getSpace(row, col).setPiece(new Bishop(board.getSpace(row, col), 'w'));
                                            break;
                                        case 'R':
                                            board.getSpace(row, col).setPiece(new Rook(board.getSpace(row, col), 'w'));
                                            break;

                                    }
                                }
                                else {
                                    board.getSpace(row, col).setPiece(new Queen(board.getSpace(row, col), 'w'));
                                }
                            }
                            else if (sourcePiece instanceof Pawn && sourcePiece.getColor() == 'b' && sourcePiece.getLocation().getRow() == 7) {
                            // Assign the column
                            if (input.length() == 7) {
                                switch (input.charAt(6)) {
                                    case 'N':
                                        board.getSpace(row, col).setPiece(new Knight(board.getSpace(row, col), 'b'));
                                        break;
                                    case 'B':
                                        board.getSpace(row, col).setPiece(new Bishop(board.getSpace(row, col), 'b'));
                                        break;
                                    case 'R':
                                        board.getSpace(row, col).setPiece(new Rook(board.getSpace(row, col), 'b'));
                                        break;

                                }
                            }
                            else {
                                board.getSpace(row, col).setPiece(new Queen(board.getSpace(row, col), 'b'));
                            }
                        }
                        }
                        }
                    }
                }
            else {

            	if(!winner) {
            		
            		System.out.println("ERROR: Invalid input. Please try again.");
            	}
            }


            System.out.println("");
        } while(reinput == true);
    }

    /**
     * The main method
     * @param args input arguments
     */
    public static void main(String args[]) {

        playGame();
    }
}