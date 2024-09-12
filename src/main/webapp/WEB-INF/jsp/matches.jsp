<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches. Tennis Scoreboard</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <main>
        <section>
            <h1>Matches</h1>
            <div class="search-pagination-container">
                <!-- Форма поиска -->
                <form action="/matches" method="GET" class="search-form">
                    <input type="text" name="query" placeholder="Search..." class="search-input" value="">
                    <button type="submit" class="search-button">Search</button>
                </form>

                <!-- Результаты поиска -->
                <div class="search-results">
                    <ul class="results-list">
                        <li>Result 1</li>
                        <li>Result 2</li>
                        <li>Result 3</li>
                        <!-- ... -->
                    </ul>
                </div>

                <!-- Пагинация -->
                <div class="pagination">
                    <a href="?query=your-query&page=1" class="pagination-link">1</a>
                    <a href="?query=your-query&page=2" class="pagination-link">2</a>
                    <a href="?query=your-query&page=3" class="pagination-link">3</a>
                    <span class="pagination-dots">...</span>
                    <a href="?query=your-query&page=10" class="pagination-link">10</a>
                    <a href="?query=your-query&page=next" class="pagination-link">Next</a>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
