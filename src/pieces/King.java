package pieces;

/**
 * @author Liangyan Ding
 * @authod Tien-Hsueh Li
 */
public class King extends Pieces {

    char name = 'K';

    @Override
    public Pieces[][] move(String fileRank1, String fileRank2, Pieces[][] board) {
    	// convert rankFile to array index
        int file1 = Character.toLowerCase( (int)fileRank1.charAt(0) ) - 97;
        int rank1 = (Character.getNumericValue(fileRank1.charAt(1))-8)*(-1);
        int file2 = Character.toLowerCase( (int)fileRank2.charAt(0) ) - 97;
        int rank2 = (Character.getNumericValue(fileRank2.charAt(1))-8)*(-1);
        
        if(board[rank1][file1].getColor()=='w'){
        	King newKing = new King();
        	newKing.setColor('w');
        	if(rank2>=0&&rank2<=7&&file2>=0&&file2<=7&&(Math.abs(rank2-rank1)==1||Math.abs(rank2-rank1)==0)&&(Math.abs(file2-file1)==1||Math.abs(file2-file1)==0)&&(board[rank2][file2]==null||board[rank2][file2].getColor()=='b')){
        		board[rank2][file2] = newKing;
        		board[rank1][file1] = null;
        		return board;
        	}else{
        		System.out.println("Illegal move, please try again.");
                return board;
        	}
        }
        if(board[rank1][file1].getColor()=='b'){
        	King newKing = new King();
        	newKing.setColor('b');
        	if(rank2>=0&&rank2<=7&&file2>=0&&file2<=7&&(Math.abs(rank2-rank1)==1||Math.abs(rank2-rank1)==0)&&(Math.abs(file2-file1)==1||Math.abs(file2-file1)==0)&&(board[rank2][file2]==null||board[rank2][file2].getColor()=='w')){
        		board[rank2][file2] = newKing;
        		board[rank1][file1] = null;
        		return board;
        	}else{
        		System.out.println("Illegal move, please try again.");
                return board;
        	}
        }
        System.out.println("Illegal move, please try again.");
        return board;
    }

    public char getName(){
        return this.name;
    }

}
