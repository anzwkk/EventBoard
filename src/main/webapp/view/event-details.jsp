<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Деталі заходу</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/events">Повернутися до списку</a>

    <c:if test="${empty selectedEvent}">
        <h2>Захід не знайдено</h2>
    </c:if>

    <c:if test="${not empty selectedEvent}">
        <h1>${selectedEvent.title}</h1>
        <p><strong>Дата проведення:</strong> ${selectedEvent.formattedDate}</p>
        <p><strong>Загалом місць:</strong> ${selectedEvent.maxSeats}</p>
        <p><strong>Залишилось місць:</strong> ${eventService.avaliableSeats(selectedEvent.id)}</p>

        <hr>

        <c:if test="${eventService.avaliableSeats(selectedEvent.id) > 0}">
            <h3>Зареєструватися на захід:</h3>
            <form action="${pageContext.request.contextPath}/event" method="POST">
                <input type="hidden" name="eventId" value="${selectedEvent.id}">

                <label>Ваше ім'я:</label>
                <input type="text" name="studentName" required><br><br>

                <label>Ваш Email:</label>
                <input type="email" name="studentEmail" required><br><br>

                <button type="submit">Зареєструватися</button>
            </form>
        </c:if>

        <c:if test="${eventService.avaliableSeats(selectedEvent.id) <= 0}">
            <h3 style="color: red;">Реєстрація не можлива, місць немає</h3>
        </c:if>

        <hr>

        <h3>Список зареєстрованих учасників:</h3>
        <c:if test="${empty participantsList}">
            <p>Ще немає зареєстрованих учасників</p>
        </c:if>

        <ul>
            <c:forEach items="${participantsList}" var="user">
                <li>${user.studentName} — ${user.studentEmail}</li>
            </c:forEach>
        </ul>
    </c:if>

</body>
</html>