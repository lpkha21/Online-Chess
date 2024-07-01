package javaClasses;

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

        if(!board.isEmpty(c) &&
                (board.getPiece(c.i, c.j).color() == this.color || board.getPiece(c.i,c.j).getType() == pieceEnum.KING ))
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
    public boolean moved(){
        return moved;
    }

}
