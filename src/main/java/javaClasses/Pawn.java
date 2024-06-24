package javaClasses;

import static java.lang.Math.abs;

public class Pawn implements Piece {
    int color;
    final int type = pieceEnum.PAWN;
    Coordinate cord;
    Board board;

    boolean moved = false;


    public Pawn(Coordinate cord, Board board, int color){
        this.color = color;
        this.board = board;
        this.cord = cord;
    }
    @Override
    public boolean canMove(Coordinate c) {
        if(this.color == pieceEnum.BLACK){
            if(c.i <= this.cord.i)return false;
        }else{
            if(c.i >= this.cord.i)return false;
        }
        if(!moved && abs(c.i-this.cord.i) == 2 && abs(c.j - this.cord.j) == 0 && board.isEmpty(c))return true;

        if(abs(c.i - this.cord.i) != 1 || abs(c.j - this.cord.j) > 1)return false;
        if(abs(c.j - this.cord.j) == 1 && (board.getPiece(c) == null || board.getPiece(c).color() == this.color))return false;
        if(abs(c.j - this.cord.j) == 0 && board.getPiece(c) != null)return false;

        return false;
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
        moved = true;
    }
}
