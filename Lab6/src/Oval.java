import java.awt.*;

public class Oval extends Figure {
    int width;

    int height;

    public Oval(Frame frame, Color color, int id, int speed, int width, int height) {
        super(frame, color, id, speed);
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        while (true) {
            if (x >= frame.getWidth() - width - speed * Math.cos(angle)) toRight = false; // Правая граница
            if (x <= 0) toRight = true; // Левая граница
            if (y >= frame.getHeight() - height - speed * Math.sin(angle)) toBottom = false; // Нижняя граница
            if (y <= 28 + speed * Math.sin(angle)) toBottom = true; // Верхняя граница
            if (toRight) x += speed * Math.cos(angle);
            else x -= speed * Math.cos(angle);
            if (toBottom) y += speed * Math.sin(angle);
            else y -= speed * Math.sin(angle);
            setChanged();
            notifyObservers(this);
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(x, y, width, height);
        g.setColor(Color.black);
        g.drawString(String.valueOf(id), x + width / 2, y + height / 2);
    }
}
