package javaClasses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetMessagesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (game != null) {
            ArrayList<Message> messages = game.messageGet();
            StringBuilder jsonMessages = new StringBuilder("[");

            for (int i = 0; i < messages.size(); i++) {
                Message msg = messages.get(i);
                jsonMessages.append("{");
                jsonMessages.append("\"color\":").append(msg.getColor()).append(",");
                jsonMessages.append("\"message\":\"").append(msg.getMessage()).append("\"");
                jsonMessages.append("}");

                if (i < messages.size() - 1) {
                    jsonMessages.append(",");
                }
            }

            jsonMessages.append("]");
            out.print(jsonMessages.toString());
        }

        out.flush();
    }
}
