import java.util.ArrayList;

public class Board {

    private ArrayList<ArrayList<Square>> board;
    public Board(){
        board = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<Square>(8));
        }
    }



}
