package javaClasses;

public interface Piece {
    public boolean canMove(Coordinate c);
    public int color();
    public int getType();
    public Coordinate getCoordinate();
    public void updateCoordinate(Coordinate c);

    public boolean moved();
    public void setMoved(boolean b);
}
