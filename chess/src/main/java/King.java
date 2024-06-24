public class King implements Piece{
    int color;
    final int type = pieceEnum.KING;
    Coordinate coord;
    Board board;


    public King(Coordinate coord, Board board, int color){
        this.coord = coord;
        this.board = board;
        this.color = color;
    }

    @Override
    public boolean canMove(Coordinate c) {
        boolean ans = true;

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
        return coord;
    }

    @Override
    public void updateCoordinate(Coordinate c) {
        coord = c;
    }
}
