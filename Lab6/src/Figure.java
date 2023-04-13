import java.awt.*;
import java.util.Observable;

public abstract class Figure extends Observable implements Runnable {
    int id;
    int modifier = 1;
    Thread thread;
    Frame frame;
    boolean toRight;
    boolean toBottom;
    final int xSpeed;
    final int ySpeed;
    int x;
    int y;
    Color color;
    protected Figure(Frame frame, Color color, int id, int xSpeed, int ySpeed) {
        toRight = true;
        toBottom = true;
        x = 0;
        y = 30;
        this.id = id;
        this.frame = frame;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = color;
        Test.count++;
        thread = new Thread(this, Test.count + ":");
        thread.start();
    }

    abstract public void run();

    abstract public void draw(Graphics g);
}
