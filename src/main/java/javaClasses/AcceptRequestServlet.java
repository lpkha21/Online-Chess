package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AcceptRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String friendUsername = request.getParameter("friendUsername");
        String username = request.getParameter("username");

        DataBase dataBase = (DataBase) getServletContext().getAttribute("dataBase");
        RequestDispatcher dispatcher;
        try {
            dataBase.addFriends(username,friendUsername);
            dataBase.addFriends(friendUsername,username);
            dataBase.removeFromRequests(username,friendUsername);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("username",username);
        dispatcher = request.getRequestDispatcher("requests.jsp");
        dispatcher.forward(request, response);
    }
}
