import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server
{
    public static void main(String[] args) 
    {
        try 
        {
            ServerSocket server = new ServerSocket(6010);
            System.out.println("[SERVER]: We are online!");
            System.err.println("[SERVER]: Waiting for a client to connect...");



            Socket connection = server.accept();  // Venter på at en klient forbinder til serveren

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 

            String line; // Deklarerer en string variabel, som skal bruges til at læse linjer fra klienten

            try 
            {
                while ((line = reader.readLine()) != null) {
                    System.out.println("[SERVER RECEIVED]: " + line.toUpperCase());
                    if (line.equals("")) {
                        System.out.println("<END>");
                        break;
                    }
                }
            }
             catch (IOException e) 
            {
                System.out.println("[SERVER ERROR]: Connection reset. The client may have disconnected abruptly.");
            }

            reader.close(); // Lukker reader
            connection.close();  // Lukker connection
            server.close(); // Lukker serveren
            System.out.println("[SERVER OFFLINE!]");
        } 
        catch (IOException e) 
        {
            System.out.println("[SERVER ERROR]: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
