import java.util.Observable;

public class ConsoleWriter extends Observable {
    Logger logger;

    public void writeToConsole(String message) {
        System.out.println(message);
        if (logger != null) {
            logger.log(message);
        }
        setChanged();
        notifyObservers("Обращение к потоку вывода на консоль");
    }
    public void addLogger(Logger logger) {
        this.logger = logger;
    }

}
