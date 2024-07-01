<%@ page import="javaClasses.Board" %>
<%@ page import="javaClasses.Piece" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chessboard</title>
    <link rel="stylesheet" href="styles.css">
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
            cursor: pointer;
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

        .selectedLight {
            background-color: #eaf0b5;
        }

        .selectedDark {
            background-color: #b5b163;
        }
    </style>
</head>
<body>
<div class="chessboard">
    <%
        Board b = (Board) request.getAttribute("board");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = b.getPiece(i, j);
                String id = p != null ? Integer.toString(p.color() * 10 + p.getType()) : null;
                String imgSrc = id != null ? "pieceImages/" + id + ".png" : null;
                String squareClass = ((i + j) % 2 == 0) ? "light" : "dark";
    %>
    <div class="<%= squareClass %>" onclick="squareClicked(this, <%= i %>, <%= j %>)">
        <% if (imgSrc != null) { %>
        <img src="<%= imgSrc %>" alt="<%= id %>" class="img">
        <% } %>
    </div>
    <%
            }
        }
    %>
</div>

<script>
    var selectedPiece = null;
    var selectedSquare = null;

    function squareClicked(square, row, col) {
        if (selectedPiece === null) {
            if (square.querySelector('img')) {
                selectedPiece = square.querySelector('img');
                selectedSquare = { row: row, col: col };
                square.classList.add('selected');
            }
        } else {
            var fromRow = selectedSquare.row;
            var fromCol = selectedSquare.col;

            if (square.querySelector('img')) {
                var originalSquare = document.querySelector('.chessboard').childNodes[fromRow * 8 + fromCol];
                originalSquare.appendChild(selectedPiece);
                originalSquare.classList.remove('selected');
            } else {
                square.appendChild(selectedPiece);
            }

            selectedPiece = null;
            selectedSquare = null;
        }

        var initialClass = square.classList.contains('light') ? 'selectedLight' : 'selectedDark';
        square.classList.toggle(initialClass);
    }
</script>

</body>
</html>
