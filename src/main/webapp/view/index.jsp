<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Дошка заходів</title>
</head>
<body>

    <h1>Дошка оголошень заходів</h1>

    <h3>Створити новий захід</h3>
    <form action="${pageContext.request.contextPath}/events" method="POST">
        <label>Назва заходу:</label>
        <input type="text" name="title" required><br><br>

        <label>Дата заходу:</label>
        <input type="date" name="eventDate" required><br><br>

        <label>Кількість місць:</label>
        <input type="number" name="maxSeats" min="1" required><br><br>

        <button type="submit">Додати захід</button>
    </form>

    <hr>

    <h3>Список усіх доступних заходів</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Назва</th>
                <th>Дата</th>
                <th>Загалом місць</th>
                <th>Вільних місць</th>
                <th>Дії</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="event" items="${eventsList}">
                <tr>
                    <td>${event.title}</td>
                    <td>${event.eventDate}</td>
                    <td>${event.maxSeats}</td>
                    <td>${eventService.avaliableSeats(event.id)}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/event?id=${event.id}">Деталі та реєстрація</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty eventsList}">
                <tr>
                    <td colspan="5">Заходів поки немає</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>