package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameServlet extends HttpServlet {

    Board board;
    Player whitePlayer;
    Player blackPlayer;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        board = new Board();
        whitePlayer = new Player(pieceEnum.WHITE);
        blackPlayer = new Player(pieceEnum.BLACK);

        httpServletRequest.setAttribute("board", board);
        httpServletRequest.setAttribute("whitePlayer", whitePlayer);
        httpServletRequest.setAttribute("blackPlayer", blackPlayer);

        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int fi = Integer.parseInt(httpServletRequest.getParameter("fromi"));
        int fj = Integer.parseInt(httpServletRequest.getParameter("fromj"));

        System.out.println(fi);
        int ti = Integer.parseInt(httpServletRequest.getParameter("toi"));
        int tj = Integer.parseInt(httpServletRequest.getParameter("toj"));
        Coordinate from = new Coordinate(fi, fj);
        Coordinate to = new Coordinate(ti, tj);

        int col = Integer.parseInt(httpServletRequest.getParameter("col"));

        if(board.makeMove(from ,to, col)){
            httpServletRequest.setAttribute("board", board);
        }

        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);

    }
}
