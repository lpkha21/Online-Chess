package javaClasses;

public class Player {

    String name;
    int color;
    long timer;

    public Player(String name, int color){
        this.name = name;
        this.color = color;
    }

    public int getColor() {
        return color;
    }
    public String getName(){ return name; }
}
