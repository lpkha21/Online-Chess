package javaClasses;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.abs;

public class Queen implements Piece{
    int color;

    final int type = pieceEnum.QUEEN;

    Coordinate cord;

    Board board;

    public Queen(Coordinate coordinate, Board board, int col) {
        cord = coordinate;
        this.board = board;
        this.color = col;
    }

    @Override
    public boolean canMove(Coordinate c) {
        int dx = c.j - this.cord.j;
        int dy = c.i - this.cord.i;

        if( dx == 0 && dy == 0 ) // move to same block
            return false;

        if ( abs(dx) != abs(dy) ) // move is not diagonal
            return false;

        if(cord.i != c.i && cord.j != c.j) // move is not horizontal nor vertical
            return false;

        // moving to a same colored piece
        if( !board.isEmpty(c.i,c.j) && board.getPiece(c.i, c.j).color() == this.color )
            return false;

        // Check diagonals paths

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

        // Check vertical path
        if(cord.j == c.j){
            if(cord.i > c.i){
                for (int i = c.i; i < cord.i; i++) {
                    if(!board.isEmpty(i, c.j)){
                        return false;
                    }
                }
            }else{
                for(int i = cord.i; i < c.i; i++){
                    if(!board.isEmpty(i, c.j)){
                        return false;
                    }
                }
            }
        }

        // Check horizontal path
        if(cord.i == c.i) {
            if (cord.j > c.j) {
                for(int j = c.j; j <= cord.j; j++){
                    if(!board.isEmpty(c.i, j)){
                        return false;
                    }
                }
            }else{
                for(int j = cord.j; j <= c.j; j++){
                    if(!board.isEmpty(c.i, j)){
                        return false;
                    }
                }
            }
        }


        return true;
    }

    @Override
    public int color() {
        return color;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public Coordinate getCoordinate() {
        return cord;
    }

    @Override
    public void updateCoordinate(Coordinate c) {
        cord = c;
    }
}
