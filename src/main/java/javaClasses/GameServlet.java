package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class GameServlet extends HttpServlet {

    Board board;
    Player whitePlayer;
    Player blackPlayer;
    Player gameOver;
    int currTurn;
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        board = new Board();
        whitePlayer = new Player("luka", pieceEnum.WHITE);
        blackPlayer = new Player("george", pieceEnum.BLACK);
        gameOver = new Player("", pieceEnum.END);

        currTurn = pieceEnum.WHITE;

        httpServletRequest.setAttribute("board", board);
        httpServletRequest.setAttribute("currTurn", whitePlayer);

        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        ArrayList<String> queue = (ArrayList<String>) session.getAttribute("queue");
        String username = (String) session.getAttribute("username");
        queue.remove(username);

        int fi = Integer.parseInt(httpServletRequest.getParameter("fromi"));
        int fj = Integer.parseInt(httpServletRequest.getParameter("fromj"));

        int ti = Integer.parseInt(httpServletRequest.getParameter("toi"));
        int tj = Integer.parseInt(httpServletRequest.getParameter("toj"));
        Coordinate from = new Coordinate(fi, fj);
        Coordinate to = new Coordinate(ti, tj);

        int col = Integer.parseInt(httpServletRequest.getParameter("col"));


        if (board.makeMove(from, to, col)) {
            if (currTurn == pieceEnum.WHITE) {
                if(board.isCheckMate(pieceEnum.BLACK)){
                    currTurn = pieceEnum.END;
                    httpServletRequest.setAttribute("currTurn", gameOver);
                    httpServletRequest.setAttribute("winner", pieceEnum.WHITE);
                }else if (board.isDraw(pieceEnum.BLACK)) {
                    currTurn = pieceEnum.END;
                    httpServletRequest.setAttribute("currTurn", gameOver);
                    httpServletRequest.setAttribute("winner", pieceEnum.END);

                }else {
                    currTurn = pieceEnum.BLACK;
                    httpServletRequest.setAttribute("currTurn", blackPlayer);
                }
            } else {
                if(board.isCheckMate(pieceEnum.WHITE)){
                    currTurn = pieceEnum.END;
                    httpServletRequest.setAttribute("currTurn", gameOver);
                    httpServletRequest.setAttribute("winner", pieceEnum.BLACK);
                }else if (board.isDraw(pieceEnum.WHITE)) {
                    currTurn = pieceEnum.END;
                    httpServletRequest.setAttribute("currTurn", gameOver);
                    httpServletRequest.setAttribute("winner", pieceEnum.END);
                }else {
                    currTurn = pieceEnum.WHITE;
                    httpServletRequest.setAttribute("currTurn", whitePlayer);
                }
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
