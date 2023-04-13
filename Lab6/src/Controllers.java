import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Controllers {
    public static void addNewFigure(
            DemonstrationFrame parent,
            Frame frame,
            Label errorLabel,
            JColorChooser colorChooser,
            List<Figure> figuresList,
            String type,
            String idStr,
            String xSpeedStr,
            String ySpeedStr) {
        if (isValidStrings(new String[]{type, xSpeedStr, ySpeedStr, idStr})) {
            errorLabel.setText("Данные не введены!");
            return;
        }

        Color color = colorChooser.getColor();
        int xSpeed, ySpeed, id;

        try {
            id = Integer.parseInt(idStr);
            xSpeed = Integer.parseInt(xSpeedStr);
            ySpeed = Integer.parseInt(ySpeedStr);
        } catch (NumberFormatException e) {
            errorLabel.setText("Введены не числа!");
            return;
        }

        if (figureWithIdExists(figuresList, id)) {
            errorLabel.setText("Фигура с таким номером существует!");
            return;
        }

        if (type.equalsIgnoreCase("круг")) {
            Ball ball = new Ball(frame, color, id, xSpeed, ySpeed, 20);
            figuresList.add(ball);
            ball.addObserver(parent);
        } else if (type.equalsIgnoreCase("овал")) {
            Oval oval = new Oval(frame, color, id, xSpeed, ySpeed, 20, 10);
            figuresList.add(oval);
            oval.addObserver(parent);
        } else if (type.equalsIgnoreCase("квадрат")) {
            Square square = new Square(frame, color, id, xSpeed, ySpeed, 20);
            figuresList.add(square);
            square.addObserver(parent);
        } else if (type.equalsIgnoreCase("прямоугольник")) {
            Rectangle rect = new Rectangle(frame, color, id, xSpeed, ySpeed, 20, 10);
            figuresList.add(rect);
            rect.addObserver(parent);
        } else if (type.equalsIgnoreCase("треугольник")) {
            Triangle triangle = new Triangle(frame, color, id, xSpeed, ySpeed, 20);
            figuresList.add(triangle);
            triangle.addObserver(parent);
        } else errorLabel.setText("Введенный тип отсутствует!");
    }

    public static void updateId(
            Label errorLabel,
            List<Figure> figuresList,
            String oldIdStr,
            String newIdStr) {
        if (isValidStrings(new String[]{oldIdStr, newIdStr})) {
            errorLabel.setText("Данные не введены!");
            return;
        }

        int newId, oldId;
        try {
            newId = Integer.parseInt(newIdStr);
            oldId = Integer.parseInt(oldIdStr);
        } catch (NumberFormatException e) {
            errorLabel.setText("Введены не числа!");
            return;
        }

        if (!figureWithIdExists(figuresList, oldId)) {
            errorLabel.setText("Фигура с таким номером отсутствует!");
            return;
        }

        if (figureWithIdExists(figuresList, newId)) {
            errorLabel.setText("Фигура с таким номером уже есть!");
            return;
        }

        for (Figure figure : figuresList) {
            if (figure.id == oldId) {
                figure.id = newId;
                break;
            }
        }
    }

    public static void updateSpeed(
            Label errorLabel,
            List<Figure> figuresList,
            Choice speedModifierChoice,
            String modifiedIdStr) {
        if (isValidStrings(new String[]{modifiedIdStr})) {
            errorLabel.setText("Данные не введены!");
            return;
        }

        int modifiedId;
        try {
            modifiedId = Integer.parseInt(modifiedIdStr);
        } catch (NumberFormatException e) {
            errorLabel.setText("Введены не числа!");
            return;
        }

        if (!figureWithIdExists(figuresList, modifiedId)) {
            errorLabel.setText("Фигура с таким номером отсутствует!");
            return;
        }

        int modifier = 0;
        switch (speedModifierChoice.getSelectedIndex()) {
            case 0 -> modifier = 1;
            case 1 -> modifier = 2;
            case 2 -> modifier = 3;
            case 3 -> modifier = 4;
            case 4 -> modifier = 5;
        }

        for (Figure figure: figuresList)
            if (figure.id == modifiedId) {
                figure.modifier = modifier;
                break;
            }
    }

    private static boolean isValidStrings(String[] list) {
        for (String str : list)
            if (str.isEmpty())
                return true;

        return false;
    }

    private static boolean figureWithIdExists(List<Figure> figuresList, int id) {
        for (Figure figure : figuresList)
            if (figure.id == id)
                return true;

        return false;
    }
}
