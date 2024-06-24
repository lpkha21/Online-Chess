import java.util.ArrayList;

public class Board {
    static final int SIZE = 8;
    private final Piece[][] board;
    //dasaweria bordis sheqmna 8X8 nalebit shevseba da 32 figuris sheqmna da ganlageba
    public Board(){
        board = new Piece[SIZE][SIZE];
    }

    public boolean isEmpty(Coordinate cord){
        return board[cord.i][cord.j] == null;
    }

    public boolean isEmpty(int i, int j){
        return board[i][j] == null;
    }
    public Piece getPiece(Coordinate cord){
        return board[cord.i][cord.j];
    }

    public Piece getPiece(int i, int j){
        return board[i][j];
    }

}
