import java.awt.*;

public class Square extends Figure {
    int side;

    public Square(Frame frame, Color color, int id, int xSpeed, int ySpeed, int side) {
        super(frame, color, id, xSpeed, ySpeed);
        this.side = side;
    }

    @Override
    public void run() {
        while (true) {
            if (x >= frame.getWidth() - side - xSpeed) toRight = false; // Правая граница
            if (x <= 0) toRight = true; // Левая граница
            if (y >= frame.getHeight() - side - ySpeed) toBottom = false; // Нижняя граница
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
        g.drawRect(x, y, side, side);
        g.setColor(Color.black);
        g.drawString(String.valueOf(id), x + side / 2, y + side / 2);
    }
}
