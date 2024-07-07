package javaClasses;

import java.util.ArrayList;

import static java.lang.Math.abs;
public class Bishop implements Piece{

    int color;
    final int type = pieceEnum.BISHOP;
    Coordinate cord;
    Board board;
    public Bishop(Coordinate cord, Board board, int color){
        this.cord = cord;
        this.color = color;
        this.board = board;
        board.setPiece(getCoordinate().i, getCoordinate().j,this);

        if(color == pieceEnum.WHITE)
            board.whitePieces.add(this);
        else
            board.blackPieces.add(this);
    }

    public Bishop(int i, int j, Board board, int color){
        this(new Coordinate(i,j), board, color);
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
        if( !board.isEmpty(c.i,c.j) && board.getPiece(c.i, c.j).color() == this.color)
            return false;


        // check the paths to c
        if(dx > 0 && dy > 0) { // 1. bottom right
            for (int i = 1; i < abs(dx); i++) {
                if (!board.isEmpty(cord.i + i, cord.j + i)) {
                    return false;
                }
            }
        } else if(dx > 0 && dy < 0) { // 2. top right
            for (int i = 1; i < abs(dx); i++) {
                if (!board.isEmpty(cord.i - i, cord.j + i)) {
                    return false;
                }
            }
        } else if(dx < 0 && dy > 0) { // 3. bottom left
            for (int i = 1; i < abs(dx); i++) {
                if (!board.isEmpty(cord.i + i, cord.j - i)) {
                    return false;
                }
            }
        } else { // 4. top left
            for (int i = 1; i < abs(dx); i++) {
                if (!board.isEmpty(cord.i - i, cord.j - i)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canMove(int i, int j){
        return canMove(new Coordinate(i,j));
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

    @Override
    public boolean moved() { return false; }

    @Override
    public void setMoved(boolean b) {}


    public void updateCoordinate(int i, int j) { this.cord = new Coordinate(i,j); }

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
    public ArrayList<Coordinate> getCheckPath(Coordinate kingCord) {
        int dx = kingCord.j - this.cord.j;
        int dy = kingCord.i - this.cord.i;
        if(abs(dx) == 1) // no path
            return new ArrayList<Coordinate>();

        ArrayList<Coordinate> pathCords = new ArrayList<Coordinate>();

        if(dx > 0 && dy > 0) { // 1. bottom right
            for (int i = 1; i < abs(dx); i++) {
                pathCords.add(new Coordinate(cord.i+i,cord.j+i));
            }
        } else if(dx > 0 && dy < 0) { // 2. top right
            for (int i = 1; i < abs(dx); i++) {
                pathCords.add(new Coordinate(cord.i-i,cord.j+i));
            }
        } else if(dx < 0 && dy > 0) { // 3. bottom left
            for (int i = 1; i < abs(dx); i++) {
                pathCords.add(new Coordinate(cord.i+i,cord.j-i));
            }
        } else { // 4. top left
            for (int i = 1; i < abs(dx); i++) {
                pathCords.add(new Coordinate(cord.i-i,cord.j-i));
            }
        }

        return pathCords;
    }
}
