<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score. Tennis Scoreboard</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <main>
        <section>
            <h1>Tennis Score Tracker</h1>
            <div class="score-container">
                <div class="score-header">
                    <div></div>
                    <div>SETS</div>
                    <div>GAMES</div>
                    <div>POINTS</div>
                </div>

                <div class="score-row">
                    <div>Player 1</div>
                    <div id="player1-set">0</div>
                    <div id="player1-game">0</div>
                    <div id="player1-points">0</div>
                </div>

                <div class="score-row">
                    <div>Player 2</div>
                    <div id="player2-set">0</div>
                    <div id="player2-game">0</div>
                    <div id="player2-points">0</div>
                </div>

                <form action="/match-score" method="POST">
                    <div class="score-button-container">
                        <button type="submit" class="score-button" name="player" value="player1">Player 1 won the point</button>
                        <button type="submit" class="score-button" name="player" value="player1">Player 2 won the point</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
</body>
</html>
