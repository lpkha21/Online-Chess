<%@ page import="javaClasses.*" %>
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

    <% Game game = (Game) session.getAttribute("game");
        Player pl =  (Player) session.getAttribute("player");
//        int myT = Integer.parseInt(request.getParameter("myTimer"));
//        int oppT = Integer.parseInt(request.getParameter("opponentTimer"));
    %>

    <script type="text/javascript">
        // Get the countdown time from the request attribute
        var myTimer = <%= (request.getAttribute("myTimer")) %>;
        var opponentTimer = <%= (request.getAttribute("opponentTimer")) %>;



        // Function to start the countdown timer
        function startTimer(timerId, countdownTime) {
            var timer = document.getElementById(timerId);
            var minutes, seconds;
            var interval = setInterval(function() {
                minutes = parseInt(countdownTime / 60, 10);
                seconds = parseInt(countdownTime % 60, 10);

                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                timer.textContent = minutes + ":" + seconds;
                countdownTime--;
                if (timerId === "timer2") {
                    myTimer = countdownTime;
                } else {
                    opponentTimer = countdownTime;
                }

                if (countdownTime < 0) {
                    clearInterval(interval);
                    alert("Time's up for " + timerId + "!");
                }
            }, 1000);
        }

        window.onload = function() {
            <%
                if(game.current == pl.getColor()){
                    %>
                    startTimer("timer2", myTimer); // Start your timer next
                <%}else{
                    %>
                    startTimer("timer1", opponentTimer); // Start opponent's timer first
                <%}%>
        };
    </script>

</head>

<body>
<div class="chessboard-container">
    <div class="timer" style="position: absolute; top: 20px; left: 20px;">
        <div class="timer-label">Opponent's Timer</div>
        <div id="timer1"></div> <!-- Opponent's timer -->
    </div>
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
    <div class="timer" style="position: absolute; bottom: 20px; left: 20px;">
        <div class="timer-label">Your Timer</div>
        <div id="timer2"></div> <!-- Your timer -->
    </div>
</div>

<form id="form" action="Game" method="POST">
    <input type="hidden" name="fromi" id="fromi" value="">
    <input type="hidden" name="fromj" id="fromj" value="">
    <input type="hidden" name="toi" id="toi" value="">
    <input type="hidden" name="toj" id="toj" value="">
    <input type="hidden" name="myTimer" id="myTimer" value="">
    <input type="hidden" name="opponentTimer" id="opponentTimer" value="">
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
            document.getElementById("myTimer").value = myTimer;
            document.getElementById("opponentTimer").value = opponentTimer;


            var squares = document.querySelectorAll('.square');
            squares.forEach(function (square){
                square.classList.remove('selected');
            });

            document.getElementById("form").submit();

        }
    }

</script>

</body>
</html>
