import java.util.ArrayList;

public class Board {
    static final int SIZE = 8;
    Piece[][] board;
    //dasaweria bordis sheqmna 8X8 nalebit shevseba da 32 figuris sheqmna da ganlageba
    public Board(){
        board = new Piece[8][8];

    }

    public boolean isEmpty(int i, int j){
        return board[i][j] == null;
    }
}
