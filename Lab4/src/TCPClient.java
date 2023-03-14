import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient implements Runnable {
    String expression;
    String host;
    int port;
    private Logger logger;
    public TCPClient(String host, int port, Logger logger, String expression) {
        this.port = port;
        this.host = host;
        this.logger = logger;
        this.expression = expression;
    }

    public void run() {
        try {
            Socket socket = new Socket(host, port);

            SocketWriter.write(socket, expression);
            socket.shutdownOutput();
            Thread.sleep(200);

            var result = SocketReader.read(socket);
            logger.log(result);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.err.println("Исключение: " + e.toString());
        }
    }
}
