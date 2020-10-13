package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            // Connect to the server
            Socket socket = new Socket(server, portNumber);

            // Get the input stream
            InputStream input = socket.getInputStream();

            // Get the output stream
            OutputStream output = socket.getOutputStream();

            int byteInput;

            while ((byteInput = System.in.read()) != -1) {
                output.write(byteInput);
                System.out.write(input.read());
                output.flush();
                System.out.flush();
            }

            input.close(); // Close the input
            socket.close(); // Close the socket when we're done reading from it

            // Provide some minimal error handling.
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}