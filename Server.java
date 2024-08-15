import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server
{
    public static void main(String[] args) 
    {
        try {
            ServerSocket server = new ServerSocket(6010);
            System.out.println("[SERVER]: We are online!");

            Socket connection = server.accept();  // Corrected line

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));  // Corrected line

            String line = "";

            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println("[SERVER RECEIVED]: " + line.toUpperCase());
                    if (line.equals("")) {
                        System.out.println("<END>");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("[SERVER ERROR]: Connection reset. The client may have disconnected abruptly.");
            }

            reader.close();
            connection.close();  // It's also good practice to close the connection.
            server.close();  // Don't forget to close the server socket as well.
            System.out.println("[SERVER OFFLINE!]");
        } catch (IOException e) {
            System.out.println("[SERVER ERROR]: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
