package chess;

import java.util.Scanner;

public class Chess {
	
	static Scanner scan = new Scanner(System.in);
	static boolean winner = false;
	static Board board;
	
	public static void playGame() {
		
		boolean turn = false; 		// true - white, false - black
		board = new Board();
		
		do {
	
			turn = !turn;
			
			board.printBoard();
			System.out.println("");
			
			if(turn) {
				
				// white turn
				
				System.out.print("White's move: ");
			}
			else {
				
				// black turn
				System.out.print("Black's move: ");
			}
			
			turn(turn);
			
		} while(!winner);
		
		if(turn) {
			
			System.out.println("WHITE WINS!");
		}
		else {
			
			System.out.println("BLACK WINS!");
		}

	}
	
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
			
			if(input.equalsIgnoreCase("exit")) {
				
				winner = true;
			}
			
			if(input.length() == 5) {
				
				String source = input.substring(0, 2);
				String destination = input.substring(3, 5);
				
				System.out.println("Source piece: " + source);
				System.out.println("Destination location: " + destination);
				
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
					
					System.out.println("Valid input");
					
					ChessPiece sourcePiece = board.getPiece(source);
					
					if(sourcePiece == null)
					{
						System.out.println("No piece at that source.");
						reinput = true;
					}
					else {
					
						int row = (Integer.parseInt(source.substring(1)) - 1);
						int col = 0;
						
						switch(source.charAt(0))
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
							
							System.out.println("ERROR: Invalid move.");
						}
						else
						{
							System.out.println("MOVE SUCCESSFUL!");
						}
					}
				}
			}
			else {
				
				System.out.println("ERROR: Invalid input. Please try again.");
			}
			
			
			System.out.println("");
		} while(reinput == true);
	}
	
	public static void main(String args[]) {
		
		playGame();
	}
}
