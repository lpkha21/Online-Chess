package javaClasses;

import javax.servlet.http.HttpSession;
import java.util.Random;

public class Game {

    Board board;
    Player whitePlayer;
    Player blackPlayer;

    int current;



    public Game(HttpSession session1, HttpSession session2){
        board = new Board();
        Random random = new Random();
        current = pieceEnum.WHITE;
        if(random.nextBoolean()){
            whitePlayer = new Player((String) session1.getAttribute("username"),pieceEnum.WHITE);
            blackPlayer = new Player((String) session2.getAttribute("username"),pieceEnum.BLACK);
            session1.setAttribute("player",whitePlayer);
            session2.setAttribute("player",blackPlayer);
        }else{
            whitePlayer = new Player((String) session2.getAttribute("username"),pieceEnum.WHITE);
            blackPlayer = new Player((String) session1.getAttribute("username"),pieceEnum.BLACK);
            session1.setAttribute("player",blackPlayer);
            session2.setAttribute("player",whitePlayer);
        }
    }

    public Board getBoard(){
        return board;
    }

    public void changeBoard(HttpSession session,Coordinate from, Coordinate to){
        Player p = (Player) session.getAttribute("player");
        if(p.getColor() != current){
            return;
        }

        if (board.makeMove(from, to, current)) {
           if(p.getColor() == pieceEnum.WHITE){
               current = pieceEnum.BLACK;
           }else{
               current = pieceEnum.WHITE;
           }
        }
    }
}
