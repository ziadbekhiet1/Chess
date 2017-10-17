package chess;

import java.util.Scanner;

public class Chess {
	
	static Scanner scan = new Scanner(System.in);
	static boolean winner = false;
	
	public static void playGame() {
		
		boolean turn = false; 		// true - white, false - black
		Board board = new Board();
		
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
		
		String input = scan.nextLine();
		
		if(input.equalsIgnoreCase("exit")) {
			
			winner = true;
		}
		
		System.out.println("");
	}
	
	public static void main(String args[]) {
		
		playGame();
	}
}
