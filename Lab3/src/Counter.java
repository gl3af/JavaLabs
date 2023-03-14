import java.util.Observable;

public class Counter extends Observable {
    String expectedNumber = "5";
    public int oddNumbersSum(String list) {
        int result = 0;
        var numbers = list.split(" ");
        for (String x: numbers) {
            setChanged();
            notifyObservers("Обращение к массиву!");
            if (x.equals(expectedNumber)) {
                setChanged();
                notifyObservers("Равенство заданному значению!");
            }
            if (Integer.parseInt(x) % 2 != 0) {
                result += Integer.parseInt(x);
            }
        }

        return result;
    }
    public int evenNumbersSum(String list) {
        int result = 0;
        var numbers = list.split(" ");
        for (String x: numbers) {
            setChanged();
            notifyObservers("Обращение к массиву!");
            if (x.equals(expectedNumber)) {
                setChanged();
                notifyObservers("Равенство заданному значению!");
            }
            if (Integer.parseInt(x) % 2 == 0) {
                result += Integer.parseInt(x);
            }
        }

        return result;
    }
}
