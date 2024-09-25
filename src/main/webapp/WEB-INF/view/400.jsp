<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>400 - Bad Request</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <main>
        <h1>400 - Bad Request</h1>
        <div class="error-card">
            <p>${error}</p>
        </div>
    </main>
</body>
</html>
