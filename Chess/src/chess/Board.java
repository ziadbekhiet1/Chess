package chess;

public class Board {

	// Array representation of the chess board
	private Space[][] chessboard;
	
	// Constructor
	public Board () {
		
		chessboard = new Space[8][8];
		initializeBoard();
	}
	
	public Space getSpace(int row, int col) {
		
		return chessboard[row][col];
	}
	
	public ChessPiece getPiece(String boardCoords) {
		
		int row = 0;
		int col = 0;
		
		// Assign the row
		switch(boardCoords.charAt(1))
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
		
		switch(boardCoords.charAt(0))
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
		
		return chessboard[row][col].getPiece();
	}
	
	// Initializes the chess board to it's initial state
	public final void initializeBoard() {
		
		boolean bw = true;
		
		for(int row = 0; row < 8; row++) {
			
			for(int col = 0; col < 8; col++) {
				
				// Initialize space with black or white
				
				if(bw) {
					
					chessboard[row][col] = new Space(row, col, 'w');
				}
				else
				{
					chessboard[row][col] = new Space(row, col, 'b');
				}
				
				bw = !bw; 
				
				// Check to see if a piece belongs on space when the game starts
				
				// Black pieces
				if(row == 0 || row == 1) {
					
					if(row == 1) {
						
						//pawn
						chessboard[row][col].setPiece(new Pawn(chessboard[row][col], 'b'));
					} else if(col == 0 || col == 7) {
						
						// rook
						chessboard[row][col].setPiece(new Rook(chessboard[row][col], 'b'));
					}
					else if(col == 1 || col == 6) {
						
						// knight
						chessboard[row][col].setPiece(new Knight(chessboard[row][col], 'b'));
					}
					else if(col == 2 || col == 5) {
						
						// bishop
						chessboard[row][col].setPiece(new Bishop(chessboard[row][col], 'b'));
					}
					else if(col == 3) {
						
						// queen
						chessboard[row][col].setPiece(new Queen(chessboard[row][col], 'b'));
					}
					else if(col == 4) {
						
						// king
						chessboard[row][col].setPiece(new King(chessboard[row][col], 'b'));
					}
					
					chessboard[row][col].setOccupied(true);
				}
				// White pieces
				else if(row == 6 || row == 7) {
					
					if(row == 6) {
						
						// Pawn
						chessboard[row][col].setPiece(new Pawn(chessboard[row][col], 'w'));
					}
					else if(col == 0 || col == 7) {
						
						// rook
						chessboard[row][col].setPiece(new Rook(chessboard[row][col], 'w'));
					}
					else if(col == 1 || col == 6) {
						
						// knight
						chessboard[row][col].setPiece(new Knight(chessboard[row][col], 'w'));
					}
					else if(col == 2 || col == 5) {
						
						// bishop
						chessboard[row][col].setPiece(new Bishop(chessboard[row][col], 'w'));
					}
					else if(col == 3) {
						
						// queen
						chessboard[row][col].setPiece(new Queen(chessboard[row][col], 'w'));
					}
					else if(col == 4) {
						
						// king
						chessboard[row][col].setPiece(new King(chessboard[row][col], 'w'));
					}
					
					chessboard[row][col].setOccupied(true);
				}
				else 
				{
					chessboard[row][col].setPiece(null);
				}
			}
			
			bw = !bw;
		}
	}
	
	// Prints out the board to the console
	public void printBoard() {
		
		char letter = 'a';
		int number = 8;
		
		for(int x = 0; x < 9; x++) {
			
			for (int y = 0; y < 9; y++) {
				
				if(x == 8)
				{
					if(y != 8)
					{
						System.out.print(" " + letter + " ");
						letter++;
					}
				}
				else if(y == 8)
				{
					System.out.print(number);
					number--;
				} 
				else if(chessboard[x][y].getPiece() instanceof Pawn) {
					
					if(chessboard[x][y].getPiece().getColor() == 'w') {
						
						System.out.print("wP ");
					}
					else {
						
						System.out.print("bP ");
					}
				}
				else if(chessboard[x][y].getPiece() instanceof Bishop) {
					
					if(chessboard[x][y].getPiece().getColor() == 'w') {
						
						System.out.print("wB ");
					}
					else {
						
						System.out.print("bB ");
					}
					
				}
				else if(chessboard[x][y].getPiece() instanceof Knight) {
					
					if(chessboard[x][y].getPiece().getColor() == 'w') {
						
						System.out.print("wN ");
					}
					else {
						
						System.out.print("bN ");
					}
					
				}
				else if(chessboard[x][y].getPiece() instanceof Rook) {
					
					if(chessboard[x][y].getPiece().getColor() == 'w') {
						
						System.out.print("wR ");
					}
					else {
						
						System.out.print("bR ");
					}
					
				}
				else if(chessboard[x][y].getPiece() instanceof Queen) {
					
					if(chessboard[x][y].getPiece().getColor() == 'w') {
						
						System.out.print("wQ ");
					}
					else {
						
						System.out.print("bQ ");
					}
					
				}
				else if(chessboard[x][y].getPiece() instanceof King) {
					
					if(chessboard[x][y].getPiece().getColor() == 'w') {
						
						System.out.print("wK ");
					}
					else {
						
						System.out.print("bK ");
					}
					
				}
				else {
					
					if(chessboard[x][y].getBW() == 'b') {
						
						System.out.print("## ");
					}
					else {
						
						System.out.print("   ");
					}
				}
			}
			
			System.out.println();
		}
	}
}
