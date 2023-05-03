package com.example.lab7;

import java.io.*;
import java.util.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/task")
public class MyServlet extends HttpServlet {
    static long counter;
    static int maxFontSize;
    static int fontSize;

    static List<String> itemsList;

    public MyServlet() {
        counter = 0;
        fontSize = 9;
        maxFontSize = 24;
        itemsList = new ArrayList<>();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='ru'><head>");
            out.println("<title>Баранов Н. Р. 4310</title>");
            out.println("</head><body>");
            out.println("<h1>URL: " + request.getServletPath() + "</h1>");
            String fio = request.getParameter("fio");
            String groupNumber = request.getParameter("group");
            if (fio != null && groupNumber != null)
                out.println(fio + " " + groupNumber);

            counter++;
            if (fontSize < maxFontSize) {
                fontSize++;
            } else {
                out.println("<h3>Достигнут максимальный размер шрифта</h3>");
            }

            out.println("<h3> Количество обновлений: " + counter + "</h3>");
            out.println("<h3> Размер шрифта: " + fontSize + "</h3>");

            String input = request.getParameter("input");
            String font = request.getParameter("font");

            int enteredFontSize = fontSize;
            if (font != null) {
                out.println("<h3> Введеный размер шрифта:" + font + "</h3>");
                try {
                    enteredFontSize = Integer.parseInt(font);
                } catch (NumberFormatException e) {
                    out.println("<h3 style='color: red'>Неверный формат размера шрифта!</h3>");
                }
            }
            int fontValue = font == null ? fontSize : enteredFontSize;
            String tableHead = String.format("<table style='font-size: %dpx'>", fontValue);

            if (input != null) {
                out.println("<h3> Введеные данные:" + input + "</h3>");
                String[] array = input.split(" ");
                int odd = 0;
                int even = 0;
                for (String value: array) {
                    int x = Integer.parseInt(value);
                    if (x % 2 == 0)
                        even += x;
                    else
                        odd += x;
                }
                String item = input + " -> " + "Н: " + odd + " Ч: " + even;
                itemsList.add(item);
            }

            out.println(tableHead);
            for (String item: itemsList) {
                out.println(String.format("<tr><td>%s</td></tr>", item));
            }
            out.println("</table></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
