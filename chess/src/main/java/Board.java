import java.util.ArrayList;

public class Board {
    static final int SIZE = 8;
    Piece[][] board;
    //dasaweria bordis sheqmna 8X8 nalebit shevseba da 32 figuris sheqmna da ganlageba
    public Board(){
        board = new Piece[8][8];
    }

    public boolean isEmpty(Coordinate cord){
        return board[cord.i][cord.j] == null;
    }

}
