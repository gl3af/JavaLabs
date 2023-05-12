import java.awt.*;

public class Triangle extends Figure {
    int side;

    public Triangle(Frame frame, Color color, int id, int speed, int side) {
        super(frame, color, id, speed);
        this.side = side;
    }

    @Override
    public void run() {
        while (true) {
            if (x >= frame.getWidth() - side - speed * Math.cos(angle)) toRight = false; // Правая граница
            if (x <= 0) toRight = true; // Левая граница
            if (y >= frame.getHeight() - side - speed * Math.sin(angle)) toBottom = false; // Нижняя граница
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
        g.drawPolygon(
                new int[] {x, x, x + side},
                new int[] {y, y + side, y + side},
                3
        );
        g.setColor(Color.black);
        g.drawString(String.valueOf(id), x + side / 2, y + side / 2);
    }
}
