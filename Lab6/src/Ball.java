import java.awt.*;

class Ball extends Figure {
    private final int radius;

    public Ball(Frame frame, Color color, int id, int xSpeed, int ySpeed, int radius) {
        super(frame, color, id, xSpeed, ySpeed);
        this.radius = radius;
    }

    @Override
    public void run() {
        while (true) {
            if (x >= frame.getWidth() - radius - xSpeed) toRight = false; // Правая граница
            if (x <= 0) toRight = true; // Левая граница
            if (y >= frame.getHeight() - radius - ySpeed) toBottom = false; // Нижняя граница
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
        g.drawOval(x, y, radius, radius);
        g.setColor(Color.black);
        g.drawString(String.valueOf(id), x + radius / 2, y + radius / 2);
    }
}