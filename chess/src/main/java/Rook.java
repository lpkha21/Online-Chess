public class Rook implements Piece{
    int color;
    final int type = pieceEnum.ROOK;
    Coordinate cord;
    Board board;


    public Rook(Coordinate coord, Board board, int color){
        this.board = board;
        this.color = color;
        this.cord = coord;
    }
    @Override
    public boolean canMove(Coordinate c) {

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
        cord = c;
    }

}
