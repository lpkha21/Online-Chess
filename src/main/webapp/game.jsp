<%@ page import="javaClasses.*" %>
<%@ page import="java.util.ArrayList" %>
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

        .messaging-container {
            display: flex;
            flex-direction: column;
            width: 300px;
            margin-left: 20px;
        }

        .message-box {
            height: 400px;
            overflow-y: auto;
            border: 2px solid #333;
            background-color: #fff;
            padding: 10px;
        }

        .message {
            margin: 5px 0;
            padding: 5px;
            border-radius: 5px;
        }

        .white-message {
            background-color: #f0d9b5;
            align-self: flex-start;
        }

        .black-message {
            background-color: #b58863;
            align-self: flex-end;
        }

        .input-container {
            display: flex;
            margin-top: 10px;
        }

        .message-input {
            flex: 1;
            padding: 10px;
            font-size: 16px;
        }

        .send-button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
        }
    </style>

    <%
        Game game = (Game) session.getAttribute("game");
        Player pl = (Player) session.getAttribute("player");
    %>

    <script type="text/javascript">
        var myTimer = <%= (request.getAttribute("myTimer")) %>;
        var opponentTimer = <%= (request.getAttribute("opponentTimer")) %>;

        function startTimer(timerId, countdownTime) {
            var timer = document.getElementById(timerId);
            var minutes, seconds;
            interval = setInterval(function() {
                minutes = parseInt(countdownTime / 60, 10);
                seconds = parseInt(countdownTime % 60, 10);

                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                timer.textContent = minutes + ":" + seconds;
                countdownTime--;
                if (countdownTime >= 0) {
                    updateTimers()
                } else {
                    clearInterval(interval);
                    alert("Time's up for " + timerId + "!");
                }
            }, 1000);
        }

        window.onload = function() {
            if (<%= game.current == pl.getColor() %>) {
                startTimer("timer2", myTimer); // Start your timer
            } else {
                startTimer("timer1", opponentTimer); // Start opponent's timer
            }
        };

        function updateTimers() {
            if (<%= game.current == pl.getColor() %>) {
                myTimer--;
            } else {
                opponentTimer--;
            }

            $.ajax({
                url: '/UpdateTimersServlet',
                type: 'POST',
                data: {
                    myTimer: myTimer,
                    opponentTimer: opponentTimer
                },
                success: function (data) {
                    // Handle success response if needed
                },
                error: function () {
                    // Handle error if needed
                }
            });
        }
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

    <!-- Messaging section -->
    <div class="messaging-container">
        <div class="message-box" id="messageBox">
            <%
                ArrayList<Message> messages = game.messageGet();

                for (Message msg : messages) {
                    String messageClass = msg.getColor() == pieceEnum.WHITE ? "white-message" : "black-message";
                    String messageContent = msg.getMessage();
            %>
            <div class="message <%= messageClass %>">
                <%= messageContent %>
            </div>
            <%
                }
            %>
        </div>
        <div class="input-container">
            <input type="text" id="messageInput" class="message-input" placeholder="Type your message here...">
            <button class="send-button" onclick="sendMessage()">Send</button>
        </div>
    </div>
</div>

<form id="form" action="Game" method="POST">
    <input type="hidden" name="fromi" id="fromi" value="">
    <input type="hidden" name="fromj" id="fromj" value="">
    <input type="hidden" name="toi" id="toi" value="">
    <input type="hidden" name="toj" value="">
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

    function sendMessage() {
        var message = document.getElementById('messageInput').value;
        if (message.trim() !== "") {
            $.ajax({
                url: '/sendMessage',
                type: 'POST',
                data: {
                    message: message
                },
                success: function(response) {
                    document.getElementById('messageInput').value = '';
                    updateMessages();
                }
            });
        }
    }

    function updateMessages() {
        $.ajax({
            url: '/getMessage',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                var messageBox = document.getElementById('messageBox');
                messageBox.innerHTML = '';

                data.forEach(function(msg) {
                    var messageClass = msg.color == 0 ? 'white-message' : 'black-message'; // Assuming 0 is white and 1 is black
                    var messageDiv = document.createElement('div');
                    messageDiv.className = 'message ' + messageClass;
                    messageDiv.textContent = msg.message;
                    messageBox.appendChild(messageDiv);
                });

                messageBox.scrollTop = messageBox.scrollHeight; // Auto-scroll to the bottom
            }
        });
    }

    $(document).ready(function() {
        updateMessages();
        setInterval(updateMessages, 5000); // Update messages every 5 seconds
    });
</script>
</body>
</html>
