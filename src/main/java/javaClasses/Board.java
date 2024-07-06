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

        blackKing = new Coordinate(0,4);
        board[0][4] = new King(blackKing, this, pieceEnum.BLACK);
        board[0][3] = new Queen(new Coordinate(0,3), this, pieceEnum.BLACK);

        // WHITE
        board[7][0] = new Rook(new Coordinate(7,0), this, pieceEnum.WHITE);
        board[7][7] = new Rook(new Coordinate(7,7), this, pieceEnum.WHITE);

        board[7][1] = new Knight(new Coordinate(7,1), this, pieceEnum.WHITE);
        board[7][6] = new Knight(new Coordinate(7,6), this, pieceEnum.WHITE);

        board[7][2] = new Bishop(new Coordinate(7,2), this, pieceEnum.WHITE);
        board[7][5] = new Bishop(new Coordinate(7,5), this, pieceEnum.WHITE);

        whiteKing = new Coordinate(7,4);
        board[7][4] = new King(whiteKing, this, pieceEnum.WHITE);
        board[7][3] = new Queen(new Coordinate(7,3), this, pieceEnum.WHITE);
    }

    public void removePiece(int i, int j){
        board[i][j] = null;
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

        if(curr.color() == color && curr.canMove(to)){ // Check right player is making a move

            Piece temp = board[to.i][to.j];
            board[to.i][to.j] = curr;
            board[from.i][from.j] = null;

            boolean prevMoved = false;
            if(curr instanceof Rook || curr instanceof Pawn || curr instanceof King)
                prevMoved = curr.moved();

            if(curr.getType() == pieceEnum.KING){
                if(color == pieceEnum.BLACK)
                    blackKing = to;
                else
                    whiteKing = to;

                // Check for Castling
                if(to.j - from.j == 2   ){
                    this.removePiece(from.i,SIZE-1);
                    Rook rook = new Rook(from.i, from.j+1, this, color);
                    rook.setMoved(true);
                }else if(to.j - from.j == -2){
                    this.removePiece(from.i, 0);
                    Rook rook = new Rook(from.i, from.j-1, this, color);
                    rook.setMoved(true);
                }
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
                if(curr instanceof Rook || curr instanceof Pawn || curr instanceof King)
                    curr.setMoved(prevMoved);


                return false;
            }

            return true;
        }

        return false;
    }

    public boolean isCheck(int color){
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

    public void setPiece(int i, int j, Piece piece) {
        board[i][j] = piece;
        if(piece.getType() == pieceEnum.KING){
            if(piece.color() == pieceEnum.WHITE){
                whiteKing = piece.getCoordinate();
            }
            else{
                blackKing = piece.getCoordinate();
            }
        }
    }
    public void setPiece(Piece piece) { board[piece.getCoordinate().i][piece.getCoordinate().j] = piece; }

    public boolean isCheckMate(int color){

        Coordinate king;

        if(color == pieceEnum.WHITE)
            king = whiteKing;
        else
            king = blackKing;

        if(isCheck(color)){
//            for (int i = 0; i < SIZE; i++) {
//                for (int j = 0; j < SIZE; j++) {
//                    if(((King)getPiece(king)).canMove(new Coordinate(i,j))){
//                        return false;
//                    }
//                }
//            }

            for( int i=-1; i<=1; i++ ){
                for( int j=-1; j<=1; j++ ){
                    if( (king.i+i < 0 || king.i+i > SIZE-1 || king.j+j < 0 || king.j+j > SIZE-1) || (i==0 && j==0) )
                        continue;
                    if(isEmpty(king.i+i, king.j+j)) { // Moving to empty Spot
                        boolean prevMoved = getPiece(king.i,king.j).moved();
                        moveKingManually(king, i, j, color); // move King to king.i+i, king.j+j

                        if( !isCheck(color) && !nearOtherKing(new Coordinate(king.i+i, king.j+j)) ) {
                            returnKingManually(king, i, j, prevMoved, color);
                            return false;
                        }
                        returnKingManually(king, i, j, prevMoved, color);

                    } else if (!isEmpty(king.i+i, king.j+j) && color != getPiece(king.i+i, king.j+j).color()){
                        // save this Piece
                        int pieceColor = getPiece(king.i+i,king.j+j).color();
                        int pieceType = getPiece(king.i+i,king.j+j).getType();
                        boolean pieceMoved = getPiece(king.i+i,king.j+j).moved();

                        // kill piece - move king Manually
                        boolean prevMoved = getPiece(king.i,king.j).moved();
                        moveKingManually(king, i, j, color); // move King to king.i+i, king.j+j

                        // see !isCheck || !nearOtherKing
                        if(!isCheck(color) && !nearOtherKing(new Coordinate(king.i+i, king.j+j)) ) {
                            returnKingManually(king, i, j, prevMoved, color);
                            returnPieceManually(king.i+i, king.j+j, pieceMoved, pieceColor, pieceType);
                            return false;
                        }
                        returnKingManually(king, i, j, prevMoved, color);
                        returnPieceManually(king.i+i, king.j+j, pieceMoved, pieceColor, pieceType);

                    }

                }
            }

            return true;
        }
        return false;
    }


    private void moveKingManually(Coordinate king, int i, int j, int color){
        removePiece(king.i, king.j);
        setPiece(king.i+i, king.j+j, new King(king.i+i, king.j+j, this, color));

        if(color == pieceEnum.WHITE)
            whiteKing = new Coordinate(king.i+i, king.j+j);
        else
            blackKing = new Coordinate(king.i+i, king.j+j);
    }


    private void returnKingManually(Coordinate king, int i, int j, boolean prevMoved, int color){
        removePiece(king.i + i, king.j + j);
        setPiece(king.i, king.j, new King(king.i, king.j, this, color));
        getPiece(king.i,king.j).setMoved(prevMoved);

        if(color == pieceEnum.WHITE)
            whiteKing = new Coordinate(king.i, king.j);
        else
            blackKing = new Coordinate(king.i, king.j);
    }

    private void returnPieceManually(int i, int j, boolean prevMoved, int color, int type){
        Piece p;
        if(type == pieceEnum.ROOK){
            p = new Rook(i,j,this,color);
            p.setMoved(prevMoved);
        } else if(type == pieceEnum.QUEEN ){
            p = new Queen(i,j,this,color);
        } else if(type == pieceEnum.BISHOP){
            p = new Bishop(i,j,this,color);
        } else if(type == pieceEnum.KNIGHT){
            p = new Knight(i,j,this,color);
        } else if(type == pieceEnum.PAWN){
            p = new Pawn(i,j,this,color);
        }
        return;
    }

    private boolean nearOtherKing(Coordinate c){
        for( int i=-1; i<=1; i++ ) {
            for (int j = -1; j <= 1; j++) {
                if ((c.i + i < 0 || c.i + i > SIZE - 1 || c.j + j < 0 || c.j + j > SIZE - 1) || (i == 0 && j == 0))
                    continue;
                if(!isEmpty(c.i+i, c.j+j) && getPiece(c.i+i,c.j+j) instanceof King)
                    return true;
            }
        }
        return false;
    }

    public boolean isDraw(int color){
        Coordinate king;

        if(color == pieceEnum.WHITE)
            king = whiteKing;
        else
            king = blackKing;

        if(!isCheck(color)){
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if(getPiece(king).canMove(new Coordinate(i,j))){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
