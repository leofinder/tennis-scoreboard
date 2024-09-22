<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <header>
        <nav class="header-menu">
            <ul class="header-menu__list">
                <li class="header-menu__item">
                    <a href="/" class="header-menu__link current">Main</a>
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
            <h1>Welcome to the Tennis Scoreboard</h1>
            <div>
                <ul class="main__list">
                    <li><a href="/new-match" class="main__link">Create a New Match</a></li>
                    <li><a href="/matches" class="main__link">View Matches</a></li>
                </ul>
            </div>
        </section>
    </main>
</body>
</html>
