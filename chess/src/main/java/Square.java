public class Square {
    private int COL;
    private int type;

    private int x;
    private int y;

    public Square(int col, int type, int x, int y){
        this.COL = col;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getColor(){
        return COL;
    }
    public int getType(){
        return type;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
