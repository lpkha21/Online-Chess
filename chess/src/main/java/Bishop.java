
import static com.sun.org.apache.xalan.internal.lib.ExsltMath.abs;

public class Bishop implements Piece{

    int color;
    final int type = pieceEnum.BISHOP;
    Coordinate coord;
    Board board;
    public Bishop(Coordinate coord, Board board, int color){
        this.coord = coord;
        this.color = color;
        this.board = board;
    }
    @Override
    public boolean canMove(Coordinate c) {
        int dx = this.coord.j - c.j;
        int dy = this.coord.i - c.i;

        if( dx == 0 && dy == 0){  // on the same square
            return false;
        } else if ( abs(dx) != abs(dy) ) { // not on the diagonal
            return false;
        } else { // check the path to c
            for(int row=this.coord.i; row<=this.coord.i+dx; row++) {
                for(int col=this.coord.j; col<=this.coord.j+dy; col++) {
                    if (!board.isEmpty(new Coordinate(row, col))) {
                        return false;
                    }
                }
            }
        }

        return true;
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
        this.coord = c;
    }
}
