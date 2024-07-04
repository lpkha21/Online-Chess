package javaClasses;

import static java.lang.Math.abs;

public class Queen implements Piece{
    int color;

    final int type = pieceEnum.QUEEN;

    Coordinate cord;

    Board board;

    public Queen(Coordinate coordinate, Board board, int col) {
        cord = coordinate;
        this.board = board;
        this.color = col;
        board.setPiece(getCoordinate().i, getCoordinate().j,this);
    }
    public Queen(int i, int j, Board board, int col){
        this(new Coordinate(i,j), board, col);
    }

    @Override
    public boolean canMove(Coordinate c) {
        int dx = c.j - this.cord.j;
        int dy = c.i - this.cord.i;

        if( dx == 0 && dy == 0 ) // move to same block
            return false;

        // moving to a same colored piece
        if( !board.isEmpty(c.i,c.j) &&
                (board.getPiece(c.i, c.j).color() == this.color || board.getPiece(c.i,c.j).getType() == pieceEnum.KING ) )
            return false;

        if ( ( abs(dx) != abs(dy) ) || ( cord.i != c.i && cord.j != c.j ) )
            return false;

        // check the paths to c
        if(dx > 0 && dy > 0) { // 1. bottom right
            for (int row = this.cord.i+1; row < this.cord.i + dy; row++) {
                for (int col = this.cord.j+1; col < this.cord.j + dx; col++) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        } else if(dx > 0 && dy < 0) { // 2. top right
            for (int row = this.cord.i-1; row > this.cord.i + dy; row--) {
                for (int col = this.cord.j+1; col < this.cord.j + dx; col++) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        } else if(dx < 0 && dy > 0) { // 3. bottom left
            for (int row = this.cord.i+1; row < this.cord.i + dy; row++) {
                for (int col = this.cord.j-1; col > this.cord.j + dx; col--) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        } else { // 4. top left
            for (int row = this.cord.i-1; row > this.cord.i + dy; row--) {
                for (int col = this.cord.j-1; col > this.cord.j + dx; col--) {
                    if (!board.isEmpty(row, col)) {
                        return false;
                    }
                }
            }
        }

        // Check horizontal path
        if(cord.i == c.i) {
            if (cord.j > c.j) {
                for(int j = cord.j-1; j > c.j; j--){
                    if(!board.isEmpty(c.i, j)){
                        return false;
                    }
                }
            }else{
                for(int j = cord.j+1; j < c.j; j++){
                    if(!board.isEmpty(c.i, j)){
                        return false;
                    }
                }
            }
        }

        // Check vertical path
        if(cord.j == c.j){
            if(cord.i > c.i){
                for (int i = cord.i-1; i > c.i; i--) {
                    if(!board.isEmpty(i, c.j)){
                        return false;
                    }
                }
            }else{
                for(int i = cord.i+1; i < c.i; i++){
                    if(!board.isEmpty(i, c.j)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canMove(int i, int j){
        return this.canMove(new Coordinate(i,j));
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
