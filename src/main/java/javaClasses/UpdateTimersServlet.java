package javaClasses;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateTimersServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int myTimer = Integer.parseInt(request.getParameter("myTimer"));
        int opponentTimer = Integer.parseInt(request.getParameter("opponentTimer"));

        HttpSession session = request.getSession();
        session.setAttribute("myTimer", myTimer);
        session.setAttribute("opponentTimer", opponentTimer);
    }
}
