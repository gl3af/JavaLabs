<%--
  Created by IntelliJ IDEA.
  User: Баранов Никита
  Date: 03.05.2023
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<jsp:useBean id="mybean" scope="session" class="com.example.beans_jsp.MainBean"/>
<%
    try {
        String data = request.getParameter("data");
        mybean.executeTask(data);
    } catch (Exception ignored) {
        mybean.setOddSum(-1);
        mybean.setEvenSum(-1);
    }
%>
<main>
    <h1>Данные: </h1>
    <form name="inputForm" action="MainPage.jsp">
        <label>
            <input type="text" name="data"/>
        </label>
        <input type="submit" value="Готово" name="entered"/>
    </form>
    <form name="redirectForm" action="FinishPage.jsp">
        <input type="submit" value="Результат" name="redirect"/>
    </form>
</main>
<h3>Число переходов: ${mybean.redirectsCount}</h3>
<%
    mybean.setRedirectsCount(mybean.getRedirectsCount() + 1);
%>
</body>
</html>
