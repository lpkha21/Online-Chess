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


    %>


    <% for (int i = 0; i < 8; i++) { %>
    <% for (int j = 0; j < 8; j++) { %>
    <%
        Piece p = b.getPiece(i, j);
        String id = p != null ? Integer.toString(p.color() * 10 + p.getType()) : null;
        String imgSrc = id != null ? "pieceImages/" + id + ".png" : null;
        String squareClass = ((i + j) % 2 == 0) ? "light" : "dark";
    %>
            <div class="<%= squareClass %>" onclick="clicked( <%= i%>, <%= j%>)">
                <div class="square">
                    <% if (imgSrc != null) { %>
                    <img src="<%= imgSrc %>" alt="<%= id %>" class="img">
                    <% } %>
                </div>
            </div>

            <% }
         } %>


</div>
<form id="form" action="Game" method="POST">
    <input type="hidden" name="fromi" id="fromi" value="">
    <input type="hidden" name="fromj" id="fromj" value="">
    <input type="hidden" name="toi" id="toi" value="">
    <input type="hidden" name="toj" id="toj" value="">
    <input type="hidden" name="col" id="col" value="">
</form>
<script>

    var flag = false;

    function clicked(i, j){
        if(!flag){
            document.getElementById("fromi").value = i;
            document.getElementById("fromj").value = j;
            flag = !flag;
        }
        else{
            flag = !flag;

            document.getElementById("toi").value = i;
            document.getElementById("toj").value = j;
            document.getElementById("col").value = 1;
            document.getElementById("form").submit();

        }
    }

</script>

</body>
</html>
