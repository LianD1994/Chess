package pieces;

/**
 * @author Liangyan Ding
 * @authod Tien-Hsueh Li
 */
public class Bishop extends Pieces {

    char name = 'B';

    @Override
    public Pieces[][] move(String fileRank1, String fileRank2, Pieces[][] board) {

        // convert rankFile to array index
        int file1 = Character.toLowerCase( (int)fileRank1.charAt(0) ) - 97;
        int rank1 = (Character.getNumericValue(fileRank1.charAt(1))-8)*(-1);
        int file2 = Character.toLowerCase( (int)fileRank2.charAt(0) ) - 97;
        int rank2 = (Character.getNumericValue(fileRank2.charAt(1))-8)*(-1);

        // Check if the move is valid
        // ----------------------------
        // check if the starting and ending position are the same
        if(file1 == file2 && rank1 == rank2) {
            System.out.println("Illegal move, please try again.");
            return board;
        }
        // check if not diagonally
        if(Math.abs(rank1-rank2) != Math.abs(file1-file2)){
            System.out.println("Illegal move, please try again.");
            return board;
        }
        // check if there is piece in between start and end position
        if(rank1 > rank2 && file1 > file2){// up & left
            for(int i=file2+1, j=rank2+1; i<file1;i++, j++){
                if(board[j][i] != null){
                    System.out.println("Illegal move, please try again.");
                    return board;
                }
            }
        }
        if(rank1 > rank2 && file1 < file2){// up & right
            for(int i=file2-1, j=rank2+1; i>file1;i--, j++){
                if(board[j][i] != null){
                    System.out.println("Illegal move, please try again.");
                    return board;
                }
            }
        }
        if(rank1 < rank2 && file1 < file2){// down & right
            for(int i=file1+1, j=rank1+1; i<file2;i++, j++){
                if(board[j][i] != null){
                    System.out.println("Illegal move, please try again.");
                    return board;
                }
            }
        }
        if(rank1 < rank2 && file1 > file2){// down & left
            for(int i=file2+1, j=rank1+1; i<file1;i++, j++){
                if(board[j][i] != null){
                    System.out.println("Illegal move, please try again.");
                    return board;
                }
            }
        }

        // check if the ending position already has a piece, replace it if it's opponents'
        // other wise illegal move
        if(board[rank2][file2] != null){
            if(board[rank2][file2].getColor() == board[rank1][file1].getColor()){
                System.out.println("Illegal move, please try again.");
                return board;
            }
        }

        Pieces newBishop = new Bishop();
        // set color for the new Rook
        newBishop.setColor(board[rank1][file1].getColor());
        // move the piece to new position
        board[rank2][file2] = newBishop;
        board[rank1][file1] = null;
        return board;
    }

    public char getName(){
        return this.name;
    }


}
