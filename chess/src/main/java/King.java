public class King implements Piece{
    int color;
    final int type = pieceEnum.KING;
    Coordinate cord;
    Board board;
    boolean moved = false;

    public King(Coordinate cord, Board board, int color){
        this.cord = cord;
        this.board = board;
        this.color = color;
    }

    @Override
    public boolean canMove(Coordinate c) {
        if(c.equals(cord))
            return false;

        Piece p = board.getPiece(c);
        if(p != null && p.color() == this.color)
            return false;

       // if(!moved && )

        if(c.i - cord.i < 2 && c.i - cord.i > -2 && c.j - cord.j < 2 && c.j - cord.j < 2){
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    Piece curr = board.getPiece(i,j);
                    if(curr != null && curr.color() != this.color && curr.canMove(c)){
                        return false;
                    }
                }
            }
        }else{
            return false;
        }

        return true;
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
        moved = true;
    }
}
