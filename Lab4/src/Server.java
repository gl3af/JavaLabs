import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String port = args[0];

            System.out.print("Введите файл журнала сервера: ");
            String serverLogPath = scanner.nextLine();

            Logger serverLogger = new Logger(serverLogPath);

            TCPServer tcpServer = new TCPServer(Integer.parseInt(port), serverLogger);
            tcpServer.go();

        } catch (Exception e) {
            System.out.println("Ошибка чтения файла настроек");
        }

    }
}
