package javaClasses;

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
        board.setPiece(getCoordinate().i, getCoordinate().j,this);
    }

    public King(int i, int j, Board board, int color){
        this(new Coordinate(i,j), board, color);
    }

    @Override
    public boolean canMove(Coordinate c) {
        if(c.equals(cord))
            return false;

        Piece p = board.getPiece(c);
        if(p != null && p.color() == this.color)
            return false;

       if(c.j - cord.j == 2){
           if(moved)
               return false;
           Piece rook = board.getPiece(this.cord.i,this.cord.j + 4);
           if(rook.getType() == pieceEnum.ROOK && ((Rook)(rook)).moved())
               return false;
           if(!board.isEmpty(this.cord.i,this.cord.j + 1) || !board.isEmpty(this.cord.i,this.cord.j + 2) || !board.isEmpty(this.cord.i,this.cord.j + 3))
               return false;
           if(check(new Coordinate(this.cord.i,this.cord.j + 1)) && check(new Coordinate(this.cord.i,this.cord.j + 2)))
               return false;
       }
       else if(c.j - cord.j == -2){
           if(moved)
               return false;
           Piece rook = board.getPiece(this.cord.i,this.cord.j - 3);
           if(rook.getType() == pieceEnum.ROOK && ((Rook)(rook)).moved())
               return false;
           if(!board.isEmpty(this.cord.i,this.cord.j - 1) || !board.isEmpty(this.cord.i,this.cord.j - 2))
               return false;
           if(check(new Coordinate(this.cord.i,this.cord.j - 1)) && check(new Coordinate(this.cord.i,this.cord.j - 2)))
               return false;
       }

        if(c.i - cord.i < 2 && c.i - cord.i > -2 && c.j - cord.j < 2 && c.j - cord.j < 2){
            if(check(c))
                return false;
        }else{
            return false;
        }

        return true;
    }

    private boolean check(Coordinate c){
        for (int i = 0; i < board.SIZE; i++) {
            for (int j = 0; j < board.SIZE; j++) {
                Piece curr = board.getPiece(i,j);
                if(curr != null && curr.color() != this.color){
                    if(curr.getType() == pieceEnum.KING) {
                        Coordinate temp = curr.getCoordinate();
                        if (temp.i - c.i == 1 || temp.i - c.i == -1 || temp.j - c.j == 1 || temp.j - c.j == -1)
                            return true;
                    }else if(curr.canMove(c))
                        return true;
                }
            }
        }
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
        moved = true;
    }
}
