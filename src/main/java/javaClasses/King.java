package javaClasses;
import static java.lang.Math.abs;

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

        if(color == pieceEnum.WHITE)
            board.whitePieces.add(this);
        else
            board.blackPieces.add(this);
    }

    public King(int i, int j, Board board, int color){
        this(new Coordinate(i,j), board, color);
    }

    @Override
    public boolean canMove(Coordinate c) {
        if(c.equals(cord)) // move to same cord
            return false;

        Piece p = board.getPiece(c);
        if(p != null && p.color() == this.color) // move to same colored piece
            return false;

        if(check(c))
            return false;

        if(c.j - cord.j == 2){
           if(moved)
               return false;
           Piece rook = board.getPiece(this.cord.i,board.SIZE-1);
           if(rook.getType() != pieceEnum.ROOK)
               return false;
           if( ((Rook)(rook)).moved() )
               return false;
           if(!board.isEmpty(this.cord.i,this.cord.j + 1) || !board.isEmpty(this.cord.i,this.cord.j + 2))
               return false;
           if(check(new Coordinate(this.cord.i,this.cord.j)) || check(new Coordinate(this.cord.i,this.cord.j + 1)) || check(new Coordinate(this.cord.i,this.cord.j + 2)))
               return false;
       }else if(c.j - cord.j == -2){
           if(moved)
               return false;
           Piece rook = board.getPiece(this.cord.i,0);
           if(rook.getType() != pieceEnum.ROOK)
               return false;
           if(((Rook)(rook)).moved())
               return false;
           if(!board.isEmpty(this.cord.i,this.cord.j - 1) || !board.isEmpty(this.cord.i,this.cord.j - 2) || !board.isEmpty(this.cord.i,this.cord.j - 3))
               return false;
           if(check(new Coordinate(this.cord.i,this.cord.j)) || check(new Coordinate(this.cord.i,this.cord.j - 1)) && check(new Coordinate(this.cord.i,this.cord.j - 2)))
               return false;
       }else if(abs(c.i-cord.i) >= 2 || abs(c.j-cord.j) >= 2) { // moving too far
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
                        if ( abs(temp.i - c.i) <= 1  && abs(temp.j - c.j) <= 1)
                            return true;
                    }
                    else if(curr.canMove(c))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean canMove(int i, int j){ return this.canMove(new Coordinate(i,j)); }

    public boolean check(int i, int j){ return this.check(new Coordinate(i,j)); }

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

    @Override
    public boolean moved() { return moved; }

    @Override
    public void setMoved(boolean b) { moved = b;}

    @Override
    public boolean isStuck() {
        for(int i=0; i<board.SIZE-1; i++){
            for(int j=0; j<board.SIZE-1; j++){
                if(this.canMove(i,j))
                    return false;
            }
        }
        return true;
    }
}
