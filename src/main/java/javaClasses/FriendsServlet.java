package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FriendsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        DataBase dataBase = (DataBase) getServletContext().getAttribute("dataBase");
        RequestDispatcher dispatcher;

        try {
            request.setAttribute("username", username);
            String friends = dataBase.getFriends(username);
            request.setAttribute("friends",friends);
            dispatcher = request.getRequestDispatcher("removeFriends.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dispatcher.forward(request, response);
    }
}
