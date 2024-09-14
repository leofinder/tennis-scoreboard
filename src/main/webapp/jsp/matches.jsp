<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches. Tennis Scoreboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
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
                    <a href="/matches" class="header-menu__link current">Matches</a>
                </li>
            </ul>
        </nav>
    </header>
    <main>
        <section>
            <h1>Matches</h1>
            <div class="search-pagination-container">
                <!-- Форма поиска -->
                <form action="/matches" method="GET" class="search-form">
                    <input type="text" name="query" placeholder="Search..." class="search-input" value="">
                    <button type="submit" class="search-button">Search</button>
                    <button type="button" class="clear-button" onclick="clearSearch()">х</button>
                </form>

                <div class="search-results">
                    <!-- Заголовок -->
                    <div class="result-item header">
                        <div class="id">id</div>
                        <div class="player">Player 1</div>
                        <div class="player">Player 2</div>
                        <div class="winner">Winner</div>
                    </div>

                    <!-- Пример результатов -->
                    <div class="result-item">
                        <div class="id">1</div>
                        <div class="player">John Doe</div>
                        <div class="player">Jane Smith</div>
                        <div class="winner">John Doe</div>
                    </div>
                    <div class="result-item">
                        <div class="id">2</div>
                        <div class="player">Alice Johnson</div>
                        <div class="player">Bob Lee</div>
                        <div class="winner">Alice Johnson</div>
                    </div>
                </div>

                <!-- Пагинация -->
                <div class="pagination">
                    <a href="?page=prev" class="pagination-link prev">Prev</a>
                    <span class="pagination-info">Page 1 of 10</span> <!-- Текущая страница и общее количество страниц -->
                    <a href="?page=next" class="pagination-link next">Next</a>
                </div>
            </div>
        </section>
    </main>

    <script>
        function clearSearch() {
            document.querySelector('.search-input').value = ''; // Очищаем поле поиска
            document.querySelector('form').submit(); // Отправляем форму для обновления страницы
        }
    </script>
</body>
</html>
