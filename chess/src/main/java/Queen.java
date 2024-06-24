public class Queen implements Piece{
    int color;

    final int type = pieceEnum.QUEEN;

    Coordinate cord;

    Board board;

    @Override
    public boolean canMove(Coordinate c) {

        return false;
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
}
