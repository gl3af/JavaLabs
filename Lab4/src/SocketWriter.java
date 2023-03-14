import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketWriter {
    public static void write(Socket socket, String message) throws IOException {
        OutputStream out = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        out.write(message.getBytes());
        out.flush();
    }
}
