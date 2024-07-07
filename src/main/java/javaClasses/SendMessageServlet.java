package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        Player player = (Player) session.getAttribute("player");

        String message = request.getParameter("message");
        if (game != null && player != null && message != null && !message.trim().isEmpty()) {
            game.messageSent(player, message);
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
