package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Game game = (Game) session.getAttribute("game");

        httpServletRequest.setAttribute("board", game.getBoard());

        String t = game.time;
        int time = Integer.parseInt(t);
        time = time*60;

        if(session.getAttribute("myTimer") != null){
            int myTimer = (int) session.getAttribute("myTimer");
            int opponentTimer = (int) session.getAttribute("opponentTimer");
            httpServletRequest.setAttribute("myTimer",myTimer);
            httpServletRequest.setAttribute("opponentTimer",opponentTimer);
        }else{
            httpServletRequest.setAttribute("myTimer", time);
            httpServletRequest.setAttribute("opponentTimer", time);
        }
        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int fi = Integer.parseInt(httpServletRequest.getParameter("fromi"));
        int fj = Integer.parseInt(httpServletRequest.getParameter("fromj"));

        int ti = Integer.parseInt(httpServletRequest.getParameter("toi"));
        int tj = Integer.parseInt(httpServletRequest.getParameter("toj"));
        Coordinate from = new Coordinate(fi, fj);
        Coordinate to = new Coordinate(ti, tj);


        HttpSession session = httpServletRequest.getSession();
        Game game = (Game) session.getAttribute("game");
        game.changeBoard(session,from,to);

        int myTimer = (int) session.getAttribute("myTimer");
        int opponentTimer = (int) session.getAttribute("opponentTimer");
        httpServletRequest.setAttribute("myTimer",myTimer);
        httpServletRequest.setAttribute("opponentTimer",opponentTimer);
        httpServletRequest.setAttribute("board", game.getBoard());
        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);

    }
}
