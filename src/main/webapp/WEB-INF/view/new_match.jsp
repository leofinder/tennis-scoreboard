<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match. Tennis Scoreboard</title>
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
                    <a href="/new-match" class="header-menu__link current">New Match</a>
                </li>
                <li class="header-menu__item">
                    <a href="/matches" class="header-menu__link">Matches</a>
                </li>
            </ul>
        </nav>
    </header>
    <main>
        <section>
            <h1>New Match</h1>
            <div class="new-match-container">
                <form action="/new-match" method="POST">
                    <fieldset>
                        <legend>Enter Player Names</legend>

                        <label for="player-one">Player 1 name:</label>
                        <input type="text" id="player-one" name="player-one" required>

                        <label for="player-two">Player 2 name:</label>
                        <input type="text" id="player-two" name="player-two" required>

                        <button type="submit">Play</button>
                    </fieldset>
                </form>
            </div>
        </section>
    </main>
</body>
</html>
