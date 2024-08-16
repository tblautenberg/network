import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class Client
{
    public static void main(String[] args) throws UnknownHostException, IOException 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the IP address of the server: ");
        String ip = scanner.nextLine();

        System.out.println("Enter the port number of the server: ");
        int port = scanner.nextInt();
        
        scanner.nextLine(); 

        try(Socket connection = new Socket(ip, port))
        {
            System.out.println("[CLIENT]: Connection established");

            PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);  // Anvender PrintWriter over bufferedWriter
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String message;
            
            while (true) // Køre loop til vi rammer <END> tag
            {
                System.out.println("**********************************************");
                System.out.println("Enter a message to send to the server (type '<END>' to quit): ");
                System.out.println("**********************************************");
                message = scanner.nextLine();

                communicateWithServer(writer, reader, message);

                if (message.equalsIgnoreCase("<END>")) 
                {
                    System.out.println("<END> tag sent - shutting client off");
                    break;
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println("Wrong IP or port number. Server might also be offline? Error code from Java: " + e);
        }
    }

    // Metode til at kommunikere med serveren
    private static void communicateWithServer(PrintWriter writer, BufferedReader reader, String line) throws IOException
    {
        System.out.println("[CLIENT]: Sending line: " + line);

        writer.println(line);

        // Læser dataen der kommer retur fra serveren og skriver den ud

        String response = reader.readLine();
        System.out.println("[SERVER RESPONSE]: " + response);
    }
}
