package pieces;

/**
 * @author Liangyan Ding
 * @authod Tien-Hsueh Li
 */
public class Knight extends Pieces {

    char name = 'N';

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
        // check if moving correctly
        // can only move 2 squares vertically & 1 square horizontally, or
        // 2 squares horizontally & 1 square vertically
        if( !(Math.abs(rank1-rank2)==2&&Math.abs(file1-file2)==1) && !(Math.abs(rank1-rank2)==1&&Math.abs(file1-file2)==2) ){
            System.out.println("Illegal move, please try again.");
            return board;
        }

        // check if the ending position already has a piece, replace it if it's opponents'
        // other wise illegal move
        if(board[rank2][file2] != null){
            if(board[rank2][file2].getColor() == board[rank1][file1].getColor()){
                System.out.println("Illegal move, please try again.");
                return board;
            }
        }

        Pieces newKnight = new Knight();
        // set color for the new Knight
        newKnight.setColor(board[rank1][file1].getColor());
        // move the Piece to new position
        board[rank2][file2] = newKnight;
        board[rank1][file1] = null;

        return board;
    }

    public char getName(){
        return this.name;
    }
}
