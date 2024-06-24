public class Board {
    public final int SIZE = 8;
    private final Piece[][] board;

    public Board(){
        board = new Piece[SIZE][SIZE];
        init();
    }

    private void init(){
        // BLACK PAWN
        for (int i = 0; i < SIZE; i++) {
            board[1][i] = new Pawn(new Coordinate(1,i), this, pieceEnum.BLACK);
        }
        // WHITE PAWN
        for (int i = 0; i < SIZE; i++) {
            board[6][i] = new Pawn(new Coordinate(6,i), this, pieceEnum.WHITE);
        }
        // BLACK
        board[0][0] = new Rook(new Coordinate(0,0), this, pieceEnum.BLACK);
        board[0][7] = new Rook(new Coordinate(0,7), this, pieceEnum.BLACK);

        board[0][1] = new Knight(new Coordinate(0,1), this, pieceEnum.BLACK);
        board[0][6] = new Knight(new Coordinate(0,6), this, pieceEnum.BLACK);

        board[0][2] = new Bishop(new Coordinate(0,2), this, pieceEnum.BLACK);
        board[0][5] = new Bishop(new Coordinate(0,5), this, pieceEnum.BLACK);

        board[0][3] = new King(new Coordinate(0,3), this, pieceEnum.BLACK);
        board[0][4] = new Queen(new Coordinate(0,4), this, pieceEnum.BLACK);

        // WHITE
        board[7][0] = new Rook(new Coordinate(7,0), this, pieceEnum.WHITE);
        board[7][7] = new Rook(new Coordinate(7,7), this, pieceEnum.WHITE);

        board[7][1] = new Knight(new Coordinate(7,1), this, pieceEnum.WHITE);
        board[7][6] = new Knight(new Coordinate(7,6), this, pieceEnum.WHITE);

        board[7][2] = new Bishop(new Coordinate(7,2), this, pieceEnum.WHITE);
        board[7][5] = new Bishop(new Coordinate(7,5), this, pieceEnum.WHITE);

        board[7][3] = new King(new Coordinate(7,3), this, pieceEnum.WHITE);
        board[7][4] = new Queen(new Coordinate(7,4), this, pieceEnum.WHITE);
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
