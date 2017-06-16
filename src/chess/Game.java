package chess;

import pieces.*;

/**
 * @author Liangyan Ding
 * @author Tien-Hsueh Li
 */
class Game {

    Pieces[][] initialize(){

        Pieces[][] board = new Pieces[8][8];

        // White Pieces
        Pieces wKing = new King();
        Pieces wQueen = new Queen();
        Pieces wBishop = new Bishop();
        Pieces wPawn = new Pawn();
        Pieces wRook = new Rook();
        Pieces wKnight = new Knight();

        // Black Pieces
        Pieces bKing = new King();
        Pieces bQueen = new Queen();
        Pieces bBishop = new Bishop();
        Pieces bPawn = new Pawn();
        Pieces bRook = new Rook();
        Pieces bKnight = new Knight();

        // Put pieces into the board
        for(int i=0;i<8;i++){
            board[1][i] = bPawn;
            board[1][i].setColor('b');
            board[6][i] = wPawn;
            board[6][i].setColor('w');
        }

        board[0][0] = bRook;
        board[0][0].setColor('b');
        board[0][7] = bRook;
        board[0][7].setColor('b');
        board[7][0] = wRook;
        board[7][0].setColor('w');
        board[7][7] = wRook;
        board[7][7].setColor('w');

        board[0][1] = bKnight;
        board[0][1].setColor('b');
        board[0][6] = bKnight;
        board[0][6].setColor('b');
        board[7][1] = wKnight;
        board[7][1].setColor('w');
        board[7][6] = wKnight;
        board[7][6].setColor('w');

        board[0][2] = bBishop;
        board[0][2].setColor('b');
        board[0][5] = bBishop;
        board[0][5].setColor('b');
        board[7][2] = wBishop;
        board[7][2].setColor('w');
        board[7][5] = wBishop;
        board[7][5].setColor('w');

        board[0][3] = bQueen;
        board[0][3].setColor('b');
        board[7][3] = wQueen;
        board[7][3].setColor('w');

        board[0][4] = bKing;
        board[0][4].setColor('b');
        board[7][4] = wKing;
        board[7][4].setColor('w');

        return board;
    }

    void printBoard(Pieces[][] board){
        int count = 8;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board [i][j]!=null){
                    System.out.print(board[i][j].getColor());
                    System.out.print(board[i][j].getName());
                    System.out.print(' ');
                }else{
                    if((i+j)%2==0){
                        System.out.print("   ");
                    }else{
                        System.out.print("## ");
                    }
                }
            }
            System.out.print(count);
            count--;
            System.out.println();
        }
        System.out.print(" a  b  c  d  e  f  g  h\n\n");
    }

    Game(){


    }
}
