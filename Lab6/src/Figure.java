import java.awt.*;
import java.util.Observable;

public abstract class Figure extends Observable implements Runnable {
    final int SLEEP_TIME = 200;
    int id;
    Thread thread;
    Frame frame;
    boolean toRight;
    boolean toBottom;
    int speed;
    int x;
    int y;
    double angle;
    Color color;
    protected Figure(Frame frame, Color color, int id, int speed) {
        angle = Math.PI / 2 * Math.random();
        toRight = true;
        toBottom = true;
        x = 0;
        y = 30;
        this.id = id;
        this.frame = frame;
        this.speed = speed;
        this.color = color;
        thread = new Thread(this);
        thread.start();
    }

    abstract public void run();

    abstract public void draw(Graphics g);
}
