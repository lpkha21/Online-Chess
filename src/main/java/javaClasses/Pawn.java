package javaClasses;

import javax.swing.*;

import static java.lang.Math.abs;

public class Pawn implements Piece {
    int color;
    final int type = pieceEnum.PAWN;
    Coordinate cord;
    Board board;


    boolean moved = false;
    boolean promoted = false;


    public Pawn(Coordinate cord, Board board, int color){
        this.color = color;
        this.board = board;
        this.cord = cord;
        board.setPiece(getCoordinate().i, getCoordinate().j,this);

        if(color == pieceEnum.WHITE)
            board.whitePieces.add(this);
        else
            board.blackPieces.add(this);
    }
    public Pawn(int i, int j, Board board, int color){
        this(new Coordinate(i,j), board, color);
    }
    @Override
    public boolean canMove(Coordinate c) {
        if(this.color == pieceEnum.BLACK){
            if(c.i <= this.cord.i)
                return false;
        }else{
            if(c.i >= this.cord.i)
                return false;
        }

        if(!moved && abs(c.i-this.cord.i) == 2 && abs(c.j - this.cord.j) == 0 && board.isEmpty(c))
            return true;

        if(abs(c.i - this.cord.i) != 1 || abs(c.j - this.cord.j) > 1)
            return false;
        if(abs(c.j - this.cord.j) == 1 &&
                (board.getPiece(c) == null || board.getPiece(c).color() == this.color))
            return false;
        if(abs(c.j - this.cord.j) == 0 && board.getPiece(c) != null)
            return false;

        return true;
    }

    public boolean canMove(int i, int j){
        return this.canMove(new Coordinate(i,j));
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

    @Override
    public boolean moved() { return moved; }

    @Override
    public void setMoved(boolean b) { moved = b;}

    public void updateCoordinate(int i, int j){
        this.updateCoordinate(new Coordinate(i,j));

        if(this.color == pieceEnum.BLACK){
           if(this.cord.i == board.SIZE-1)
               this.promoted = true;
        } else {
            if(this.cord.i == 0)
                this.promoted = true;
        }
    }

    public boolean isPromoted(){
        return this.promoted;
    }

    @Override
    public boolean isStuck() {
        for(int i=0; i<board.SIZE-1; i++){
            for(int j=0; j<board.SIZE-1; j++){
                if(this.canMove(i,j))
                    return false;
            }
        }
        return true;
    }

}
