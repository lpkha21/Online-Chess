public class Rook implements Piece{
    int color;
    final int type = pieceEnum.ROOK;
    Coordinate cord;
    Board board;


    public Rook(Coordinate cord, Board board, int color){
        this.board = board;
        this.color = color;
        this.cord = cord;
    }
    @Override
    public boolean canMove(Coordinate c) {
        if(cord.i != c.i && cord.j != c.j) return false;

        boolean can = false;
        if(cord.i == c.i) {
            if (cord.j > c.j) {
                for(int j = c.j; j <= cord.j; j++){
                    if(!board.isEmpty(c.i, j)){

                    }
                }
            }else{

            }
        }
        return can;
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
