package javaClasses;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.abs;

public class Bishop implements Piece{

    int color;
    final int type = pieceEnum.BISHOP;
    Coordinate cord;
    Board board;
    public Bishop(Coordinate cord, Board board, int color){
        this.cord = cord;
        this.color = color;
        this.board = board;
    }
    @Override
    public boolean canMove(Coordinate c) {
        int dx = c.j - this.cord.j;
        int dy = c.i - this.cord.i;

        if( dx == 0 && dy == 0 ) // move to same block
            return false;

        if ( abs(dx) != abs(dy) ) // move is not diagonal
            return false;

        // moving to a same colored piece
        if( !board.isEmpty(c.i,c.j) && board.getPiece(c.i, c.j).color() == this.color )
            return false;


        // check the paths to c
        if(dx > 0 && dy > 0) { // 1. bottom right
            for (int row = this.cord.i; row < this.cord.i + dy; row++) {
                for (int col = this.cord.j; col < this.cord.j + dx; col++) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        } else if(dx > 0 && dy < 0) { // 2. top right
            for (int row = this.cord.i; row > this.cord.i + dy; row--) {
                for (int col = this.cord.j; col < this.cord.j + dx; col++) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        } else if(dx < 0 && dy > 0) { // 3. bottom left
            for (int row = this.cord.i; row < this.cord.i + dy; row++) {
                for (int col = this.cord.j; col > this.cord.j + dx; col--) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        } else { // 4. top left
            for (int row = this.cord.i; row > this.cord.i + dy; row--) {
                for (int col = this.cord.j; col > this.cord.j + dx; col--) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int color() {
        return this.color;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.cord;
    }

    @Override
    public void updateCoordinate(Coordinate c) {
        this.cord = c;
    }
}
