package javaClasses;

public class Coordinate {
    public int i;
    public int j;
    public Coordinate(int i, int j){
        this.i = i;
        this.j = j;
    }
    public boolean equals(Coordinate c2){
        return this.i == c2.i && this.j == c2.j;
    }
    public boolean equals(int i, int j){
        return this.equals(new Coordinate(i,j));
    }

}
