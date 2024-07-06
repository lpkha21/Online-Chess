package javaClasses;

import javax.servlet.http.HttpSession;

public class FriendlyMatch {

    private HttpSession firstSession;
    private String firstUsername;

    private HttpSession secondSession;
    private  String secondUsername;

    String time;
    int color;


    public FriendlyMatch(String firstUsername, HttpSession session, String secondUsername, String time, int color){
        this.time = time;
        this.color = color;
        this.firstUsername = firstUsername;
        this.firstSession = session;
        this.secondUsername = secondUsername;
        this.secondSession = null;
    }

    public String getFirstUsername(){
        return this.firstUsername;
    }

    public HttpSession getFirstSession(){
        return this.firstSession;
    }

    public void setSecondSession(HttpSession session){
        this.secondSession = session;
    }

    public String getSecondUsername(){
        return this.secondUsername;
    }

    public HttpSession getSecondSession(){
        return this.secondSession;
    }

}
