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
    <title>Стартовая страница</title>
</head>
<body>
<jsp:useBean id="mybean" scope="session" class="com.example.beans_jsp.MainBean"/>
<main>
    <h1>Баранов Никита Романович 1 вариант 101111</h1>
    <h1>Задание: </h1>
    <p>
        1. В зависимости от варианта выполняется разработка web-приложения. Пример
        приложения похожего на то, которое нужно разработать, см. выше. Показать работу
        приведённого выше примера.
    </p>
    <p>
        2. В целом, нужно создать web-приложение в NetBeans IDE, которое должно состоять
        из трёх JSP-страниц – стартовой, главной и финишной, а также при необходимости
        использовать для обмена данными между страницами Bean-компоненту.
    </p>
    <p>
        3. На «Главной странице» инициировать вычисление функции из задания первой
        лабораторной работы.
    </p>
    <p>
        4. Параметры необходимые для работы функции реализуемой на «Главной странице»
        задавать на ней в текстовом поле, а результат её работы показывать на «Финишной
        странице».
    </p>
    <p>
        5. Программный код вычисляемой функции разместить: 1 – в классе Bean-компоненты.
    </p>
    <p>
        6. Заголовки страниц должны иметь следующий вид: 0 – «Стартовая страница», «Главная страница» и «Финишная
        страница».
    </p>
    <p>
        7. Формат «Стартовой страницы»: 1 – содержит текст задания на лабораторную работу,
        группа студента и кнопку для перехода на «Главную страницу».
    </p>
    <p>
        8. Организовать вывод результатов работы функции на «Финишной странице»: 1 – результаты должны быть каким-то
        образом размещены в видимой таблице, в таблице допускается произвольное число столбцов и строк.
    </p>
    <p>
        9. При повторном переходе на «Главную страницу», например при нажатии кнопки
        «Возврат» на «Финишной странице», на «Главной странице» должно отображаться: 3 –
        общее число переходов (обновлений) страниц Web-приложения.
    </p>
    <button>
        <a href="MainPage.jsp">Главная страница</a>
    </button>
</main>
<%
    mybean.setRedirectsCount(mybean.getRedirectsCount() + 1);
%>
</body>
</html>
