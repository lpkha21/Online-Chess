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
        board.setPiece(getCoordinate().i, getCoordinate().j,this);

        if(color == pieceEnum.WHITE)
            board.whitePieces.add(this);
        else
            board.blackPieces.add(this);
    }

    public Knight(int i, int j, Board board, int color){
        this(new Coordinate(i,j), board, color );
    }
    @Override
    public boolean canMove(Coordinate c) {
        if(cord.i == c.i || cord.j == c.j)
            return false;
        if(abs(cord.i - c.i) + abs(cord.j - c.j) != 3)
            return false;
        if(!board.isEmpty(c) && board.getPiece(c.i, c.j).color() == this.color)
            return false;
        return true;
    }

    public boolean canMove(int i, int j){ return canMove(new Coordinate(i,j));}

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

    @Override
    public boolean moved() {return false;}

    @Override
    public void setMoved(boolean b) {}

    @Override
    public boolean isStuck() { return true; }
}
