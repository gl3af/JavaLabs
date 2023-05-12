import java.awt.*;

class Ball extends Figure {
    private final int radius;

    public Ball(Frame frame, Color color, int id, int speed, int radius) {
        super(frame, color, id, speed);
        this.radius = radius;
    }

    @Override
    public void run() {
        while (true) {
            if (x >= frame.getWidth() - radius - speed * Math.cos(angle)) toRight = false; // Правая граница
            if (x <= 0) toRight = true; // Левая граница
            if (y >= frame.getHeight() - radius - speed * Math.sin(angle)) toBottom = false; // Нижняя граница
            if (y <= 28 + Math.sin(angle)) toBottom = true; // Верхняя граница
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
        g.drawOval(x, y, radius, radius);
        g.setColor(Color.black);
        g.drawString(String.valueOf(id), x + radius / 2, y + radius / 2);
    }
}