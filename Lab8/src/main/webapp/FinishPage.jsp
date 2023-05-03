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
    <title>Финишная страница</title>
</head>
<body>
<jsp:useBean id="mybean" scope="session" class="com.example.beans_jsp.MainBean"/>
<main>
    <h1>Результат</h1>
    <table border="1">
        <tr>
            <td>
                <b>Сумма четных</b>
            </td>
            <td>
                <b>Сумма нечетных</b>
            </td>
        </tr>
        <tr>
            <td>${mybean.evenSum}</td>
            <td>${mybean.oddSum}</td>
        </tr>
    </table>
    <button>
        <a href="MainPage.jsp">Возврат</a>
    </button>
</main>
<%
    mybean.setRedirectsCount(mybean.getRedirectsCount() + 1);
%>
</body>
</html>
