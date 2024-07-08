package javaClasses;

import java.nio.file.Path;
import java.util.ArrayList;

public class Board {
    public final int SIZE = 8;
    private final Piece[][] board;

    Coordinate whiteKing;
    Coordinate blackKing;

    public ArrayList<Piece> whitePieces;
    public ArrayList<Piece> blackPieces;

    private static final int DRAW_INT = 5;

    private Piece whiteKingChecker;
    private Piece blackKingChecker;


    public Board(boolean doInit) {
        board = new Piece[SIZE][SIZE];
        blackPieces = new ArrayList<Piece>();
        whitePieces = new ArrayList<Piece>();

        if (doInit)
            init();
    }

    public Board() {
        board = new Piece[SIZE][SIZE];
        blackPieces = new ArrayList<Piece>();
        whitePieces = new ArrayList<Piece>();

        init();
    }

    private void init() {
        // BLACK PAWN
        for (int i = 0; i < SIZE; i++) {
            board[1][i] = new Pawn(new Coordinate(1, i), this, pieceEnum.BLACK);
        }
        // WHITE PAWN
        for (int i = 0; i < SIZE; i++) {
            board[6][i] = new Pawn(new Coordinate(6, i), this, pieceEnum.WHITE);
        }
        // BLACK
        board[0][0] = new Rook(new Coordinate(0, 0), this, pieceEnum.BLACK);
        board[0][7] = new Rook(new Coordinate(0, 7), this, pieceEnum.BLACK);

        board[0][1] = new Knight(new Coordinate(0, 1), this, pieceEnum.BLACK);
        board[0][6] = new Knight(new Coordinate(0, 6), this, pieceEnum.BLACK);

        board[0][2] = new Bishop(new Coordinate(0, 2), this, pieceEnum.BLACK);
        board[0][5] = new Bishop(new Coordinate(0, 5), this, pieceEnum.BLACK);

        blackKing = new Coordinate(0, 4);
        board[0][4] = new King(blackKing, this, pieceEnum.BLACK);
        board[0][3] = new Queen(new Coordinate(0, 3), this, pieceEnum.BLACK);

        // WHITE
        board[7][0] = new Rook(new Coordinate(7, 0), this, pieceEnum.WHITE);
        board[7][7] = new Rook(new Coordinate(7, 7), this, pieceEnum.WHITE);

        board[7][1] = new Knight(new Coordinate(7, 1), this, pieceEnum.WHITE);
        board[7][6] = new Knight(new Coordinate(7, 6), this, pieceEnum.WHITE);

        board[7][2] = new Bishop(new Coordinate(7, 2), this, pieceEnum.WHITE);
        board[7][5] = new Bishop(new Coordinate(7, 5), this, pieceEnum.WHITE);

        whiteKing = new Coordinate(7, 4);
        board[7][4] = new King(whiteKing, this, pieceEnum.WHITE);
        board[7][3] = new Queen(new Coordinate(7, 3), this, pieceEnum.WHITE);
    }

    public void removePiece(int i, int j) {
        board[i][j] = null;
    }

    public boolean isEmpty(Coordinate cord) {
        return board[cord.i][cord.j] == null;
    }

    public boolean isEmpty(int i, int j) {
        return board[i][j] == null;
    }

    public Piece getPiece(Coordinate cord) {
        return board[cord.i][cord.j];
    }

    public Piece getPiece(int i, int j) {
        return board[i][j];
    }

    public boolean promotion(Coordinate c){

        if(getPiece(c) == null || getPiece(c).getType() != pieceEnum.PAWN)
            return false;

        return ((Pawn) getPiece(c)).isPromoted();
    }

    //tu shedzlo moqmedeba true, tu ver shedzlo moqmedeba false
    // gadaecema saidan sad unda gadavides figura da ra feris motamashe cdilobs svlis gaketebas
    public boolean makeMove(Coordinate from, Coordinate to, int color) {
        Piece curr = getPiece(from);

        if (curr == null)
            return false;

        if (curr.color() == color && curr.canMove(to)) { // Check that the right player is making a move

            Piece temp = board[to.i][to.j];
            if(temp != null){
                if(temp.color() == pieceEnum.WHITE)
                    whitePieces.remove(temp);
                else
                    blackPieces.remove(temp);
            }

            board[to.i][to.j] = curr;
            board[from.i][from.j] = null;

            boolean prevMoved = false;
            if (curr instanceof Rook || curr instanceof Pawn || curr instanceof King)
                prevMoved = curr.moved();

            if (curr.getType() == pieceEnum.KING) {
                if (color == pieceEnum.BLACK)
                    blackKing = to;
                else
                    whiteKing = to;

                // Check for Castling
                if (to.j - from.j == 2) {
                    this.removePiece(from.i, SIZE - 1);
                    Rook rook = new Rook(from.i, from.j + 1, this, color);
                    rook.setMoved(true);
                    if(color == pieceEnum.WHITE)
                        whitePieces.remove(whitePieces.size()-1);
                    else
                        blackPieces.remove(blackPieces.size()-1);
                } else if (to.j - from.j == -2) {
                    this.removePiece(from.i, 0);
                    Rook rook = new Rook(from.i, from.j - 1, this, color);
                    rook.setMoved(true);
                    if(color == pieceEnum.WHITE)
                        whitePieces.remove(whitePieces.size()-1);
                    else
                        blackPieces.remove(blackPieces.size()-1);
                }
            }

            curr.updateCoordinate(to);

            if (isCheck(color)) {

                board[from.i][from.j] = curr;
                board[to.i][to.j] = temp;
                if(temp != null){
                    if(temp.color() == pieceEnum.WHITE)
                        whitePieces.add(temp);
                    else
                        blackPieces.add(temp);
                }

                if (curr.getType() == pieceEnum.KING) {
                    if (color == pieceEnum.BLACK)
                        blackKing = from;
                    else
                        whiteKing = from;
                }

                curr.updateCoordinate(from);
                if(curr instanceof Pawn)
                    ((Pawn)getPiece(from.i,from.j)).depromote();
                if (curr instanceof Rook || curr instanceof Pawn || curr instanceof King)
                    curr.setMoved(prevMoved);

                return false;
            }

            checkForCheckers(color);

            if(curr.getType() == pieceEnum.PAWN && ((Pawn)curr).isPromoted() ){ // Remove promoted Pawn from whitePieces/blackPieces
                if(curr.color() == pieceEnum.WHITE)
                    whitePieces.remove(curr);
                else
                    blackPieces.remove(curr);

            }

            return true;
        }

        return false;
    }

    private void checkForCheckers(int color){
        if(color == pieceEnum.WHITE){
            for(Piece p : whitePieces){
                if(p.canMove(blackKing)) {
                    blackKingChecker = p;
                    whiteKingChecker = null;
                }
            }
        } else {
            for(Piece p : blackPieces){
                if(p.canMove(whiteKing)) {
                    whiteKingChecker = p;
                    blackKingChecker = null;
                }
            }
        }
    }

    public boolean isCheck(int color) {
        Coordinate king;

        if (color == pieceEnum.WHITE)
            king = whiteKing;
        else
            king = blackKing;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Piece curr = getPiece(i, j);
                if (curr != null && curr.color() != color && curr.canMove(king)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPiece(int i, int j, Piece piece) {
        board[i][j] = piece;
        if (piece.getType() == pieceEnum.KING) {
            if (piece.color() == pieceEnum.WHITE) {
                whiteKing = piece.getCoordinate();
            } else {
                blackKing = piece.getCoordinate();
            }
        }
    }

    public void setPiece(Piece piece) {
        board[piece.getCoordinate().i][piece.getCoordinate().j] = piece;
    }

    public boolean isCheckMate(int color) {

        Coordinate king;
        if (color == pieceEnum.WHITE)
            king = whiteKing;
        else
            king = blackKing;

        if (isCheck(color)) {
            assert(blackKingChecker != null || whiteKingChecker != null);

            boolean kingStuck =  moveKingAndSeeIfChecked(king, color);
            if(!kingStuck)
                return false;

            boolean checkerCantBeKilled = !checkerKillable(color);
            if(!checkerCantBeKilled)
                return false;

            boolean kingCantBeCovered = !kingCoverable(color);
            if(!kingCantBeCovered)
                return false;

            return true;
        }
        return false;
    }

    private boolean checkerKillable(int color){
        if(color == pieceEnum.WHITE){
            for(Piece p : whitePieces){
                if(p.getType() == pieceEnum.KING)
                    continue;
                if(p.canMove(whiteKingChecker.getCoordinate()) && p.getType() != pieceEnum.KING)
                    return true;
            }
        } else {
            for(Piece p : blackPieces){
                if(p.getType() == pieceEnum.KING)
                    continue;
                if(p.canMove(blackKingChecker.getCoordinate()) && p.getType() != pieceEnum.KING)
                    return true;
            }
        }
        return false;
    }

    private boolean kingCoverable(int color){
        if(color == pieceEnum.WHITE){
            ArrayList<Coordinate> paths = whiteKingChecker.getCheckPath(whiteKing);
            for(Piece p : whitePieces){
                for(Coordinate c : paths){
                    if(p.canMove(c))
                        return true;
                }
            }
        } else {
            ArrayList<Coordinate> paths = blackKingChecker.getCheckPath(blackKing);
            for(Piece p : blackPieces){
                for(Coordinate c : paths){
                    if(p.canMove(c))
                        return true;
                }
            }
        }
        return false;
    }



    private void moveKingManually(Coordinate king, int i, int j, int color) {
        if(!isEmpty(king.i+i, king.j+j) && getPiece(king.i+i, king.j+j).color() == getPiece(king.i,king.j).color())
            return;
        removePiece(king.i, king.j);
        setPiece(king.i + i, king.j + j, new King(king.i + i, king.j + j, this, color));

        if(color == pieceEnum.WHITE)
            whitePieces.remove(whitePieces.size()-1);
        else
            blackPieces.remove(blackPieces.size()-1);

        if (color == pieceEnum.WHITE)
            whiteKing = new Coordinate(king.i + i, king.j + j);
        else
            blackKing = new Coordinate(king.i + i, king.j + j);
    }

    private void returnKingManually(Coordinate king, int i, int j, boolean prevMoved, int color) {
        removePiece(king.i + i, king.j + j);
        setPiece(king.i, king.j, new King(king.i, king.j, this, color));
        getPiece(king.i, king.j).setMoved(prevMoved);

        if (color == pieceEnum.WHITE) {
            whiteKing = new Coordinate(king.i, king.j);
            whitePieces.remove(whitePieces.size() - 1);
        } else{
            blackKing = new Coordinate(king.i, king.j);
            blackPieces.remove(blackPieces.size()-1);
        }
    }

/*    private void returnPieceManually(int i, int j, boolean prevMoved, int color, int type){
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
*/

    private boolean nearOtherKing(Coordinate c) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((c.i + i < 0 || c.i + i > SIZE - 1 || c.j + j < 0 || c.j + j > SIZE - 1) || (i == 0 && j == 0))
                    continue;
                if (!isEmpty(c.i + i, c.j + j) && getPiece(c.i + i, c.j + j) instanceof King)
                    return true;
            }
        }
        return false;
    }

    private boolean moveKingAndSeeIfChecked(Coordinate king, int color){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((king.i + i < 0 || king.i + i > SIZE - 1 || king.j + j < 0 || king.j + j > SIZE - 1) || (i == 0 && j == 0))
                    continue;

                Piece killedPiece = getPiece(king.i + i, king.j + j);
                boolean prevMoved = getPiece(king.i, king.j).moved();
                moveKingManually(king, i, j, color); // move King to king.i+i, king.j+j

                if (!isCheck(color) && !nearOtherKing(new Coordinate(king.i + i, king.j + j))) {
                    returnKingManually(king, i, j, prevMoved, color);
                    this.board[king.i + i][king.j + j] = killedPiece;
                    return false;
                }
                returnKingManually(king, i, j, prevMoved, color);
                this.board[king.i + i][king.j + j] = killedPiece;

            }
        }
        return true;
    }
    public boolean isDraw(int color) {

        if (blackPieces.size() == 1 && whitePieces.size() == 1) // Only 2 Kings Left
            return true;
        else if (blackPieces.size() + whitePieces.size() == 3) { // 2 Kings + 1 Bishop/Knight
            ArrayList<Piece> ar;
            if (blackPieces.size() > whitePieces.size())
                ar = blackPieces;
            else ar = whitePieces;

            for (Piece p : ar) {
                if (p instanceof Bishop || p instanceof Knight)
                    return true;
            }
        } else if (blackPieces.size() + whitePieces.size() == 4) { // 2 Kings + 2 Knights
            ArrayList<Piece> ar;
            if (blackPieces.size() >= whitePieces.size())
                ar = blackPieces;
            else ar = whitePieces;

            int knightCount = 0;
            for (Piece p : ar) {
                if (p instanceof Knight)
                    knightCount++;
            }
            if (knightCount == 2)
                return true;
        }

        if (!isCheck(color) && (blackPieces.size() < DRAW_INT || whitePieces.size() < DRAW_INT) ) { // Number of pieces is small

                Coordinate king;
                if (color == pieceEnum.WHITE)
                    king = whiteKing;
                else
                    king = blackKing;

                boolean kingCantMove = false;
                kingCantMove = moveKingAndSeeIfChecked(king, color);

                if (kingCantMove) { // King is Stuck
                    ArrayList<Piece> myPieces;

                    if (color == pieceEnum.WHITE)
                        myPieces = whitePieces;
                    else
                        myPieces = blackPieces;


                    if (myPieces.size() == 1) // only King left
                        return true;

                    // iterate over myPieces and see if any piece can move anywhere
                    for(Piece p : myPieces){
                        if(!p.isStuck())
                            return false;
                    }
                    return true;
                }
        }
        return false;
    }



}
