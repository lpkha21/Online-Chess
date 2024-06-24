public interface Piece {
    public boolean canMove(Coordinate c);
    public int color();
    public int getType();
    public Coordinate getCoordinate();
}
