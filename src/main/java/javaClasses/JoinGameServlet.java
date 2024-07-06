package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class JoinGameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<HttpSession> queue = (ArrayList<HttpSession>) session.getAttribute("queue");
        String time = (String) session.getAttribute("time");

        HttpSession opponentSession;
        if(queue.get(0).equals(session)){
            opponentSession = queue.get(1);
        }else{
            opponentSession = queue.get(0);
        }

        if(session.getAttribute("game") == null){
            Game game = new Game(session,opponentSession,time,-1);
            session.setAttribute("game",game);
            opponentSession.setAttribute("game",game);
        }else{
            queue.clear();
        }

        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/Game");
        dispatcher.forward(request, response);

    }
}
