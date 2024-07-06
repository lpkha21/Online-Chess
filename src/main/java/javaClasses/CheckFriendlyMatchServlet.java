package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CheckFriendlyMatchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<FriendlyMatch> friendlyMatches = (ArrayList<FriendlyMatch>) request.getServletContext().getAttribute("friendlyMatches");
        String username = (String) session.getAttribute("username");
        FriendlyMatch friendlyMatch = (FriendlyMatch) session.getAttribute("friendlyMatch");

        boolean isRequest = false;
        int answer = 0;
        if(friendlyMatch.getSecondSession() == null){
            answer = 0;
        }else{
            answer = 1;
        }

        if(!friendlyMatches.contains(friendlyMatch)){
            answer = -1;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"answer\": " + answer + "}");
    }
}
