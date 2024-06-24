public class Pawn implements Piece {


    int color;
    final int type = pieceEnum.PAWN;
    Coordinate coord;
    Board board;


    public Pawn(Coordinate coord, Board board, int color){
        this.color = color;
        this.board = board;
        this.coord = coord;
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
        return this.coord;
    }

    @Override
    public void updateCoordinate(Coordinate c) {
        
    }
}
