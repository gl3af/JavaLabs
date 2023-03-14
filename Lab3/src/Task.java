import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
class Task {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleWriter cw = new ConsoleWriter();

        String message = "Введите название файла журнала: ";
        cw.writeToConsole(message);
        String logFilename = scanner.nextLine();

        Logger logger = new Logger(logFilename);
        logger.log(message);
        cw.addLogger(logger);
        ConsoleObserver consoleObserver = new ConsoleObserver(logger);
        cw.addObserver(consoleObserver);

        cw.writeToConsole("Введите название файла: ");
        String dataFilename = scanner.nextLine();
        File dataFile = new File(dataFilename);
        if (dataFile.exists()) {
            try {
                String fileData = Files.readAllLines(Paths.get(dataFilename)).get(0);

                Counter counter = new Counter();
                DataObserver dataObserver = new DataObserver(logger);
                counter.addObserver(dataObserver);

                int oddNumbersSum = counter.oddNumbersSum(fileData);
                int evenNumbersSum = counter.evenNumbersSum(fileData);

                cw.writeToConsole("Сумма четных: " + evenNumbersSum);
                cw.writeToConsole("Сумма нечетных: " + oddNumbersSum);
            } catch (IOException e) {
                cw.writeToConsole("Ошибка чтения!");
            }
        } else {
            cw.writeToConsole("Файл не найден!");
        }
    }
}