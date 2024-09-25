<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches. Tennis Scoreboard</title>
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
                    <input type="text" name="filter_by_player_name" placeholder="Search..." class="search-input" value="${requestScope.playerName}">
                    <button type="submit" class="search-button">Search</button>
                    <button type="button" class="clear-button" onclick="clearSearch()">х</button>
                </form>

                <div class="search-results">
                    <div class="result-item header">
                        <div class="id">id</div>
                        <div class="player">Player 1</div>
                        <div class="player">Player 2</div>
                        <div class="winner">Winner</div>
                    </div>

                    <c:forEach var="match" items="${requestScope.matches}">
                    <div class="result-item">
                        <div class="id">${match.id}</div>
                        <div class="player">${match.playerOne.name}</div>
                        <div class="player">${match.playerTwo.name}</div>
                        <div class="winner">${match.winner.name}</div>
                    </div>
                    </c:forEach>
                </div>

                <div class="pagination">
                    <c:choose>
                        <c:when test="${requestScope.page > 1}">
                            <a href="?page=${requestScope.page - 1}&filter_by_player_name=${requestScope.playerName}" class="pagination-link prev">Prev</a>
                        </c:when>
                        <c:otherwise>
                            <span class="pagination-link hidden">Prev</span>
                        </c:otherwise>
                    </c:choose>
                    <span class="pagination-info">Page ${requestScope.page} of ${requestScope.pageCount} </span>
                    <c:choose>
                        <c:when test="${requestScope.page < requestScope.pageCount}">
                            <a href="?page=${requestScope.page + 1}&filter_by_player_name=${requestScope.playerName}"
                               class="pagination-link next">Next</a>
                        </c:when>
                        <c:otherwise>
                            <span class="pagination-link hidden">Next</span>
                        </c:otherwise>
                    </c:choose>
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
