<%@ page import="javaClasses.Board" %>
<%@ page import="javaClasses.Piece" %>
<%@ page import="javaClasses.Player" %>
<%@ page import="javaClasses.pieceEnum" %>
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

        .selected {
            background-color: #b5b163;
        }

    </style>

    <script type="text/javascript">
        var timer1, timer2;
        var count1 = 600, count2 = 600;

        function formatTime(count) {
            var minutes = Math.floor(count / 60);
            var seconds = count % 60;
            return `${minutes} : ${seconds}`;
        }

        function updateTimerDisplay(timerNum) {
            var displayId = (timerNum === 1) ? 'timerDisplay1' : 'timerDisplay2';
            document.getElementById(displayId).innerText = formatTime(window['count' + timerNum]);
        }

        function startTimer(timerNum) {
            if (timerNum === 1 && !timer1) {
                timer1 = setInterval(function() {
                    if (count1 > 0) {
                        count1--;
                        updateTimerDisplay(1);
                    } else {
                        clearInterval(timer1);
                        timer1 = null;
                    }
                }, 1000);
            } else if (timerNum === 2 && !timer2) {
                timer2 = setInterval(function() {
                    if (count2 > 0) {
                        count2--;
                        updateTimerDisplay(2);
                    } else {
                        clearInterval(timer2);
                        timer2 = null;
                    }
                }, 1000);
            }
        }

        function stopTimer(timerNum) {
            if (timerNum === 1 && timer1) {
                clearInterval(timer1);
                timer1 = null;
            } else if (timerNum === 2 && timer2) {
                clearInterval(timer2);
                timer2 = null;
            }
        }

        function resetTimer(timerNum) {
            if (timerNum === 1) {
                clearInterval(timer1);
                timer1 = null;
                count1 = 300;
                updateTimerDisplay(1);
            } else if (timerNum === 2) {
                clearInterval(timer2);
                timer2 = null;
                count2 = 180;
                updateTimerDisplay(2);
            }
        }
    </script>

</head>

<body>
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

                boolean isBottomPlayerPiece = (isWhitePlayer && p != null && p.color() == pieceEnum.WHITE) ||
                        (!isWhitePlayer && p != null && p.color() == pieceEnum.BLACK);

    %>
    <div class="<%= squareClass %>" onclick="clicked(this, <%= rowIndex %>, <%= colIndex %>)">
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
<form id="form" action="Game" method="POST">
    <input type="hidden" name="fromi" id="fromi" value="">
    <input type="hidden" name="fromj" id="fromj" value="">
    <input type="hidden" name="toi" id="toi" value="">
    <input type="hidden" name="toj" id="toj" value="">
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function updateBoardState() {
        $.ajax({
            url: '/BoardStateServlet',
            type: 'POST',
            dataType: 'json',
            success: function(data) {
                if(data.repaint === 1){
                    window.location.href = '/Game';
                }else{
                    setTimeout(updateBoardState, 1000);
                }

            },
            error: function() {
                setTimeout(updateBoardState, 1000);
            }
        });
    }

    $(document).ready(function() {
        updateBoardState();
    });

    var flag = false;

    function clicked(square, i, j){

        let img = square.querySelector('img');
        if(!flag){
            if(img !== null) {
                document.getElementById("fromi").value = i;
                document.getElementById("fromj").value = j;
                flag = !flag;
                square.classList.add('selected');
            }
        }else{
            flag = !flag;

            document.getElementById("toi").value = i;
            document.getElementById("toj").value = j;

            var squares = document.querySelectorAll('.square');
            squares.forEach(function (square){
                square.classList.remove('selected');
            });

            document.getElementById("form").submit();

        }
    }

</script>

<div>
    <h2>Black Player</h2>
    <p>time:  <span id="timerDisplay2">10 : 0</span></p>
</div>

<div>
    <h2>White Player</h2>
    <p>time:  <span id="timerDisplay1">10 : 0</span></p>
</div>

<%--<div>--%>
<%--    <%--%>
<%--        String message = "";--%>
<%--        if(((Player) request.getAttribute("currTurn")).getColor() == pieceEnum.END){--%>
<%--            int winner = ((Integer) request.getAttribute("winner"));--%>
<%--            if(winner == pieceEnum.BLACK)--%>
<%--                message = "BLACK WINS!!!";--%>
<%--            else if(winner == pieceEnum.WHITE)--%>
<%--                message = "WHITE WINS!!!";--%>
<%--            else--%>
<%--                message = "DRAW";--%>
<%--        }--%>

<%--    %>--%>
<%--    <h1><%= message %></h1>--%>
<%--</div>--%>

</body>
</html>
