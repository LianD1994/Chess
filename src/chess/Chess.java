package chess;
import pieces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Liangyan Ding
 * @authod Tien-Hsueh Li
 */
public class Chess {
	
	public static  char turn;
	public static int bKingRank = 0;
    public static int bKingFile = 4;
    public static int wKingRank = 7;
    public static int wKingFile = 4;
    public static Pieces[][] board;
    
    public static void main(String args[]) throws IOException {
        Game game = new Game();
        board = game.initialize();
        game.printBoard(board);
        System.out.println("Please follow this format, ex:a7 a5");
        turn = 'w';
        char offeredDraw = 0;
        Pieces undo; //for promotion
        char originalTurn = 'w'; //for castling
        boolean wRook1Moved = false;
        boolean wRook2Moved = false;
        boolean wKingMoved = false;
        boolean bRook1Moved = false;
        boolean bRook2Moved = false;
        boolean bKingMoved = false;
        boolean wKingChecked = false;
    	boolean bKingChecked = false;
    	boolean wEnPassant = false;
    	boolean bEnPassant = false;
    	int bPawnFile = 0; 
    	int wPawnFile = 0;
        
        while(true) {
        	
        	if(turn == 'w' && bKingChecked){
            	System.out.println("White wins");
            	System.exit(0);
            }
            if(turn == 'b' && wKingChecked){
            	System.out.println("Black wins");
            	System.exit(0);
            }
        	if(bKingChecked){
        		if(Pieces.checkMate()){
        			System.out.println("Checkmate, white wins.");
        			System.exit(0);
        		}
            	System.out.println("Black's king is checked.");
            }
            if(wKingChecked){
            	if(Pieces.checkMate()){
            		System.out.println("Checkmate, black wins.");
            		System.exit(0);
            	}
            	System.out.println("White's king is checked.");
            }
            
            
            bKingChecked = false;
            wKingChecked = false;
            
            if (turn == 'w') {
                System.out.print("White's move: ");
            } else {
                System.out.print("Black's move: ");
            }

            // Read input from player
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            String[] input = s.split(" ");

            // check inputs
            // -----------------------------------
            // check if input is empty
            if(input.length == 0){
            	System.out.println("Please follow the format, ex:a7 a5");
            	continue;
            }
            // check if a player resigns or accepts draw from other player.
            if(input.length == 1) {
                switch (input[0]) {
                    case "resign":
                        if (turn == 'w') {
                            System.out.println("White resigned, black wins");
                        } else {
                            System.out.println("Black resigned, white wins");
                        }
                        System.exit(0);

                    case "draw":
                        if (offeredDraw == 1) {
                            System.out.println("Draw.");
                            System.exit(0);
                        } else {
                            System.out.println("Please follow the format, ex:a7 a5");
                            continue;
                        }

                    default:
                        System.out.println("Please follow the format, ex:a7 a5");
                        continue;
                }
            }

            String fileRank1 = input[0];
            String fileRank2 = input[1];
           
            // convert rankFile to array index
            int file1 = Character.toLowerCase( (int)fileRank1.charAt(0) ) - 97;
            int rank1 = (Character.getNumericValue(fileRank1.charAt(1))-8)*(-1);
            int file2 = Character.toLowerCase( (int)fileRank2.charAt(0) ) - 97;
            int rank2 = (Character.getNumericValue(fileRank2.charAt(1))-8)*(-1);
            

            // input length exceeds expected value
            if (input.length > 3 || input[0].length() > 2 || input[1].length() > 2) {
            	game.printBoard(board);
                System.out.println("Please follow the format, ex:a7 a5");
                continue;
            }

            // input a value that is out of the board
            if(rank1<0||rank1>7||rank2<0||rank2>7||file1<0||file1>7||file2<0||file2>7){
            	game.printBoard(board);
                System.out.println("Illegal move, please try again.");
                continue;
            }
            // the starting position of the piece is null
            if(board[rank1][file1]==null){
            	game.printBoard(board);
                System.out.println("Illegal move, please try again.");
                continue;
            }
            // trying to move opponent's piece
            if(board[rank1][file1].getColor() != turn){
            	game.printBoard(board);
                System.out.println("Illegal move, please try again.");
                continue;
            }
            // check if a player offers draw.
            // if does, set indicator to 1.
            if(input.length == 3 && input[2].equals("draw?")){
                offeredDraw = 1;
            }

            // keep track of the piece name before move
            char moved = board[rank1][file1].getName();
            
            // promotion
            if(board[rank1][file1].getName()=='p'){ 
            	if(rank2 == 0 && turn == 'w'){
            		undo = board[rank2][file2];
            		board = board[rank1][file1].move(fileRank1, fileRank2, board);
            		if(board[rank1][file1]==null) {
                        if(turn == 'w'){
                            turn = 'b';
                        }else{
                            turn = 'w';
                        }
                    }else if(board[rank1][file1].getName() != moved){
                        if(turn == 'w'){
                            turn = 'b';
                        }else{
                            turn = 'w';
                        }
                    }
            		if(turn == originalTurn){
            			game.printBoard(board);
            			System.out.println("Illegal move, please try again.");
            			continue;
            		}
            		if(input.length<3){
            			Queen newQueen = new Queen();
            			newQueen.setColor('w');
            			board[rank2][file2] = newQueen;
            			originalTurn = turn;
            			game.printBoard(board);
            			if(Pieces.isUnderAttack(bKingRank, bKingFile, board)){
                        	bKingChecked = true;
                        }
                        if(Pieces.isUnderAttack(wKingRank, wKingFile, board)){
                        	wKingChecked = true;
                        }
            			continue;
            		}else if(input[2].length()==1&&(input[2].charAt(0)=='R'||input[2].charAt(0)=='N'||input[2].charAt(0)=='B')) {
                        if (input[2].charAt(0) == 'N') {
                            Knight newKnight = new Knight();
                            newKnight.setColor('w');
                            board[rank2][file2] = newKnight;
                        }
                        if (input[2].charAt(0) == 'B') {
                            Bishop newBishop = new Bishop();
                            newBishop.setColor('w');
                            board[rank2][file2] = newBishop;
                        }
                        if (input[2].charAt(0) == 'R') {
                            Rook newRook = new Rook();
                            newRook.setColor('w');
                            board[rank2][file2] = newRook;
                        }
                        originalTurn = turn;
                        game.printBoard(board);
                        if (Pieces.isUnderAttack(bKingRank, bKingFile, board)) {
                            bKingChecked = true;
                        }
                        if (Pieces.isUnderAttack(wKingRank, wKingFile, board)) {
                            wKingChecked = true;
                        }
                        continue;
                    }else{
            			turn = 'w';
            			originalTurn = turn;
            			board[rank1][file1] = board[rank2][file2];
            			board[rank2][file2] = undo;
            			game.printBoard(board);
            			System.out.println("Illegal move, please try again.");
            			continue;
            		}
            	}
            	if(rank2 == 7 && turn == 'b'){
            		undo = board[rank2][file2];
            		board = board[rank1][file1].move(fileRank1, fileRank2, board);
            		if(board[rank1][file1]==null) {
                        if(turn == 'w'){
                            turn = 'b';
                        }else{
                            turn = 'w';
                        }
                    }else if(board[rank1][file1].getName() != moved){
                        if(turn == 'w'){
                            turn = 'b';
                        }else{
                            turn = 'w';
                        }
                    }
            		if(turn == originalTurn){
            			game.printBoard(board);
            			System.out.println("Illegal move, please try again.");
            			continue;
            		}
            		if(input.length<3){
            			Queen newQueen = new Queen();
            			newQueen.setColor('b');
            			board[rank2][file2] = newQueen;
            			originalTurn = turn;
            			game.printBoard(board);
            			if(Pieces.isUnderAttack(bKingRank, bKingFile, board)){
                        	bKingChecked = true;
                        }
                        if(Pieces.isUnderAttack(wKingRank, wKingFile, board)){
                        	wKingChecked = true;
                        }
            			continue;
            		}else if(input[2].length()==1&&(input[2].charAt(0)=='R'||input[2].charAt(0)=='N'||input[2].charAt(0)=='B')){
            			if(input[2].charAt(0)=='N'){
            				Knight newKnight = new Knight();
                			newKnight.setColor('b');
                			board[rank2][file2] = newKnight;
            			}
            			if(input[2].charAt(0)=='B'){
            				Bishop newBishop = new Bishop();
                			newBishop.setColor('b');
                			board[rank2][file2] = newBishop;
            			}
            			if(input[2].charAt(0)=='R'){
            				Rook newRook = new Rook();
                			newRook.setColor('b');
                			board[rank2][file2] = newRook;
            			}
            			originalTurn = turn;
            			game.printBoard(board);
            			if(Pieces.isUnderAttack(bKingRank, bKingFile, board)){
                        	bKingChecked = true;
                        }
                        if(Pieces.isUnderAttack(wKingRank, wKingFile, board)){
                        	wKingChecked = true;
                        }
            			continue;
            		}else{
            			board[rank1][file1] = board[rank2][file2];
            			board[rank2][file2] = undo;
            			turn = 'b';
            			originalTurn = turn;
            			game.printBoard(board);
            			System.out.println("Illegal move, please try again.");
            			continue;
            		}
            	}
            }


            
            // white castling
            if(board[rank1][file1].getName() == 'K' && board[rank1][file1].getColor() == 'w' && (!wKingMoved)){
            	if(rank2 == 7 && file2 == 6 && (!wRook2Moved)){
            		boolean a = false; //check if there's any piece in between
            		for(int i=5; i<=6; i++){
            			if(board[7][i]!=null){
            				a = true;
            			}
            		}
            		for(int i=4; i<=7; i++){ //check if squares are under attack
            			if(Pieces.isUnderAttack(7, i, board)){
            				a = true;
            			}
            		}
            		if(a){
            			System.out.println("Illegal move, please try again.");
            			game.printBoard(board);
            			continue;
            		}
            		board[7][5] = board [7][7];
            		board[7][6] = board [7][4];
            		board[7][4] = null;
            		board[7][7] = null;
            		turn = 'b';
            		wKingMoved = true;
            		wKingRank = 7;
            		wKingFile = 6;
            		game.printBoard(board);
            		continue;            		
            	}
            	if(rank2 == 7 && file2 ==2 && (!wRook1Moved)){
            		boolean a = false; //check if there's any piece in between
            		for(int i=3; i>=1; i--){
            			if(board[7][i]!=null){
            				a = true;
            			}
            		}
            		for(int i=4; i>=1; i--){
            			if(Pieces.isUnderAttack(7, i, board)){
            				a = true;
            			}
            		}
            		if(a){
            			System.out.println("Illegal move, please try again.");
            			game.printBoard(board);
            			continue;
            		}
            		board[7][3] = board [7][0];
            		board[7][2] = board [7][4];
            		board[7][4] = null;
            		board[7][0] = null;
            		turn = 'b';
            		wKingMoved = true;
            		wKingRank = 7; 
            		wKingFile = 2;
            		game.printBoard(board);
            		continue;            		
            	}
            }
            if(board[rank1][file1].getName() == 'K' && board[rank1][file1].getColor() == 'b' && (!bKingMoved)){
            	if(rank2 == 0 && file2 == 6 && (!bRook2Moved)){
            		boolean a = false; //check if there's any piece in between
            		for(int i=5; i<=6; i++){
            			if(board[0][i]!=null){
            				a = true;
            			}
            		}
            		for(int i=4; i<=7; i++){
            			if(Pieces.isUnderAttack(0, i, board)){
            				a = true;
            			}
            		}
            		if(a){
            			System.out.println("Illegal move, please try again.");
            			game.printBoard(board);
            			continue;
            		}
            		board[0][5] = board [0][7];
            		board[0][6] = board [0][4];
            		board[0][4] = null;
            		board[0][7] = null;
            		turn = 'w';
            		bKingMoved = true;
            		bKingRank = 0;
            		bKingFile = 6;
            		game.printBoard(board);
            		continue;      
            		
            	}
            	if(rank2 == 0 && file2 == 2 && (!bRook1Moved)){
            		boolean a = false; //check if there's any piece in between
            		for(int i=3; i>=1; i--){
            			if(board[0][i]!=null){
            				a = true;
            			}
            		}
            		for(int i=4; i>=0; i--){
            			if(Pieces.isUnderAttack(0, i, board)){
            				a = true;
            			}
            		}
            		if(a){
            			System.out.println("Illegal move, please try again.");
            			game.printBoard(board);
            			continue;
            		}
            		board[0][3] = board [0][0];
            		board[0][2] = board [0][4];
            		board[0][4] = null;
            		board[0][0] = null;
            		turn = 'w';
            		wKingMoved = true;
            		bKingRank = 0;
            		bKingFile = 2;
            		game.printBoard(board);
            		continue;                   		
            	}
            }
            
            //en Passant
            if(turn == 'w' && bEnPassant && board[rank1][file1].getName() == 'p' && rank1 ==3 && rank2 == 2 && file2 == bPawnFile && (file2 == file1+1 || file2 == file1-1)){
            	Pawn newPawn = new Pawn();
            	newPawn.setColor('w');
            	board[rank2][file2] = newPawn;
            	board[rank1][file1] = null;
            	board[3][bPawnFile] = null;
            	turn = 'b';
            	originalTurn = turn;
            	bEnPassant = false;
            	game.printBoard(board);
            	continue;
            }
            if(turn == 'b' && wEnPassant && board[rank1][file1].getName() == 'p' && rank1 == 4 && rank2 == 5 && file2 == wPawnFile && (file2 == file1+1 || file2 == file1-1)){
            	Pawn newPawn = new Pawn();
            	newPawn.setColor('b');
            	board[rank2][file2] = newPawn;
            	board[rank1][file1] = null;
            	board[4][wPawnFile] = null;
            	turn = 'w';
            	originalTurn = turn;
            	wEnPassant = false;
            	game.printBoard(board);
            	continue;
            }
            
            // move the piece to new position
            board = board[rank1][file1].move(fileRank1, fileRank2, board);            
            
            // check if the piece has been moved, otherwise move is Illegal
            // set 'turn' back to the previous one
            if(board[rank1][file1]==null) {
                if(turn == 'w'){
                    turn = 'b';
                }else{
                    turn = 'w';
                }
            }else if(board[rank1][file1].getName() != moved){
                if(turn == 'w'){
                    turn = 'b';
                }else{
                    turn = 'w';
                }
            }
            
            //en Passant
            if(originalTurn != turn){
                bEnPassant = false;
                wEnPassant = false;
            }
            if(originalTurn != turn && board[rank2][file2].getName() == 'p' && board[rank2][file2].getColor() == 'w' && rank1 == 6 && rank2 == 4){
                wEnPassant = true;
                wPawnFile = file2;
            }
            if(originalTurn != turn && board[rank2][file2].getName() == 'p' && board[rank2][file2].getColor() == 'b' && rank1 == 1 && rank2 == 3){
                bEnPassant = true;
                bPawnFile = file2;
            }

            //get the updated file & rank if King is moved
            if(originalTurn != turn && board[rank2][file2].getName() == 'K'){
            	if(board[rank2][file2].getColor() == 'w'){
            		wKingFile = file2;
            		wKingRank = rank2; 
            	}else{
            		bKingFile = file2;
            		bKingRank = rank2;
            	}
            }

            if(Pieces.isUnderAttack(bKingRank, bKingFile, board)){
            	bKingChecked = true;
            }
            if(Pieces.isUnderAttack(wKingRank, wKingFile, board)){
            	wKingChecked = true;
            }
            
            //castling
            if(originalTurn!=turn && (board[rank2][file2].getName() == 'K'||board[rank2][file2].getName() == 'R')){ 
            	if(board[rank2][file2].getName() == 'K'){
            		if(board[rank2][file2].getColor() == 'w'){
            			wKingMoved = true;
            		}
            		if(board[rank2][file2].getColor() == 'b'){
            			bKingMoved = true;
            		}
            	}
            	if(board[rank2][file2].getName() == 'R'){
            		if(rank1 == 0&& file1 == 0){
            			bRook1Moved = true;
            		}
            		if(rank1 == 0&& file1 == 7){
            			bRook2Moved = true;
            		}
            		if(rank1 == 7 && file1 == 0){
            			wRook1Moved = true;
            		}
            		if(rank1 == 7 && file1 == 7){
            			wRook2Moved = true;
            		}
            	}
            }
            originalTurn = turn;

            // check for input "draw?"
            if(input.length == 3 ){
                if(input[2].equals("draw?")) {
                    offeredDraw = 1;
                }else{
                    System.out.println("Please follow the format, ex:a7 a5.");
                    continue;
                }
            }

            // print the board
            game.printBoard(board);
        }
    }
}
