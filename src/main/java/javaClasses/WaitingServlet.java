package javaClasses;

import com.mysql.cj.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class WaitingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String time = request.getParameter("time");
        HttpSession session = request.getSession();

        RequestDispatcher dispatcher;


        ArrayList<HttpSession> queue;
        if(time.equals("3")){
            queue = (ArrayList<HttpSession>) request.getServletContext().getAttribute("minute3");
        }else if(time.equals("5")){
            queue = (ArrayList<HttpSession>) request.getServletContext().getAttribute("minute5");
        }else{
            queue = (ArrayList<HttpSession>) request.getServletContext().getAttribute("minute10");
        }
        if(!queue.contains(session)){
            queue.add(session);
        }



        request.setAttribute("username",username);
        request.setAttribute("queue",queue);

        session.setAttribute("queue",queue);
        session.setAttribute("time",time);

        dispatcher = request.getRequestDispatcher("waiting.jsp");
        dispatcher.forward(request, response);
    }
}
