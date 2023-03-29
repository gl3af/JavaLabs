import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
public class SocketReader {
    public static String read(Socket socket) throws IOException {
        final int dataLength = 20;
        char[] data = new char[dataLength];
        StringBuffer buffer = new StringBuffer();
        InputStream in = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);

        int count = reader.read(data, 0, dataLength);
        buffer.append(data, 0, count);
        Thread.yield();

        return buffer.toString();
    }
}
