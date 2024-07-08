package javaClasses;

import java.util.ArrayList;

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

        if(color == pieceEnum.WHITE)
            board.whitePieces.add(this);
        else
            board.blackPieces.add(this);
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
        if( !board.isEmpty(c.i,c.j) && board.getPiece(c.i, c.j).color() == this.color )
            return false;

        if ( !(abs(dx) == abs(dy) || c.i == cord.i || c.j == cord.j) )
            return false;

        if(abs(dx) == abs(dy)){
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

        // Move and see if Checked
        Coordinate prevCord = cord;
        Piece killedPiece = this.board.getPiece(c);

        this.updateCoordinate(c);
        this.board.setPiece(c.i,c.j,this);

        if(this.board.isCheck(this.color)){
            updateCoordinate(prevCord);
            this.board.setPiece(cord.i,cord.j,this);
            this.board.setPiece(c.i,c.j,killedPiece);
            return false;
        }

        updateCoordinate(prevCord);
        this.board.setPiece(cord.i,cord.j,this);
        this.board.setPiece(c.i,c.j,killedPiece);

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

    @Override
    public boolean moved() { return false; }

    @Override
    public void setMoved(boolean b) {}

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
        if(abs(dx) < 2 && abs(dy) < 2) // no path
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
        } else if (dx < 0 && dy < 0){ // 4. top left
            for (int i = 1; i < abs(dx); i++) {
                pathCords.add(new Coordinate(cord.i-i,cord.j-i));
            }
        } else if(cord.i == kingCord.i) {
            if (cord.j > kingCord.j) {
                for(int j = cord.j-1; j > kingCord.j; j--){
                    pathCords.add(new Coordinate(cord.i, j));
                }
            }else{
                for(int j = cord.j+1; j < kingCord.j; j++){
                    pathCords.add(new Coordinate(cord.i, j));
                }
            }
        } else {
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
