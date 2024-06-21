import java.util.ArrayList;

public class Board {

    private ArrayList<ArrayList<Square>> board;

    public Board(){
        board = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<Square>(8));
        }
    }

    private void init(){
        for (int i = 0; i < 8; i++) {
            board.get(1).set(i, new Square(pieceEnum.BLACK, pieceEnum.PAWN, i, 1));
        }
        for (int i = 0; i < 8; i++) {
            board.get(6).set(i, new Square(pieceEnum.WHITE, pieceEnum.PAWN, i, 6));
        }
        // BLACK
        board.get(0).set(0, new Square(pieceEnum.BLACK, pieceEnum.ROOK, 0,0));
        board.get(0).set(7, new Square(pieceEnum.BLACK, pieceEnum.ROOK, 7,0));

        board.get(0).set(1, new Square(pieceEnum.BLACK, pieceEnum.KNIGHT, 1, 0));
        board.get(0).set(6, new Square(pieceEnum.BLACK, pieceEnum.KNIGHT, 6, 0));

        board.get(0).set(2, new Square(pieceEnum.BLACK, pieceEnum.BISHOP, 2, 0));
        board.get(0).set(5, new Square(pieceEnum.BLACK, pieceEnum.BISHOP, 5, 0));

        board.get(0).set(3, new Square(pieceEnum.BLACK, pieceEnum.QUEEN, 3, 0));
        board.get(0).set(4, new Square(pieceEnum.BLACK, pieceEnum.KNIGHT, 4, 0));



        // WHITE
        board.get(7).set(0, new Square(pieceEnum.WHITE, pieceEnum.ROOK, 0,7));
        board.get(7).set(7, new Square(pieceEnum.WHITE, pieceEnum.ROOK, 7,7));

        board.get(7).set(1, new Square(pieceEnum.WHITE, pieceEnum.KNIGHT, 1, 7));
        board.get(7).set(6, new Square(pieceEnum.WHITE, pieceEnum.KNIGHT, 6, 7));

        board.get(7).set(2, new Square(pieceEnum.WHITE, pieceEnum.BISHOP, 2, 7));
        board.get(7).set(5, new Square(pieceEnum.WHITE, pieceEnum.BISHOP, 5, 7));

        board.get(7).set(3, new Square(pieceEnum.WHITE, pieceEnum.QUEEN, 3, 7));
        board.get(7).set(4, new Square(pieceEnum.WHITE, pieceEnum.KNIGHT, 4, 7));


    }

}
