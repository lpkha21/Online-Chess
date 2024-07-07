package javaClasses;

public class Message {
    private int color;
    private String message;
    public Message(int color,String message){
        this.message = message;
        this.color = color;
    }

    public int getColor(){
        return color;
    }

    public String getMessage(){
        return message;
    }
}
