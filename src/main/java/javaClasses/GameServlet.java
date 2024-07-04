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
    int currTurn;
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        board = new Board();
        whitePlayer = new Player(pieceEnum.WHITE);
        blackPlayer = new Player(pieceEnum.BLACK);

        currTurn = pieceEnum.WHITE;

        httpServletRequest.setAttribute("board", board);
        httpServletRequest.setAttribute("currTurn", whitePlayer);

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

        int col = Integer.parseInt(httpServletRequest.getParameter("col"));


        if (board.makeMove(from, to, col)) {
            if (currTurn == pieceEnum.WHITE) {
                currTurn = pieceEnum.BLACK;
                httpServletRequest.setAttribute("currTurn", blackPlayer);
            } else {
                currTurn = pieceEnum.WHITE;
                httpServletRequest.setAttribute("currTurn", whitePlayer);
            }
        }else{
            if (currTurn == pieceEnum.WHITE) {
                httpServletRequest.setAttribute("currTurn", whitePlayer);
            } else {
                httpServletRequest.setAttribute("currTurn", blackPlayer);
            }
        }

        httpServletRequest.setAttribute("board", board);
        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);

    }
}
