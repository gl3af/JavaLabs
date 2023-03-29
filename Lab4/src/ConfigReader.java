import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {
    public static List<String> getData() throws Exception {
        List<String> data = new ArrayList<>();

        String filename = "config.txt";
        var fileInfo = Files.readAllLines(Paths.get(filename));
        String host = fileInfo.get(0);
        String port = fileInfo.get(1);

        data.add(host);
        data.add(port);

        return data;
    }
}
