import java.util.Observable;
import java.util.Observer;

public class ConsoleObserver implements Observer {
    Logger logger;
    public ConsoleObserver(Logger logger) {
        this.logger = logger;
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String)arg);
        logger.log((String)arg);
    }
}
