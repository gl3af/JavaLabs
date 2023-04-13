import java.awt.event.*;

public class MyWindowAdapter extends WindowAdapter {
    public void windowClosing (WindowEvent wE) { System.exit (0); }
}

