public class Queen implements Piece{
    int color;

    final int type = pieceEnum.QUEEN;

    Coordinate cord;

    Board board;

    public Queen(Coordinate coordinate, Board board, int col) {
        cord = coordinate;
        this.board = board;
        this.color = col;
    }

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

    @Override
    public void updateCoordinate(Coordinate c) {
        cord = c;
    }
}
