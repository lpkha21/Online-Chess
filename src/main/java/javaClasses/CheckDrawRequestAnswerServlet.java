package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckDrawRequestAnswerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        Player p = (Player) session.getAttribute("player");

        int answerDraw = 0;
        if(p.getColor() == pieceEnum.WHITE){
            if(game.answerDrawBlack){
                answerDraw = 1;
                game.answerDrawBlack = false;
            };
        }else{
            if(game.answerDrawWhite){
                answerDraw = 1;
                game.answerDrawWhite = false;
            }

        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"answerDraw\": " + answerDraw + "}");



    }
}
