<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score. Tennis Scoreboard</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <main>
        <section>
            <h1>Match Result</h1>
            <div class="match-result-card">
                <div class="match-result-card__players">
                    <div class="_match-result-card__player">
                        <strong>${match.playerOne.name}</strong>
                        <div class="match-result-card__set-points">${match.score.playerOneScore.set}</div>
                    </div>
                    <div class="match-result-card__vs">vs</div>
                    <div class="match-result-card__player">
                        <strong>${match.playerTwo.name}</strong>
                        <div class="match-result-card__set-points">${match.score.playerTwoScore.set}</div>
                    </div>
                </div>
                <div class="match-result-card__winner">
                    <span>Winner: <strong>${match.winner.name}</strong></span>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
