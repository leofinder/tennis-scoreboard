<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <%@include file="header.jsp"%>
    <main>
        <section>
            <h1>404 - Page Not Found</h1>
            <div class="error-card">
                <p>Sorry, the page you're looking for doesn't exist.</p>
                <p>Check the URL or return to the home page.</p>
            </div>
        </section>
    </main>
</body>
</html>
