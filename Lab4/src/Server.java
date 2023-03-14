import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            var data = XMLReader.getData();
            int port = Integer.parseInt(data.get(1));

            System.out.print("Введите файл журнала сервера: ");
            Scanner scanner = new Scanner(System.in);
            String serverLogPath = scanner.nextLine();

            Logger serverLogger = new Logger(serverLogPath);

            TCPServer tcpServer = new TCPServer(port, serverLogger);
            tcpServer.go();

        } catch (Exception e) {
            System.out.println("Ошибка чтения файла настроек");
        }

    }
}
