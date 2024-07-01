package javaClasses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String friendUsername = request.getParameter("friendUsername");
        String username = request.getParameter("username");

        DataBase dataBase = (DataBase) getServletContext().getAttribute("dataBase");
        RequestDispatcher dispatcher;

        try {
            if(dataBase.exists(friendUsername)){
                request.setAttribute("friendUsername",friendUsername);
                request.setAttribute("username",username);
                int temp = dataBase.addRequest(friendUsername,username);
                if(temp == 0){
                    dispatcher = request.getRequestDispatcher("successRequest.jsp");
                }else if(temp == -1){
                    dispatcher = request.getRequestDispatcher("requestAlreadySent.jsp");
                }else{
                    dispatcher = request.getRequestDispatcher("alreadyFriends.jsp");
                }
            }else{
                request.setAttribute("friendUsername",friendUsername);
                request.setAttribute("username",username);
                dispatcher = request.getRequestDispatcher("incorrectName.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dispatcher.forward(request, response);
    }
}
