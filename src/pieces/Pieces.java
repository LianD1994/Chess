package pieces;

import java.io.IOException;

import chess.Chess;

/**
 * @author Liangyan Ding
 * @authod Tien-Hsueh Li
 */
public abstract class Pieces {

    char color;
    char name;

    /*
    * @param  fileRank1  the starting position of the piece
    * @param  fileRank2  the ending position of the piece
    * @param  board      the Pieces array which contains all the chess piece objects
    * @return board      the modified board after the move is made
    * */
    public abstract Pieces[][] move(String fileRank1, String fileRank2, Pieces[][] board) throws IOException;
    
    public static int checkFile;
    
    public static int checkRank;
    
    public static char checkPiece;
    
    /*
     * @return  boolean  whether it's checkmate or not
     */
    public static boolean checkMate(){
    	
    	char color = Chess.turn;
    	int rankCount;
    	int fileCount; 
    	boolean availableMove = false;
    	boolean checkMate = true;
    	
    	if(color == 'w'){
			//set it to null to prevent the case that a move is protected by King itself
    		Chess.board[Chess.wKingRank][Chess.wKingFile] = null;
	    	rankCount = Chess.wKingRank-1; //1
			fileCount = Chess.wKingFile+1;
			if(rankCount >= 0 && fileCount <= 7){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.wKingRank; //2
			fileCount = Chess.wKingFile+1;
			if(fileCount <= 7){
                if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
                    availableMove = true;
                }
			}
			
			rankCount = Chess.wKingRank+1; //3
			fileCount = Chess.wKingFile+1;
			if(rankCount <= 7 && fileCount <= 7){
                if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
                    availableMove = true;
                }
			}
			
			rankCount = Chess.wKingRank+1; //4
			fileCount = Chess.wKingFile;
			if(rankCount <= 7){
                if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
                    availableMove = true;
                }
			}
			
			rankCount = Chess.wKingRank+1; //5
			fileCount = Chess.wKingFile-1;
			if(rankCount <= 7 && fileCount >= 0){
                if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
                    availableMove = true;
                }
			}
			
			rankCount = Chess.wKingRank;//6
			fileCount = Chess.wKingFile-1;
			if(fileCount >= 0){
                if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
                    availableMove = true;
                }
			}
			
			rankCount = Chess.wKingRank-1; //7
			fileCount = Chess.wKingFile-1;
			if(rankCount >= 0 && fileCount >= 0){
                if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
                    availableMove = true;
                }
			}
			
			rankCount = Chess.wKingRank-1; //8
			fileCount = Chess.wKingFile;
			if(rankCount >= 0){
                if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
                    availableMove = true;
                }
			}

			// put the king back once underattack() check is complete
			King wKing = new King();
			wKing.setColor('w');
			Chess.board[Chess.wKingRank][Chess.wKingFile] = wKing;
	    	
			if(availableMove) {
                return false;
            }

			// if there's no available move for King
	    	if(isUnderAttack(Chess.wKingRank, Chess.wKingFile, Chess.board)){
		    		if(checkPiece == 'R'){ //if the checked by Rook
		    			if(Chess.wKingRank == checkRank && Chess.wKingFile < checkFile){
		    				fileCount = Chess.wKingFile+1;
		    				while(fileCount <= checkFile){
		    					if(isProtected(Chess.wKingRank, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					fileCount++;
		    				}
		    			}
		    			if(Chess.wKingRank == checkRank && Chess.wKingFile > checkFile){
		    				fileCount = Chess.wKingFile-1;
		    				while(fileCount >= checkFile){
		    					if(isProtected(Chess.wKingRank, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					fileCount--;
		    				}
		    			}
		    			if(Chess.wKingFile == checkFile && Chess.wKingRank < checkRank){
		    				rankCount = Chess.wKingRank+1;
		    				while(rankCount <= checkRank){
		    					if(isProtected(rankCount, Chess.wKingFile, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount++;
		    				}
		    			}
		    			if(Chess.wKingFile == checkFile && Chess.wKingRank > checkRank){
		    				rankCount = Chess.wKingRank-1;
		    				while(rankCount >= checkRank){
		    					if(isProtected(rankCount, Chess.wKingFile, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount--;
		    				}
		    			}
		    			
		    		}else if(checkPiece == 'B'){
		    			if(checkRank > Chess.wKingRank && checkFile >Chess.wKingFile){
		    				rankCount = Chess.wKingRank+1;
		    				fileCount = Chess.wKingFile+1;
		    				while(rankCount <= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount++;
		    					fileCount++;
		    				}
		    			}
						if(checkRank > Chess.wKingRank && checkFile <Chess.wKingFile){
							rankCount = Chess.wKingRank+1;
		    				fileCount = Chess.wKingFile-1;
		    				while(rankCount <= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount++;
		    					fileCount--;
		    				}		    				
						}
						if(checkRank < Chess.wKingRank && checkFile >Chess.wKingFile){
							rankCount = Chess.wKingRank-1;
		    				fileCount = Chess.wKingFile+1;
		    				while(rankCount >= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount--;
		    					fileCount++;
		    				}
						}
						if(checkRank < Chess.wKingRank && checkFile <Chess.wKingFile){
							rankCount = Chess.wKingRank-1;
		    				fileCount = Chess.wKingFile-1;
		    				while(rankCount >= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount--;
		    					fileCount--;
		    				}
						}
		    			
		    		}else if(checkPiece == 'N'){
		    			if(isProtected(checkRank, checkFile, Chess.board)){
		    				checkMate = false;
		    			}
		    		}else if(checkPiece == 'p'){
		    			if(!isUnderAttack(checkRank, checkFile, Chess.board)||isProtected(Chess.wKingRank, Chess.wKingFile, Chess.board)){
		    				checkMate = false;
		    			}
		    		}else if(checkPiece == 'Q'){
		    			if(Chess.wKingRank == checkRank || Chess.wKingFile == checkFile){
		    				if(Chess.wKingRank == checkRank && Chess.wKingFile < checkFile){
			    				fileCount = Chess.wKingFile+1;
			    				while(fileCount <= checkFile){
			    					if(isProtected(Chess.wKingRank, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					fileCount++;
			    				}
			    			}
			    			if(Chess.wKingRank == checkRank && Chess.wKingFile > checkFile){
			    				fileCount = Chess.wKingFile-1;
			    				while(fileCount >= checkFile){
			    					if(isProtected(Chess.wKingRank, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					fileCount--;
			    				}
			    			}
			    			if(Chess.wKingFile == checkFile && Chess.wKingRank < checkRank){
			    				rankCount = Chess.wKingRank+1;
			    				while(rankCount <= checkRank){
			    					if(isProtected(rankCount, Chess.wKingFile, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount++;
			    				}
			    			}
			    			if(Chess.wKingFile == checkFile && Chess.wKingRank > checkRank){
			    				rankCount = Chess.wKingRank-1;
			    				while(rankCount >= checkRank){
			    					if(isProtected(rankCount, Chess.wKingFile, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount--;
			    				}
			    			}
		    			}else{
		    				if(checkRank > Chess.wKingRank && checkFile >Chess.wKingFile){
			    				rankCount = Chess.wKingRank+1;
			    				fileCount = Chess.wKingFile+1;
			    				while(rankCount <= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount++;
			    					fileCount++;
			    				}
			    			}
							if(checkRank > Chess.wKingRank && checkFile <Chess.wKingFile){
								rankCount = Chess.wKingRank+1;
			    				fileCount = Chess.wKingFile-1;
			    				while(rankCount <= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount++;
			    					fileCount--;
			    				}		    				
							}
							if(checkRank < Chess.wKingRank && checkFile >Chess.wKingFile){
								rankCount = Chess.wKingRank-1;
			    				fileCount = Chess.wKingFile+1;
			    				while(rankCount >= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount--;
			    					fileCount++;
			    				}
							}
							if(checkRank < Chess.wKingRank && checkFile <Chess.wKingFile){
								rankCount = Chess.wKingRank-1;
			    				fileCount = Chess.wKingFile-1;
			    				while(rankCount >= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount--;
			    					fileCount--;
			    				}
							}
		    			}
		  
	    		}
	    		
		    	//change the color of the check piece, to check if there's more than one pieces checking 
		    	Chess.board[checkRank][checkFile].setColor('w');
		    	if(isUnderAttack(Chess.wKingRank, Chess.wKingFile, Chess.board)){
		    		return true;
		    	}
		    	Chess.board[checkRank][checkFile].setColor('b');
	    	}
    	}else{ //black
			Chess.board[Chess.bKingRank][Chess.bKingFile] = null;
    		rankCount = Chess.bKingRank-1; //1
			fileCount = Chess.bKingFile+1;
			if(rankCount >= 0 && fileCount <= 7){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.bKingRank; //2
			fileCount = Chess.bKingFile+1;
			if(fileCount <= 7){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.bKingRank+1; //3
			fileCount = Chess.bKingFile+1;
			if(rankCount <= 7 && fileCount <= 7){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.bKingRank+1; //4
			fileCount = Chess.bKingFile;
			if(rankCount <= 7){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.bKingRank+1; //5
			fileCount = Chess.bKingFile-1;
			if(rankCount <= 7 && fileCount >= 0){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.bKingRank;//6
			fileCount = Chess.bKingFile-1;
			if(fileCount >= 0){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.bKingRank-1; //7
			fileCount = Chess.bKingFile-1;
			if(rankCount >= 0 && fileCount >= 0){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}
			
			rankCount = Chess.bKingRank-1; //8
			fileCount = Chess.bKingFile;
			if(rankCount >= 0){
				if((Chess.board[rankCount][fileCount] == null || Chess.board[rankCount][fileCount].getColor() != color) && !isUnderAttack(rankCount, fileCount, Chess.board)){
					availableMove = true;
				}
			}

	    	King bKing = new King();
			bKing.setColor('b');
			Chess.board[Chess.bKingRank][Chess.bKingFile] = bKing;

			if(availableMove){
			    return false;
			}

			// if there's no available move for King
	    	if(isUnderAttack(Chess.bKingRank, Chess.bKingFile, Chess.board)){
	    	        if(checkPiece == 'R'){ //if the checked by Rook
		    			if(Chess.bKingRank == checkRank && Chess.bKingFile < checkFile){
		    				fileCount = Chess.bKingFile+1;
		    				while(fileCount <= checkFile){
		    					if(isProtected(Chess.bKingRank, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					fileCount++;
		    				}
		    			}
		    			if(Chess.bKingRank == checkRank && Chess.bKingFile > checkFile){
		    				fileCount = Chess.bKingFile-1;
		    				while(fileCount >= checkFile){
		    					if(isProtected(Chess.bKingRank, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					fileCount--;
		    				}
		    			}
		    			if(Chess.bKingFile == checkFile && Chess.bKingRank < checkRank){
		    				rankCount = Chess.bKingRank+1;
		    				while(rankCount <= checkRank){
		    					if(isProtected(rankCount, Chess.bKingFile, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount++;
		    				}
		    			}
		    			if(Chess.bKingFile == checkFile && Chess.bKingRank > checkRank){
		    				rankCount = Chess.bKingRank-1;
		    				while(rankCount >= checkRank){
		    					if(isProtected(rankCount, Chess.bKingFile, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount--;
		    				}
		    			}
		    			
		    		}else if(checkPiece == 'B'){
		    			if(checkRank > Chess.bKingRank && checkFile >Chess.bKingFile){
		    				rankCount = Chess.bKingRank+1;
		    				fileCount = Chess.bKingFile+1;
		    				while(rankCount <= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount++;
		    					fileCount++;
		    				}
		    			}
						if(checkRank > Chess.bKingRank && checkFile <Chess.bKingFile){
							rankCount = Chess.bKingRank+1;
		    				fileCount = Chess.bKingFile-1;
		    				while(rankCount <= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount++;
		    					fileCount--;
		    				}		    				
						}
						if(checkRank < Chess.bKingRank && checkFile >Chess.bKingFile){
							rankCount = Chess.bKingRank-1;
		    				fileCount = Chess.bKingFile+1;
		    				while(rankCount >= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount--;
		    					fileCount++;
		    				}
						}
						if(checkRank < Chess.bKingRank && checkFile <Chess.bKingFile){
							rankCount = Chess.bKingRank-1;
		    				fileCount = Chess.bKingFile-1;
		    				while(rankCount >= checkRank){
		    					if(isProtected(rankCount, fileCount, Chess.board)){
		    						checkMate = false;
		    					}
		    					rankCount--;
		    					fileCount--;
		    				}
						}
		    			
		    		}else if(checkPiece == 'N'){
		    			if(isProtected(checkRank, checkFile, Chess.board)){
		    				checkMate = false;
		    			}
		    		}else if(checkPiece == 'p'){
		    			if(!isUnderAttack(checkRank, checkFile, Chess.board)||isProtected(Chess.bKingRank, Chess.bKingFile, Chess.board)){
		    				checkMate = false;
		    			}
		    		}else if(checkPiece == 'Q'){
		    			if(Chess.bKingRank == checkRank || Chess.bKingFile == checkFile){
		    				if(Chess.bKingRank == checkRank && Chess.bKingFile < checkFile){
			    				fileCount = Chess.bKingFile+1;
			    				while(fileCount <= checkFile){
			    					if(isProtected(Chess.bKingRank, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					fileCount++;
			    				}
			    			}
			    			if(Chess.bKingRank == checkRank && Chess.bKingFile > checkFile){
			    				fileCount = Chess.bKingFile-1;
			    				while(fileCount >= checkFile){
			    					if(isProtected(Chess.bKingRank, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					fileCount--;
			    				}
			    			}
			    			if(Chess.bKingFile == checkFile && Chess.bKingRank < checkRank){
			    				rankCount = Chess.bKingRank+1;
			    				while(rankCount <= checkRank){
			    					if(isProtected(rankCount, Chess.bKingFile, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount++;
			    				}
			    			}
			    			if(Chess.bKingFile == checkFile && Chess.bKingRank > checkRank){
			    				rankCount = Chess.bKingRank-1;
			    				while(rankCount >= checkRank){
			    					if(isProtected(rankCount, Chess.bKingFile, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount--;
			    				}
			    			}
		    			}else{
		    				if(checkRank > Chess.bKingRank && checkFile >Chess.bKingFile){
			    				rankCount = Chess.bKingRank+1;
			    				fileCount = Chess.bKingFile+1;
			    				while(rankCount <= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount++;
			    					fileCount++;
			    				}
			    			}
							if(checkRank > Chess.bKingRank && checkFile <Chess.bKingFile){
								rankCount = Chess.bKingRank+1;
			    				fileCount = Chess.bKingFile-1;
			    				while(rankCount <= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount++;
			    					fileCount--;
			    				}		    				
							}
							if(checkRank < Chess.bKingRank && checkFile >Chess.bKingFile){
								rankCount = Chess.bKingRank-1;
			    				fileCount = Chess.bKingFile+1;
			    				while(rankCount >= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount--;
			    					fileCount++;
			    				}
							}
							if(checkRank < Chess.bKingRank && checkFile <Chess.bKingFile){
								rankCount = Chess.bKingRank-1;
			    				fileCount = Chess.bKingFile-1;
			    				while(rankCount >= checkRank){
			    					if(isProtected(rankCount, fileCount, Chess.board)){
			    						checkMate = false;
			    					}
			    					rankCount--;
			    					fileCount--;
			    				}
							}
		    			}
		  
	    		}
	    		
		    	//change the color of the check piece, to check if there's more than one pieces checking 
		    	Chess.board[checkRank][checkFile].setColor('b');
		    	if(isUnderAttack(Chess.bKingRank, Chess.bKingFile, Chess.board)){
		    		return true;
		    	}
		    	Chess.board[checkRank][checkFile].setColor('w');
	    	}
    	}
    	return checkMate;
    }
    /*
     * @param rank  	rank of the square/piece to be checked whether is protected or not
     * @param file  	file of the square/piece to be checked whether is protected or not
     * @param board		board with pieces
     * @return boolean  whether the square/piece is protected by allied pieces
     */
    public static boolean isProtected(int rank, int file, Pieces[][]board){
    	
    	boolean underAttack = false;
    	int rankCount;
    	int fileCount;
    	char color;
    	if(Chess.turn == 'w'){
    		color = 'b';
    	}else{
    		color = 'w';
    	}
    	
    		for(int i = file+1; i <= 7; i++){ //check for rook and queen, right
    			if(board[rank][i]!=null){
    				if(board[rank][i].getColor() != color &&(board[rank][i].getName()=='Q'||board[rank][i].getName()=='R')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		for(int i = file-1; i >= 0; i--){ //left
    			if(board[rank][i]!=null){
    				if(board[rank][i].getColor() != color && (board[rank][i].getName()=='Q'||board[rank][i].getName()=='R')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		for(int i = rank+1; i <= 7; i++){ //down
    			if(board[i][file]!=null){
    				if(board[i][file].getColor() != color &&(board[i][file].getName()=='Q'||board[i][file].getName()=='R')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		for(int i = rank-1; i >= 0; i--){ //up
    			if(board[i][file]!=null){
    				if(board[i][file].getColor() != color &&(board[i][file].getName()=='Q'||board[i][file].getName()=='R')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		
    		rankCount = rank+1; //right, bottom
    		fileCount = file+1;
    		while(rankCount <= 7 && fileCount <= 7){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount++;
    			fileCount++;
    		}
    		
    		rankCount = rank-1; //right, top
    		fileCount = file+1;
    		while(rankCount >= 0 && fileCount <= 7){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount--;
    			fileCount++;
    		}
    		
    		rankCount = rank+1; //left, bottom
    		fileCount = file-1;
    		while(rankCount <= 7 && fileCount >= 0 ){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount++;
    			fileCount--;
    		}
    		
    		rankCount = rank-1;//left, top
    		fileCount = file-1;
    		while(rankCount >= 0 && fileCount >= 0){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount--;
    			fileCount--;
    		}
    		
    		rankCount = rank-2; //Knight, 1
    		fileCount = file+1;
    		if(rankCount >= 0 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank-1; //2
    		fileCount = file+2;
    		if(rankCount >= 0 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank+1; //3
    		fileCount = file+2;
    		if(rankCount <= 7 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank+2; //4
    		fileCount = file+1;
    		if(rankCount <= 7 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank+2; //5
    		fileCount = file-1;
    		if(rankCount <= 7 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank+1; //6
    		fileCount = file-2;
    		if(rankCount <= 7 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank-1; //7
    		fileCount = file-2;
    		if(rankCount >= 0 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank-2; //8
    		fileCount = file-1;
    		if(rankCount >= 0 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    			}
    		}
    		
    		if(color == 'w' && board[rank][file] != null && board[rank][file].getColor() == 'b'){
	    		rankCount = rank-1; //pawn, left top
	    		fileCount = file-1;
	    		if(rankCount >= 0 && fileCount >=0){ 
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    			}
	    		}
	    		
	    		rankCount = rank-1; //pawn, right top
	    		fileCount = file+1;
	    		if(rankCount >= 0 && fileCount <= 7){
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    			}
	    		}
    		}else if(color == 'b' && board[rank][file] != null && board[rank][file].getColor() == 'w'){
    			rankCount = rank+1; //pawn, left bottom
	    		fileCount = file-1;
	    		if(rankCount <= 7 && fileCount >=0){ 
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    			}
	    		}
	    		
	    		rankCount = rank+1; //pawn, right bottom
	    		fileCount = file+1;
	    		if(rankCount <= 7 && fileCount <= 7){
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    			}
	    		}
    		}
    		//black's move, see if pawn can block an attack 
    		if(Chess.turn == 'b' && board[rank][file] == null && rank-1 >= 0 && board[rank-1][file] != null && board[rank-1][file].getColor() == 'b' && board[rank-1][file].getName() == 'p'){
    			underAttack = true;
    		}
    		//white's move
    		if(Chess.turn == 'w' && board[rank][file] == null && rank+1 <= 7 && board[rank+1][file] != null && board[rank+1][file].getColor() == 'w' && board[rank+1][file].getName() == 'p'){
    			underAttack = true;
    		}
    		//black's move, see if pawn can block an attack 
    		if(Chess.turn == 'b' && board[rank][file] == null && rank == 3 && board[1][file] != null && board[1][file].getColor() == 'b' && board[1][file].getName() == 'p'){
    			underAttack = true;
    		}
    		//white's move
    		if(Chess.turn == 'w' && board[rank][file] == null && rank == 4 && board[6][file] != null && board[6][file].getColor() == 'w' && board[6][file].getName() == 'p'){
    			underAttack = true;
    		}
    		
    	return underAttack;
    	
    }
    
    /*
     * @param rank  	rank of the square/piece to be checked whether is attacked or not
     * @param file  	file of the square/piece to be checked whether is attacked or not
     * @param board		board with pieces
     * @return boolean  whether the square/piece is attacked by enemy pieces
     */
    public static boolean isUnderAttack(int rank, int file, Pieces[][]board){
    	boolean underAttack = false;
    	int rankCount;
    	int fileCount;
    	char color;
    	if(board[rank][file] != null){
    		color = board[rank][file].getColor();
    	}else{
    		color = Chess.turn;
    	}
    	
    	
    		for(int i = file+1; i <= 7; i++){ //check for rook and queen, right
    			if(board[rank][i]!=null){
    				if(board[rank][i].getColor() != color &&(board[rank][i].getName()=='Q'||board[rank][i].getName()=='R')){
    					underAttack = true;
    					checkRank = rank;
    					checkFile = i;
    					checkPiece = board[rank][i].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		for(int i = file-1; i >= 0; i--){ //left
    			if(board[rank][i]!=null){
    				if(board[rank][i].getColor() != color && (board[rank][i].getName()=='Q'||board[rank][i].getName()=='R')){
    					underAttack = true;
    					checkRank = rank;
    					checkFile = i;
    					checkPiece = board[rank][i].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		for(int i = rank+1; i <= 7; i++){ //down
    			if(board[i][file]!=null){
    				if(board[i][file].getColor() != color &&(board[i][file].getName()=='Q'||board[i][file].getName()=='R')){
    					underAttack = true;
    					checkRank = i;
    					checkFile = file;
    					checkPiece = board[i][file].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		for(int i = rank-1; i >= 0; i--){ //up
    			if(board[i][file]!=null){
    				if(board[i][file].getColor() != color &&(board[i][file].getName()=='Q'||board[i][file].getName()=='R')){
    					underAttack = true;
    					checkRank = i;
    					checkFile = file;
    					checkPiece = board[i][file].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    		}
    		
    		rankCount = rank+1; //right, bottom
    		fileCount = file+1;
    		while(rankCount <= 7 && fileCount <= 7){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount++;
    			fileCount++;
    		}
    		
    		rankCount = rank-1; //right, top
    		fileCount = file+1;
    		while(rankCount >= 0 && fileCount <= 7){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount--;
    			fileCount++;
    		}
    		
    		rankCount = rank+1; //left, bottom
    		fileCount = file-1;
    		while(rankCount <= 7 && fileCount >= 0 ){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount++;
    			fileCount--;
    		}
    		
    		rankCount = rank-1;//left, top
    		fileCount = file-1;
    		while(rankCount >= 0 && fileCount >= 0){ 
    			if(board[rankCount][fileCount]!=null){
    				if(board[rankCount][fileCount].getColor() != color &&(board[rankCount][fileCount].getName()=='Q'||board[rankCount][fileCount].getName()=='B')){
    					underAttack = true;
    					checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
    					break;
    				}else{
    					break;
    				}
    			}
    			rankCount--;
    			fileCount--;
    		}
    		
    		rankCount = rank-2; //Knight, 1
    		fileCount = file+1;
    		if(rankCount >= 0 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		rankCount = rank-1; //2
    		fileCount = file+2;
    		if(rankCount >= 0 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		rankCount = rank+1; //3
    		fileCount = file+2;
    		if(rankCount <= 7 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		rankCount = rank+2; //4
    		fileCount = file+1;
    		if(rankCount <= 7 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		rankCount = rank+2; //5
    		fileCount = file-1;
    		if(rankCount <= 7 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		rankCount = rank+1; //6
    		fileCount = file-2;
    		if(rankCount <= 7 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		rankCount = rank-1; //7
    		fileCount = file-2;
    		if(rankCount >= 0 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		rankCount = rank-2; //8
    		fileCount = file-1;
    		if(rankCount >= 0 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'N'){
    				underAttack = true;
    				checkRank = rankCount;
					checkFile = fileCount;
					checkPiece = board[rankCount][fileCount].getName();
    			}
    		}
    		
    		if(color == 'w'){
	    		rankCount = rank-1; //pawn, left top
	    		fileCount = file-1;
	    		if(rankCount >= 0 && fileCount >=0){ 
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    				checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
	    			}
	    		}
	    		
	    		rankCount = rank-1; //pawn, right top
	    		fileCount = file+1;
	    		if(rankCount >= 0 && fileCount <= 7){
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    				checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
	    			}
	    		}
    		}else{
    			rankCount = rank+1; //pawn, left bottom
	    		fileCount = file-1;
	    		if(rankCount <= 7 && fileCount >=0){ 
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    				checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
	    			}
	    		}
	    		
	    		rankCount = rank+1; //pawn, right bottom
	    		fileCount = file+1;
	    		if(rankCount <= 7 && fileCount <= 7){
	    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'p'){
	    				underAttack = true;
	    				checkRank = rankCount;
    					checkFile = fileCount;
    					checkPiece = board[rankCount][fileCount].getName();
	    			}
	    		}
    		}
    		
    		rankCount = rank-1; //1
    		fileCount = file+1;
    		if(rankCount >= 0 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank; //2
    		fileCount = file+1;
    		if(fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank+1; //3
    		fileCount = file+1;
    		if(rankCount <= 7 && fileCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank+1; //4
    		fileCount = file;
    		if(rankCount <= 7){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank+1; //5
    		fileCount = file-1;
    		if(rankCount <= 7 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank;//6
    		fileCount = file-1;
    		if(fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank-1; //7
    		fileCount = file-1;
    		if(rankCount >= 0 && fileCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    		
    		rankCount = rank-1; //8
    		fileCount = file;
    		if(rankCount >= 0){
    			if(board[rankCount][fileCount] != null && board[rankCount][fileCount].getColor() != color && board[rankCount][fileCount].getName() == 'K'){
    				underAttack = true;
    			}
    		}
    	
    	return underAttack;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public char getColor(){
        return this.color;
    }

    public char getName(){
        return this.name;
    }
}

