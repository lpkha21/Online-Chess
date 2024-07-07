package javaClasses;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Rook implements Piece{
    int color;
    final int type = pieceEnum.ROOK;
    Coordinate cord;
    Board board;
    boolean moved = false;

    public Rook(Coordinate cord, Board board, int color){
        this.board = board;
        this.color = color;
        this.cord = cord;
        board.setPiece(getCoordinate().i, getCoordinate().j,this);

        if(color == pieceEnum.WHITE)
            board.whitePieces.add(this);
        else
            board.blackPieces.add(this);
    }
    public Rook(int i, int j, Board board, int color){
        this(new Coordinate(i,j), board, color);
    }

    @Override
    public boolean canMove(Coordinate c) {
        if(c.equals(cord))
            return false;

        if(cord.i != c.i && cord.j != c.j)
            return false;

        if(!board.isEmpty(c) && board.getPiece(c.i, c.j).color() == this.color )
            return false;


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
        moved = true;
    }

    @Override
    public boolean moved() { return moved; }

    @Override
    public void setMoved(boolean b) { moved = b;}

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
        if( abs(cord.j - kingCord.j) < 2 && abs(cord.j - kingCord.j) < 2 ) // no path
            return new ArrayList<Coordinate>();

        ArrayList<Coordinate> pathCords = new ArrayList<Coordinate>();

        if(cord.i == kingCord.i) {
            if (cord.j > kingCord.j) {
                for(int j = cord.j-1; j > kingCord.j; j--){
                        pathCords.add(new Coordinate(cord.i, j));
                }
            }else{
                for(int j = cord.j+1; j < kingCord.j; j++){
                        pathCords.add(new Coordinate(cord.i, j));
                }
            }
        }

        else {
            if(cord.i > kingCord.i){
                for (int i = cord.i-1; i > kingCord.i; i--) {
                        pathCords.add(new Coordinate(i,cord.j));
                }
            }else{
                for(int i = cord.i+1; i < kingCord.i; i++){
                        pathCords.add(new Coordinate(i,cord.j));
                }
            }
        }

        return pathCords;
    }

}
