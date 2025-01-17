package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class JoinFriendlyMatchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<FriendlyMatch> friendlyMatches = (ArrayList<FriendlyMatch>) request.getServletContext().getAttribute("friendlyMatches");
        String username = (String) session.getAttribute("username");
        FriendlyMatch friendlyMatch = (FriendlyMatch) session.getAttribute("friendlyMatch");

        HttpSession opponentSession = null;
        opponentSession = friendlyMatch.getSecondSession();
        if(session.getAttribute("game") == null){
            Game game = new Game(session,opponentSession,friendlyMatch.time,friendlyMatch.color);
            session.setAttribute("game",game);
            opponentSession.setAttribute("game",game);
        }else{
            friendlyMatches.remove(friendlyMatch);
        }

        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/Game");
        dispatcher.forward(request, response);

    }
}
