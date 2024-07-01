package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DataBase dataBase = (DataBase) getServletContext().getAttribute("dataBase");
        RequestDispatcher dispatcher;

        try {
            if (dataBase.createAccount(username, password)) {
                request.setAttribute("username", username);
                String friends = dataBase.getFriends(username);
                request.setAttribute("friends",friends);
                dispatcher = request.getRequestDispatcher("profile.jsp");
            } else {
                request.setAttribute("username", username);
                dispatcher = request.getRequestDispatcher("accountNameInUse.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dispatcher.forward(request, response);
    }
}
