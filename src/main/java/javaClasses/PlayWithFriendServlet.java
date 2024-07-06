package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayWithFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String myUsername = (String) session.getAttribute("username");
        String friendUsername = request.getParameter("friendName");
        String time = request.getParameter("time");
        String color = request.getParameter("color");
        int pieceColor = 0;
        if(color.equals("white")){
            pieceColor = pieceEnum.WHITE;
        }else if(color.equals("black")){
            pieceColor = pieceEnum.BLACK;
        }else{
            pieceColor = -1;
        }

        ArrayList<FriendlyMatch> friendlyMatches = (ArrayList<FriendlyMatch>) request.getServletContext().getAttribute("friendlyMatches");

        FriendlyMatch friendlyMatch = new FriendlyMatch(myUsername,session,friendUsername,time,pieceColor);
        friendlyMatches.add(friendlyMatch);
        session.setAttribute("friendlyMatch",friendlyMatch);

        RequestDispatcher dispatcher;
        request.setAttribute("friendName",friendUsername);

        dispatcher = request.getRequestDispatcher("waitingForFriends.jsp");
        dispatcher.forward(request, response);

    }
}
