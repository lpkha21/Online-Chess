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

        if(httpServletRequest.getParameter("dr") != null){
            game.RequestDraw(session);
        }

        if(httpServletRequest.getParameter("resign") != null){
            game.Resign(session);
            game.changeColor(session);
            httpServletRequest.setAttribute("result","Lose");
        }

        if((player.getColor() == pieceEnum.WHITE && game.blackResign) || (player.getColor() == pieceEnum.BLACK && game.whiteResign)){
            httpServletRequest.setAttribute("result","Win");
        }

        if(game.lose(session)){
            httpServletRequest.setAttribute("result","Lose");
        }

        if(game.draw(player.getColor())){
            httpServletRequest.setAttribute("result","Draw");
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
        httpServletRequest.setAttribute("board", game.getBoard());

        if(httpServletRequest.getParameter("coordinate") != null){
            Coordinate c = (Coordinate) session.getAttribute("promotion");
            session.removeAttribute("promotion");
            Piece promoted;
            String type = httpServletRequest.getParameter("type");
            if(type.equals("Queen")){
                promoted = new Queen(c.i,c.j,game.getBoard(),game.current);
            } else if (type.equals("Rook")) {
                promoted = new Rook(c.i,c.j,game.getBoard(),game.current);
            } else if (type.equals("Bishop")) {
                promoted = new Bishop(c.i,c.j,game.getBoard(),game.current);
            }else {
                promoted = new Knight(c.i,c.j,game.getBoard(),game.current);
            }
            game.changeColor(session);
            game.getBoard().setPiece(c.i,c.j,promoted);
        }else {

            int fi = Integer.parseInt(httpServletRequest.getParameter("fromi"));
            int fj = Integer.parseInt(httpServletRequest.getParameter("fromj"));

            int ti = Integer.parseInt(httpServletRequest.getParameter("toi"));
            int tj = Integer.parseInt(httpServletRequest.getParameter("toj"));
            Coordinate from = new Coordinate(fi, fj);
            Coordinate to = new Coordinate(ti, tj);


            game.changeBoard(session, from, to);

            if (game.getBoard().promotion(to)) {
                session.setAttribute("promotion", to);
            }
        }

        if(game.win(session)){
            httpServletRequest.setAttribute("result","Win");
        }

        if(player.getColor() == pieceEnum.WHITE){
            if(game.draw(pieceEnum.BLACK)){
                httpServletRequest.setAttribute("result","Draw");
            }
        }else{
            if(game.draw(pieceEnum.WHITE)){
                httpServletRequest.setAttribute("result","Draw");
            }
        }

        int myTimer = (int) session.getAttribute("myTimer");
        int opponentTimer = (int) session.getAttribute("opponentTimer");
        httpServletRequest.setAttribute("myTimer",myTimer);
        httpServletRequest.setAttribute("opponentTimer",opponentTimer);
        httpServletRequest.getRequestDispatcher("game.jsp").forward(httpServletRequest, httpServletResponse);

    }
}
