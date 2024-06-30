package javaClasses;

import static java.lang.Math.abs;

public class Knight implements Piece{
    int color;
    final int type = pieceEnum.KNIGHT;
    Coordinate cord;
    Board board;
    public Knight(Coordinate cord, Board board, int color){
        this.cord = cord;
        this.board = board;
        this.color = color;
    }
    @Override
    public boolean canMove(Coordinate c) {
        if(cord.i == c.i || cord.j == c.j)return false;
        if(abs(cord.i - c.i) + abs(cord.j - c.j) != 3)return false;
        if(!board.isEmpty(c) && board.getPiece(c).color() == color)return false;
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
        cord = c;
    }
}