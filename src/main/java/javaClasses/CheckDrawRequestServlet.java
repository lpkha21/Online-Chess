package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckDrawRequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        Player p = (Player) session.getAttribute("player");

        int drawRequest = 0;
        if(p.getColor() == pieceEnum.WHITE){
            if(game.requestDrawWhite){
                drawRequest = 1;
            };
        }else{
            if(game.requestDrawBlack){
                drawRequest = 1;
            }

        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"drawRequest\": " + drawRequest + "}");



    }
}
