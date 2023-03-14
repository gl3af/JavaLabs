import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            var data = XMLReader.getData();
            String host = data.get(0);
            int port = Integer.parseInt(data.get(1));

            System.out.print("Введите файл журнала клиента: ");
            Scanner scanner = new Scanner(System.in);
            String clientLogPath = scanner.nextLine();

            Logger clientLogger = new Logger(clientLogPath);

            System.out.print("Выражение: ");
            var expression = scanner.nextLine();

            TCPClient client = new TCPClient(host, port, clientLogger, expression);
            Thread thread = new Thread(client);
            thread.start();
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла настроек");
        }

    }
}
