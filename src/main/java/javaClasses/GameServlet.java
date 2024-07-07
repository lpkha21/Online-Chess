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
        Player player = (Player) session.getAttribute("player");

        httpServletRequest.setAttribute("board", game.getBoard());

        String t = game.time;
        int time = Integer.parseInt(t);
        time = time*60;

        if(game.lose(session)){
            session.removeAttribute("myTimer");
            session.removeAttribute("opponentTimer");
            session.removeAttribute("game");
            httpServletRequest.setAttribute("result","Lose");
            httpServletRequest.getRequestDispatcher("resultGame.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(game.draw(player.getColor())){
            session.removeAttribute("myTimer");
            session.removeAttribute("opponentTimer");
            session.removeAttribute("game");
            httpServletRequest.setAttribute("result","Draw");
            httpServletRequest.getRequestDispatcher("resultGame.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }



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

        HttpSession session = httpServletRequest.getSession();
        Game game = (Game) session.getAttribute("game");
        Player player = (Player) session.getAttribute("player");

        if(httpServletRequest.getAttribute("coordinate") != null){
            game.changeColor(session);
            Coordinate c = (Coordinate) (httpServletRequest.getAttribute("coordinate"));
            Piece promoted;
            String type = (String) httpServletRequest.getAttribute("type");
            if(type.equals("Queen")){
                promoted = new Queen(c.i,c.j,game.getBoard(),game.current);
            } else if (type.equals("Rook")) {
                promoted = new Rook(c.i,c.j,game.getBoard(),game.current);
            } else if (type.equals("Bishop")) {
                promoted = new Bishop(c.i,c.j,game.getBoard(),game.current);
            }else {
                promoted = new Knight(c.i,c.j,game.getBoard(),game.current);
            }
            game.getBoard().setPiece(c.i,c.j,promoted);
        }else {

            int fi = Integer.parseInt(httpServletRequest.getParameter("fromi"));
            int fj = Integer.parseInt(httpServletRequest.getParameter("fromj"));

            int ti = Integer.parseInt(httpServletRequest.getParameter("toi"));
            int tj = Integer.parseInt(httpServletRequest.getParameter("toj"));
            Coordinate from = new Coordinate(fi, fj);
            Coordinate to = new Coordinate(ti, tj);


            game.changeBoard(session, from, to);

            httpServletRequest.setAttribute("board", game.getBoard());

            if (game.getBoard().promotion(to)) {
                session.setAttribute("promotion", to);
            }
        }

        if(game.win(session)){
            session.removeAttribute("myTimer");
            session.removeAttribute("opponentTimer");
            session.removeAttribute("game");
            httpServletRequest.setAttribute("result","Win");
            httpServletRequest.getRequestDispatcher("resultGame.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        if(player.getColor() == pieceEnum.WHITE){
            if(game.draw(pieceEnum.BLACK)){
                session.removeAttribute("myTimer");
                session.removeAttribute("opponentTimer");
                session.removeAttribute("game");
                httpServletRequest.setAttribute("result","Draw");
                httpServletRequest.getRequestDispatcher("resultGame.jsp").forward(httpServletRequest, httpServletResponse);
                return;
            }
        }else{
            if(game.draw(pieceEnum.WHITE)){
                session.removeAttribute("myTimer");
                session.removeAttribute("opponentTimer");
                session.removeAttribute("game");
                httpServletRequest.setAttribute("result","Draw");
                httpServletRequest.getRequestDispatcher("resultGame.jsp").forward(httpServletRequest, httpServletResponse);
                return;
            }
        }

        int myTimer = (int) session.getAttribute("myTimer");
        int opponentTimer = (int) session.getAttribute("opponentTimer");
        httpServletRequest.setAttribute("myTimer",myTimer);
        httpServletRequest.setAttribute("opponentTimer",opponentTimer);
        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);

    }
}
