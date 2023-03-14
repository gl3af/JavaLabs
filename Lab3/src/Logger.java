import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {
    private String filename;
    public Logger(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()){
                file.createNewFile();
            }
            this.filename = filename;
        } catch (IOException e) {
            System.out.println("Ошибка создания файла!");
        }
    }
    public void log(String message) {
        try {
            message += "\n";
            Files.write(Paths.get(filename), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }
    }
}
