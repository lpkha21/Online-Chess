<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/1/2024
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chessboard</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }
        .chessboard {
            display: grid;
            grid-template-columns: repeat(8, 50px);
            grid-template-rows: repeat(8, 50px);
            gap: 0;
            border: 2px solid #333;
        }
        .chessboard div {
            width: 50px;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
        }
        .white {
            background-color: #f0d9b5;
        }
        .black {
            background-color: #b58863;
        }
        .piece {
            font-size: 32px;
        }
    </style>
    <script>
        function handleMouseEnter(event) {
            event.target.style.backgroundColor = 'yellow';
        }

        function handleMouseLeave(event) {
            const originalColor = event.target.classList.contains('white') ? '#f0d9b5' : '#b58863';
            event.target.style.backgroundColor = originalColor;
        }

        function handleClick(event) {
            alert('Clicked on ' + event.target.id);
        }

        function initializeChessboard() {
            const cells = document.querySelectorAll('.chessboard div');
            cells.forEach(cell => {
                cell.addEventListener('mouseenter', handleMouseEnter);
                cell.addEventListener('mouseleave', handleMouseLeave);
                cell.addEventListener('click', handleClick);
            });
        }

        window.onload = initializeChessboard;
    </script>
</head>
<body>
<div class="chessboard">
    <%
        String[] initialBoard = {
                "&#9814;", "&#9816;", "&#9815;", "&#9813;", "&#9812;", "&#9815;", "&#9816;", "&#9814;", // White back row
                "&#9817;", "&#9817;", "&#9817;", "&#9817;", "&#9817;", "&#9817;", "&#9817;", "&#9817;", // White pawns
                "", "", "", "", "", "", "", "", // Empty row
                "", "", "", "", "", "", "", "", // Empty row
                "", "", "", "", "", "", "", "", // Empty row
                "", "", "", "", "", "", "", "", // Empty row
                "&#9823;", "&#9823;", "&#9823;", "&#9823;", "&#9823;", "&#9823;", "&#9823;", "&#9823;", // Black pawns
                "&#9820;", "&#9822;", "&#9821;", "&#9819;", "&#9818;", "&#9821;", "&#9822;", "&#9820;"  // Black back row
        };

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String color = (row + col) % 2 == 0 ? "white" : "black";
                String piece = initialBoard[row * 8 + col];
                String cellId = "cell" + row + col;
    %>
    <div class="<%= color %>" id="<%= cellId %>">
        <span class="piece"><%= piece %></span>
    </div>
    <%
            }
        }
    %>
</div>
</body>
</html>

