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
            System.out.println("Welcome to the UPPERSCASE SERVER!");
            System.out.println("[SERVER]: We are online!");
            System.err.println("[SERVER]: Waiting for a client to connect...");

            Socket connection = server.accept();  // Afventer klienten tilslutter

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter writer = new PrintWriter(connection.getOutputStream(), true); 

            String line; // Placeholder string

            try 
            {
                while ((line = reader.readLine()) != null) // Loop der køre så længe der er data fra klienten
                {
                    System.out.println("[SERVER RECEIVED FROM CLIENT]: " + line);
                    
                    // Konventere den modtagede linje til UPPERCASE
                    String response = line.toUpperCase();

                    // Sender den tilbage til klienten
                    writer.println(response);

                    if (line.equalsIgnoreCase("<END>"))  // Når <END> rammes, stopper loopet
                    {
                        break;
                    }
                }
            }
            catch (IOException e) 
            {
                System.out.println("[SERVER ERROR]: Server stopped working.");
            }

            reader.close();
            writer.close();
            connection.close();
            server.close();

            System.out.println("[SERVER MESSAGE]: <END> tag received from client - shutting off");
        } 
        catch (IOException e) 
        {
            System.out.println("[SERVER ERROR]: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


/* NOTER TIL OPGAVEN

Ved at bruge PrintWriter istedet for BufferedWriter undgår vi at anvende

writer.write(response);
writer.newLine();
writer.flush(); 

Ved at sætte autoFlush = true, sender den efter hvert println teksten til streamen - bruges også ved klient klassen.

*/