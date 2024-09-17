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
    <header>
        <nav class="header-menu">
            <ul class="header-menu__list">
                <li class="header-menu__item">
                    <a href="/" class="header-menu__link">Main</a>
                </li>
                <li class="header-menu__item">
                    <a href="/new-match" class="header-menu__link">New Match</a>
                </li>
                <li class="header-menu__item">
                    <a href="/matches" class="header-menu__link">Matches</a>
                </li>
            </ul>
        </nav>
    </header>
    <main>
        <section>
            <h1>Tennis Score Tracker</h1>
            <div class="score-container">
                <div class="score-header">
                    <div></div>
                    <div>SETS</div>
                    <div>GAMES</div>
                    <div class="${match.score.tiebreak ? 'hidden' : ''}">POINTS</div>
                    <div class="${match.score.tiebreak ? '' : 'hidden'}">TIE-BREAK</div>
                </div>

                <div class="score-row">
                    <div>${match.playerOne.name}</div>
                    <div id="player1-set">${match.score.playerOneScore.set}</div>
                    <div id="player1-game">${match.score.playerOneScore.game}</div>
                    <div id="player1-points" class="${match.score.tiebreak ? 'hidden' : ''}">${match.score.playerOneScore.point}</div>
                    <div id="player1-tiebreak" class="${match.score.tiebreak ? '' : 'hidden'}">${match.score.playerOneScore.tiebreak}</div>
                </div>

                <div class="score-row">
                    <div>${match.playerTwo.name}</div>
                    <div id="player2-set">${match.score.playerTwoScore.set}</div>
                    <div id="player2-game">${match.score.playerTwoScore.game}</div>
                    <div id="player2-points" class="${match.score.tiebreak ? 'hidden' : ''}">${match.score.playerTwoScore.point}</div>
                    <div id="player2-tiebreak" class="${match.score.tiebreak ? '' : 'hidden'}">${match.score.playerTwoScore.tiebreak}</div>
                </div>

                <form action="/match-score?uuid=${uuid}" method="POST" onsubmit="setTimeout(disableButtons, 0)">
                    <div class="score-button-container">
                        <button type="submit" class="score-button" name="player" value="player-one">Player 1 won the point</button>
                        <button type="submit" class="score-button" name="player" value="player-two">Player 2 won the point</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
    <script>
        function disableButtons() {
            const buttons = document.querySelectorAll('.score-button');
            buttons.forEach(button => button.disabled = true);
        }
    </script>
</body>
</html>
