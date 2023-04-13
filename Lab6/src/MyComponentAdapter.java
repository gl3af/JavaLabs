import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyComponentAdapter extends ComponentAdapter {
    public Frame frame = new Frame();

    public void componentResized(ComponentEvent componentEvent) {
        if (componentEvent.getID() == 101) {
            Frame frame = (Frame) componentEvent.getSource();
            this.frame = frame;
        }
    }
}
