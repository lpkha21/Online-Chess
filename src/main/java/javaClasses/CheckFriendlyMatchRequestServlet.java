package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CheckFriendlyMatchRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<FriendlyMatch> friendlyMatches = (ArrayList<FriendlyMatch>) request.getServletContext().getAttribute("friendlyMatches");
        String username = (String) session.getAttribute("username");

        int isRequest = 0;
        for(int i = 0; i < friendlyMatches.size(); i++){
            FriendlyMatch friendlyMatch = friendlyMatches.get(i);
            if(friendlyMatch.getSecondUsername().equals(username)){
                isRequest = 1;
                session.setAttribute("friendlyMatchRequest",friendlyMatch);
                break;
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"isRequest\": " + isRequest + "}");
    }
}
