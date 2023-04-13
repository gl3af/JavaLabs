import java.awt.*;

public class Rectangle extends Figure {
    int width;
    int height;

    public Rectangle(Frame frame, Color color, int id, int xSpeed, int ySpeed, int width, int height) {
        super(frame, color, id, xSpeed, ySpeed);
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        while (true) {
            if (x >= frame.getWidth() - width - xSpeed) toRight = false; // Правая граница
            if (x <= 0) toRight = true; // Левая граница
            if (y >= frame.getHeight() - height - ySpeed) toBottom = false; // Нижняя граница
            if (y <= 28 + ySpeed) toBottom = true; // Верхняя граница
            if (toRight) x += modifier * xSpeed;
            else x -= modifier * xSpeed;
            if (toBottom) y += modifier * ySpeed;
            else y -= modifier * ySpeed;
            setChanged();
            notifyObservers(this);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawString(String.valueOf(id), x + width / 2, y + height / 2);
    }
}
