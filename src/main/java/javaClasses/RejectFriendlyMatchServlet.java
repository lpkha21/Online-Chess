package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class RejectFriendlyMatchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<FriendlyMatch> friendlyMatches = (ArrayList<FriendlyMatch>) request.getServletContext().getAttribute("friendlyMatches");
        String username = (String) session.getAttribute("username");
        FriendlyMatch friendlyMatch = (FriendlyMatch) session.getAttribute("friendlyMatchRequest");

        friendlyMatches.remove(friendlyMatch);

        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/BackToProfileServlet");
        dispatcher.forward(request, response);
    }
}
