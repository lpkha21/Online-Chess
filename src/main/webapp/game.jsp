<%@ page import="javaClasses.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLOutput" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chessboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <form id="Draw" action="Game" method="get">
        <input type="hidden" name="answer" id="answer" value=""/>
    </form>

    <script>
        function showDraw() {
            document.getElementById('draw').style.display = 'block';
            document.getElementById('drawOverlay').style.display = 'block';
        }

        function hideDraw() {
            document.getElementById('draw').style.display = 'none';
            document.getElementById('drawOverlay').style.display = 'none';
        }

        function acceptAction() {
            document.getElementById('answer').value = 'yes';
            hideDraw();
            document.getElementById('Draw').submit();
        }

        function declineAction() {
            document.getElementById('answer').value = 'no';
            hideDraw();
            document.getElementById('Draw').submit();
        }

        function showResult() {
            document.getElementById('result').style.display = 'block';
            document.getElementById('resultOverlay').style.display = 'block';
        }

        function hideResult() {
            document.getElementById('result').style.display = 'none';
            document.getElementById('resultOverlay').style.display = 'none';
        }
    </script>

    <form id="promotion" action="Game" method="POST">
        <input type="hidden" name="coordinate" id="coordinate" value=""/>
        <input type="hidden" name="type" id="type" value=""/>
    </form>


    <% Game game = (Game) session.getAttribute("game");
        Player pl =  (Player) session.getAttribute("player");
        Coordinate coordinate = (Coordinate) session.getAttribute("promotion");
        String result = (String) request.getAttribute("result");
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

        function showPopup() {
            document.getElementById('popup').style.display = 'block';
            document.getElementById('popupOverlay').style.display = 'block';
        }

        function hidePopup() {
            document.getElementById('popup').style.display = 'none';
            document.getElementById('popupOverlay').style.display = 'none';
        }

        function choosePiece(piece) {
            document.getElementById("coordinate").value = piece;
            document.getElementById("type").value = piece;
            document.getElementById("promotion").submit();
            hidePopup();
        }

        function handlePageLoad() {
            if (<%= coordinate != null %>) {
                document.getElementById("coordinate").value = "<%= coordinate %>";
                showPopup();
            }

            if(<%=result != null%>){
                showResult();
            }

            if (<%= game.current == pl.getColor() %>) {
                startTimer("timer2", myTimer); // Start your timer
            } else {
                startTimer("timer1", opponentTimer); // Start opponent's timer
            }
        }

        window.onload = handlePageLoad;

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
<div id="drawOverlay" class="draw-overlay"></div>

<!-- Draw content -->
<div id="draw" class="draw">
    <div class="draw-content">
        <p>Draw?</p>
        <div class="draw-group">
            <button class="accept" onclick="acceptAction()">Accept</button>
            <button class="decline" onclick="declineAction()">Decline</button>
        </div>
    </div>
</div>
<%
    String res = (String) request.getAttribute("result");
    String winner = ((Player) session.getAttribute("player")).getName();
    String loser = ((Player) session.getAttribute("opponent")).getName();
    String winnerImage = "pieceImages/win.jpg";
    String text = "Winner: " + winner;
    if(res != null) {
        if (res.equals("Lose")){
            String temp = winner;
            winner = loser;
            loser = temp;
            text = "Winner: " + winner;
        }else if (res.equals("Draw")) {
            winnerImage = "pieceImages/lose.jpg";
            text = "Draw";
        }
    }
%>
<div id="resultOverlay" class="result-overlay"></div>
<!-- Popup content -->
<div id="result" class="result">
    <button class="close-button" onclick="hideResult()">X</button>
    <div class="result-content">
        <div class="image-container">
            <img src=<%=winnerImage%>>
            <img src="pieceImages/lose.jpg">
        </div>
        <div class="text-container">
            <div id="leftText"><%=winner%></div>
            <div id="rightText"><%=loser%></div>
        </div>
        <div id="Text"><%=text%></div>
    </div>
</div>

<%
    String queenImage;
    String rookImage;
    String bishopImage;
    String knightImage;
    if(((Player) session.getAttribute("player")).getColor() == pieceEnum.BLACK){
        queenImage = "pieceImages/5.png";
        rookImage = "pieceImages/4.png";
        bishopImage = "pieceImages/3.png";
        knightImage = "pieceImages/2.png";
    }else{
        queenImage = "pieceImages/15.png";
        rookImage = "pieceImages/14.png";
        bishopImage = "pieceImages/13.png";
        knightImage = "pieceImages/12.png";
    }
%>

<div id="popupOverlay" class="popup-overlay"></div>

<!-- Popup content -->
<div id="popup" class="popup">
    <h3>Choose a Piece</h3>
    <div class="button-group">
        <button onclick="choosePiece('Queen')">
            <img src=<%=queenImage%> alt="Queen">
        </button>
        <button onclick="choosePiece('Rook')">
            <img src=<%=rookImage%> alt="Rook">
        </button>
        <button onclick="choosePiece('Bishop')">
            <img src=<%=bishopImage%> alt="Bishop">
        </button>
        <button onclick="choosePiece('Knight')">
            <img src=<%=knightImage%> alt="Knight">
        </button>
    </div>
</div>

<div class="chessboard-container">
    <div class="timer" style="position: absolute; top: 30px; left: 100px;">

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
    <div class="timer" style="position: absolute; bottom: 60px; left: 100px;">

        <div id="timer2"></div> <!-- Your timer -->
    </div>
</div>

<form id="form" action="Game" method="POST">
    <input type="hidden" name="fromi" id="fromi" value="">
    <input type="hidden" name="fromj" id="fromj" value="">
    <input type="hidden" name="toi" id="toi" value="">
    <input type="hidden" name="toj" id="toj" value="">
</form>

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
        <% } %>
    </div>

    <div class="input-container">
        <input type="text" id="messageInput" class="message-input" placeholder="Type your message here...">
        <button class="send-button" onclick="sendMessage()">Send</button>
    </div>

    <div class="rd">
        <form action="Game" method="get">
            <input type="hidden" name="resign" value="resign">
            <input type="submit"  value="🏳️ Resign">
        </form>
        <form action="Game" method="get">
            <input type="hidden" name="dr" value="dr">
            <input type="submit" value="🤝🏻 Draw">
        </form>
    </div>
</div>


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

    function checkDrawRequest() {
        $.ajax({
            url: '/CheckDrawRequestServlet',
            type: 'POST',
            dataType: 'json',
            success: function(data) {
                if(data.drawRequest === 1){
                    showDraw();
                }else{
                    setTimeout(checkDrawRequest, 1000);
                }

            },
            error: function() {
                setTimeout(checkDrawRequest, 1000);
            }
        });
    }

    $(document).ready(function() {+
        checkDrawRequest();
    });

    function checkDrawRequestAnswer() {
        $.ajax({
            url: '/CheckDrawRequestAnswerServlet',
            type: 'POST',
            dataType: 'json',
            success: function(data) {
                if(data.answerDraw === 1){
                    document.getElementById("acceptDr").submit();
                }else{
                    setTimeout(checkDrawRequestAnswer, 1000);
                }

            },
            error: function() {
                setTimeout(checkDrawRequestAnswer, 1000);
            }
        });
    }

    $(document).ready(function() {
        checkDrawRequestAnswer();
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
                    // Clear the input field and update the message box
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
        setInterval(updateMessages, 3000);
    });

</script>




<form id="acceptDr"  action="Game" method="get">
    <input type="hidden" name="acceptDraw" value="accept">
</form>


</body>
</html>
