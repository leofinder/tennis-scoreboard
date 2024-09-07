<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard</title>
</head>

<body>
<nav class="top-menu">
    <ul class="menu-main">
        <li><a href="index.html">Main</a></li>
        <li><a href="">Matches</a></li>
    </ul>
</nav>
<section>
    <form action="/new-match" method="POST">
        <label for="player1">Player 1 name:</label>
        <input type="text" id="player1" name="player1" required>
        <br><br>

        <label for="player2">Player 2 name:</label>
        <input type="text" id="player2" name="player2" required>
        <br><br>

        <button type="submit">Play</button>
    </form>
</section>
</body>

</html>
