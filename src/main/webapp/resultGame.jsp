<%@ page import="javaClasses.pieceEnum" %>
<%@ page import="javaClasses.Player" %>
<%@ page import="javaClasses.Board" %>
<%@ page import="javaClasses.Piece" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/7/2024
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Of Game</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: flex-start; /* Align items at the top of the screen */
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }

        .chessboard-container {
            display: flex;
            justify-content: flex-start; /* Align items to the left */
            align-items: flex-start; /* Align items at the top of the container */
            margin-left: 20px; /* Adjust left margin for spacing */
        }

        .timer {
            font-size: 24px;
            font-weight: bold;
            padding: 10px;
            border: 2px solid #333;
            background-color: #fff;
            margin-right: 20px; /* Adjust right margin for spacing */
            text-align: center;
        }

        .timer-label {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .chessboard {
            display: grid;
            grid-template-columns: repeat(8, 80px);
            grid-template-rows: repeat(8, 80px);
            border: 5px solid #333;
        }

        .square {
            width: 80px;
            height: 80px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .light {
            background-color: #f0d9b5;
        }

        .dark {
            background-color: #b58863;
        }

        .img {
            width: 80px;
            height: auto;
        }
    </style>
</head>
<body>
<h1>You <%= request.getAttribute("result")%></h1>
<div class="chessboard-container">
    <div class="chessboard">
        <%
            Player player = (Player) session.getAttribute("player");
            Board b = (Board) request.getAttribute("board");

            boolean isWhitePlayer = (player != null && player.getColor() == pieceEnum.WHITE);

            boolean rotateBoard = !isWhitePlayer;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int rowIndex = i;
                    int colIndex = j;

                    if (rotateBoard) {
                        rowIndex = 7 - i;
                        colIndex = 7 - j;
                    }

                    Piece p = b.getPiece(rowIndex, colIndex);
                    String id = p != null ? Integer.toString(p.color() * 10 + p.getType()) : null;
                    String imgSrc = id != null ? "pieceImages/" + id + ".png" : null;
                    String squareClass = ((rowIndex + colIndex) % 2 == 0) ? "light" : "dark";

        %>
        <div class="<%= squareClass %>">
            <div class="square">
                <% if (imgSrc != null) { %>
                <img src="<%= imgSrc %>" alt="<%= id %>" class="img">
                <% } %>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
<form action="BackToProfileServlet" method="Get">
    <input type="submit" value="Back To Profile">
</form>

</body>
</html>
