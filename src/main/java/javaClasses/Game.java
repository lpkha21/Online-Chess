package javaClasses;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    Board board;
    Player whitePlayer;
    Player blackPlayer;

    boolean whiteResign;
    boolean blackResign;

    boolean requestDrawBlack;
    boolean requestDrawWhite;

    boolean answerDrawBlack;
    boolean answerDrawWhite;

    public int current;

    int update;

    String time;
    private ArrayList<Message> messages;

    public Game(HttpSession session1, HttpSession session2, String time, int color){
        messages = new ArrayList<>();
        whiteResign = false;
        blackResign = false;
        board = new Board();
        Random random = new Random();
        current = pieceEnum.WHITE;
        update = -1;
        this.time = time;
        if(color == pieceEnum.WHITE){
            whitePlayer = new Player((String) session1.getAttribute("username"),pieceEnum.WHITE);
            blackPlayer = new Player((String) session2.getAttribute("username"),pieceEnum.BLACK);
            session1.setAttribute("player",whitePlayer);
            session1.setAttribute("opponent",blackPlayer);
            session2.setAttribute("player",blackPlayer);
            session2.setAttribute("opponent",whitePlayer);

        }else if(color == pieceEnum.BLACK){
            whitePlayer = new Player((String) session2.getAttribute("username"),pieceEnum.WHITE);
            blackPlayer = new Player((String) session1.getAttribute("username"),pieceEnum.BLACK);
            session1.setAttribute("player",blackPlayer);
            session1.setAttribute("opponent",whitePlayer);
            session2.setAttribute("player",whitePlayer);
            session2.setAttribute("opponent",blackPlayer);
        }else{
            if(random.nextBoolean()){
                whitePlayer = new Player((String) session1.getAttribute("username"),pieceEnum.WHITE);
                blackPlayer = new Player((String) session2.getAttribute("username"),pieceEnum.BLACK);
                session1.setAttribute("player",whitePlayer);
                session1.setAttribute("opponent",blackPlayer);
                session2.setAttribute("player",blackPlayer);
                session2.setAttribute("opponent",whitePlayer);
            }else{
                whitePlayer = new Player((String) session2.getAttribute("username"),pieceEnum.WHITE);
                blackPlayer = new Player((String) session1.getAttribute("username"),pieceEnum.BLACK);
                session1.setAttribute("player",blackPlayer);
                session1.setAttribute("opponent",whitePlayer);
                session2.setAttribute("player",whitePlayer);
                session2.setAttribute("opponent",blackPlayer);
            }
        }
    }

    public void messageSent(Player p, String str){
        messages.add(new Message(p.getColor(), str));
    }

    public ArrayList<Message> messageGet(){
        return messages;
    }

    public Board getBoard(){
        return board;
    }

    public void changeBoard(HttpSession session,Coordinate from, Coordinate to){
        Player p = (Player) session.getAttribute("player");
        if(p.getColor() != current){
            return;
        }

        if (board.makeMove(from, to, current) && !board.promotion(to)) {
           changeColor(session);
        }
    }

    public void changeColor(HttpSession session){
        Player p = (Player) session.getAttribute("player");
        if(p.getColor() == pieceEnum.WHITE){
            current = pieceEnum.BLACK;
            update = pieceEnum.BLACK;
        }else{
            current = pieceEnum.WHITE;
            update = pieceEnum.WHITE;
        }
    }

    public boolean win(HttpSession session){
        Player p = (Player) session.getAttribute("player");
        if(p.getColor() == pieceEnum.WHITE){
            return board.isCheckMate(pieceEnum.BLACK);
        }else{
            return board.isCheckMate(pieceEnum.WHITE);
        }
    }

    public boolean lose(HttpSession session){
        Player p = (Player) session.getAttribute("player");
        return board.isCheckMate(p.getColor());
    }

    public boolean draw(int color){
        return board.isDraw(color);
    }

    public void Resign(HttpSession session){
        Player p = (Player) session.getAttribute("player");
        if(p.getColor() == pieceEnum.WHITE){
            whiteResign = true;
        }else{
            blackResign = true;
        }
    }

    public void RequestDraw(HttpSession session){
        Player p = (Player) session.getAttribute("player");
        if(p.getColor() == pieceEnum.WHITE){
            requestDrawBlack = true;
        }else{
            requestDrawWhite = true;
        }
    }

    public void AnswerDraw(HttpSession session, String answer){
        Player p = (Player) session.getAttribute("player");
        if(p.getColor() == pieceEnum.WHITE){
            if(answer.equals("yes")){
                answerDrawWhite = true;
            }else{
                answerDrawWhite = false;
            }
            requestDrawWhite = false;
        }else{
            if(answer.equals("yes")){
                answerDrawBlack = true;
            }else{
                answerDrawBlack = false;
            }
            requestDrawBlack = false;
        }
    }
}
