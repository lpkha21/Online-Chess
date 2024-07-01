package javaClasses;

public class Board {
    public final int SIZE = 8;
    private final Piece[][] board;

    Coordinate whiteKing;
    Coordinate blackKing;

    public Board(boolean doInit){
        board = new Piece[SIZE][SIZE];
        if(doInit)
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

        blackKing = new Coordinate(0,3);
        board[0][3] = new King(blackKing, this, pieceEnum.BLACK);
        board[0][4] = new Queen(new Coordinate(0,4), this, pieceEnum.BLACK);

        // WHITE
        board[7][0] = new Rook(new Coordinate(7,0), this, pieceEnum.WHITE);
        board[7][7] = new Rook(new Coordinate(7,7), this, pieceEnum.WHITE);

        board[7][1] = new Knight(new Coordinate(7,1), this, pieceEnum.WHITE);
        board[7][6] = new Knight(new Coordinate(7,6), this, pieceEnum.WHITE);

        board[7][2] = new Bishop(new Coordinate(7,2), this, pieceEnum.WHITE);
        board[7][5] = new Bishop(new Coordinate(7,5), this, pieceEnum.WHITE);

        whiteKing = new Coordinate(7,3);
        board[7][3] = new King(whiteKing, this, pieceEnum.WHITE);
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

    //tu shedzlo moqmedeba true, tu ver shedzlo moqmedeba false
    // gadaecema saidan sad unda gadavides figura da ra feris motamashe cdilobs svlis gaketebas
    public boolean makeMove(Coordinate from, Coordinate to, int color){
        Piece curr = getPiece(from);

        if(curr == null)
            return false;

        if(curr.color() == color && curr.canMove(to)){

            Piece temp = board[to.i][to.j];
            board[to.i][to.j] = curr;
            board[from.i][from.j] = null;

            if(curr.getType() == pieceEnum.KING){
                if(color == pieceEnum.BLACK)
                    blackKing = to;
                else
                    whiteKing = to;
            }

            curr.updateCoordinate(to);

            if(isCheck(color)){

                board[from.i][from.j] = curr;
                board[to.i][to.j] = temp;

                if(curr.getType() == pieceEnum.KING){
                    if(color == pieceEnum.BLACK)
                        blackKing = from;
                    else
                        whiteKing = from;
                }

                curr.updateCoordinate(from);

                return false;
            }

            return true;
        }else{
            return false;
        }
    }

    private boolean isCheck(int color){
        Coordinate king;

        if(color == pieceEnum.WHITE)
            king = whiteKing;
        else
            king = blackKing;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Piece curr = getPiece(i,j);
                if(curr != null && curr.color() != color && curr.canMove(king)){
                    return true;
                }
            }
        }
        return false;
    }

    public void setPiece(int i, int j, Piece piece) { board[i][j] = piece; }
    public void setPiece(Piece piece) { board[piece.getCoordinate().i][piece.getCoordinate().j] = piece; }

}
