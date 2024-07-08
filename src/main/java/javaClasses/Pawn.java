package javaClasses;

import javax.swing.*;

import java.util.ArrayList;

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

        if(!moved && abs(c.i-this.cord.i) == 2 && abs(c.j - this.cord.j) == 0 && board.isEmpty(c)) {
            if(color == pieceEnum.WHITE && board.isEmpty(cord.i-1,c.j)) {

                // Move and see if Checked
                Coordinate prevCord = cord;
                boolean prevMoved = this.moved;
                Piece killedPiece = this.board.getPiece(c);

                this.updateCoordinate(c);
                this.board.setPiece(c.i,c.j,this);
                this.board.setPiece(prevCord.i, prevCord.j, null);

                if(this.board.isCheck(this.color)){
                    updateCoordinate(prevCord);
                    this.moved = prevMoved;
                    this.board.setPiece(cord.i,cord.j,this);
                    this.board.setPiece(c.i,c.j,killedPiece);
                    return false;
                }

                updateCoordinate(prevCord);
                this.moved = prevMoved;
                this.board.setPiece(cord.i,cord.j,this);
                this.board.setPiece(c.i,c.j,killedPiece);

                return true;
            }
            else if(color == pieceEnum.BLACK && board.isEmpty(cord.i+1,c.j)) {
                // Move and see if Checked
                Coordinate prevCord = cord;
                boolean prevMoved = this.moved;
                Piece killedPiece = this.board.getPiece(c);

                this.updateCoordinate(c);
                this.board.setPiece(c.i,c.j,this);
                this.board.setPiece(prevCord.i, prevCord.j, null);

                if(this.board.isCheck(this.color)){
                    updateCoordinate(prevCord);
                    this.moved = prevMoved;
                    this.board.setPiece(cord.i,cord.j,this);
                    this.board.setPiece(c.i,c.j,killedPiece);
                    return false;
                }

                updateCoordinate(prevCord);
                this.moved = prevMoved;
                this.board.setPiece(cord.i,cord.j,this);
                this.board.setPiece(c.i,c.j,killedPiece);

                return true;
            }
        }

        if(abs(c.i - this.cord.i) != 1 || abs(c.j - this.cord.j) > 1)
            return false;
        if(abs(c.j - this.cord.j) == 1 &&
                (board.getPiece(c) == null || board.getPiece(c).color() == this.color))
            return false;
        if(abs(c.j - this.cord.j) == 0 && board.getPiece(c) != null)
            return false;


        // Move and see if Checked
        Coordinate prevCord = cord;
        boolean prevMoved = this.moved;
        boolean prevPromotion = this.isPromoted();
        Piece killedPiece = this.board.getPiece(c);

        this.updateCoordinate(c);
        this.board.setPiece(c.i,c.j,this);
        this.board.setPiece(prevCord.i,prevCord.j,null);

        if(this.board.isCheck(this.color)){
            updateCoordinate(prevCord);
            this.moved = prevMoved;
            this.promoted = prevPromotion;
            this.board.setPiece(cord.i,cord.j,this);
            this.board.setPiece(c.i,c.j,killedPiece);
            return false;
        }

        updateCoordinate(prevCord);
        this.moved = prevMoved;
        this.promoted = prevPromotion;
        this.board.setPiece(cord.i,cord.j,this);
        this.board.setPiece(c.i,c.j,killedPiece);

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

        if(this.color == pieceEnum.BLACK){
            if(this.cord.i == board.SIZE-1)
                this.promoted = true;
        } else {
            if(this.cord.i == 0)
                this.promoted = true;
        }
    }

    @Override
    public boolean moved() { return moved; }

    @Override
    public void setMoved(boolean b) { moved = b;}

    public void updateCoordinate(int i, int j){
        this.updateCoordinate(new Coordinate(i,j));
    }

    public boolean isPromoted(){
        return this.promoted;
    }

    public void depromote(){ this.promoted = false; }
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

    @Override
    public ArrayList<Coordinate> getCheckPath(Coordinate kingCord) { return null; }

}
