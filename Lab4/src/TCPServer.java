import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.*;

import static java.lang.String.valueOf;

public class TCPServer {
    public int port;
    private Logger logger;
    private ServerSocket serverSocket;
    public TCPServer(int port, Logger logger){
        try {
            this.port = port;
            serverSocket = new ServerSocket(port);
            this.logger = logger;
        } catch(IOException e){
            System.err.println("Не удаётся открыть сокет для сервера: " + e.toString());
        }
    }
    public void go(){
        class Listener implements Runnable {
            private final Socket socket;
            public Listener(Socket socket) {
                this.socket = socket;
            }
            public void run() {
                try {
                    System.out.println("Пришел хозяин");
                    var expression = SocketReader.read(socket);
                    logger.log(expression);
                    var result = Calculator.eval(expression);
                    SocketWriter.write(socket, valueOf(result));
                } catch (Exception e) {
                    System.err.println("Исключение: " + e);
                    try {
                        SocketWriter.write(socket, "Неверное выражение");
                    } catch (IOException ex) {
                        System.err.println("Исключение: " + ex);
                    }
                }
            }
        }
        System.out.println("Негр сгенерирован...");
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                Listener listener = new Listener(socket);
                Thread thread = new Thread(listener);
                thread.start();
            } catch(IOException e) {
                System.err.println("Исключение: " + e.toString());
            }
        }
    }
}

