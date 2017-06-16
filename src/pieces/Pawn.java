package pieces;


import java.io.IOException;


/**
 * @author Liangyan Ding
 * @authod Tien-Hsueh Li
 */
public class Pawn extends Pieces {

    char name = 'p';

    public char getName(){
        return this.name;
    }

    @Override
    public Pieces[][] move(String fileRank1, String fileRank2, Pieces[][] board) throws IOException {

        // convert rankFile to array index
        int file1 = Character.toLowerCase( (int)fileRank1.charAt(0) ) - 97;
        int rank1 = (Character.getNumericValue(fileRank1.charAt(1))-8)*(-1);
        int file2 = Character.toLowerCase( (int)fileRank2.charAt(0) ) - 97;
        int rank2 = (Character.getNumericValue(fileRank2.charAt(1))-8)*(-1);
        Pieces newPawn = new Pawn();

        if(board[rank1][file1].getColor() == 'w'){
            newPawn.setColor('w');
        }else{
            newPawn.setColor('b');
        }

        //check valid or not
        if(newPawn.getColor()=='w'){ //white's move
            if(file1==file2){
                if(board[rank2][file2]!=null){
                    System.out.println("Illegal move, please try again.");
                    return board;
                }
                if(rank1==6){
                    if(rank2!=5&&rank2!=4){
                        System.out.println("Illegal move, please try again.");
                        return board;
                    }
                }else{
                    if(rank2!=rank1-1){
                        System.out.println("Illegal move, please try again");
                        return board;
                    }
                }
            }else if(board[rank2][file2]==null){
                System.out.println("Illegal move, please try again");
                return board;
            }else if(board[rank2][file2].getColor()!='b'){
                System.out.println("Illegal move, please try again.");
                return board;
            }else if(file1==0&&file2==1&&rank2==rank1-1){

            }else if(file1==7&&file2==6&&rank2==rank1-1){

            }else if(file1!=0&&file1!=7&&(file2==file1+1||file2==file1-1)&&rank2==rank1-1){

            }else{
                System.out.println("Illegal move, please try again.");
                return board;
            }
        }else{
            if(file1==file2){
                if(board[rank2][file2]!=null){
                    System.out.println("Illegal move, please try again.");
                    return board;
                }
                if(rank1==1){
                    if(rank2!=2&&rank2!=3){
                        System.out.println("Illegal move, please try again.");
                        return board;
                    }
                }else{
                    if(rank2!=rank1+1){
                        System.out.println("Illegal move, please try again");
                        return board;
                    }
                }
            }else if(board[rank2][file2]==null){
                System.out.println("Illegal move, please try again");
                return board;
            }else if(board[rank2][file2].getColor()!='w'){
                System.out.println("Illegal move, please try again.");
                return board;
            }else if(file1==0&&file2==1&&rank2==rank1+1){

            }else if(file1==7&&file2==6&&rank2==rank1+1){

            }else if(file1!=0&&file1!=7&&(file2==file1+1||file2==file1-1)&&rank2==rank1+1){

            }else{
                System.out.println("Illegal move, please try again.");
                return board;
            }
        }
        
        	
        board[rank2][file2] = newPawn;
        board[rank1][file1] = null;

        return board;
    }

    
}
