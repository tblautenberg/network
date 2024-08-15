import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


class Client
{
    public static void main(String[] args) throws UnknownHostException, IOException 
    {
        Socket connection = new Socket("127.0.0.1", 6010); // Server IP og portnummer
            
        System.out.println("[CLIENT]: Connection established");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

        sendLine(writer, "Hello from the client!");
        sendLine(writer, "I really hope this works :-)");
        sendLine(writer, "");

    }

    // Metode så vi kan sende text til vores server

    private static void sendLine( BufferedWriter writer, String line ) throws IOException
    {
        System.out.println("[Client] sending line: " + line );
    
        writer.write(line); // Sender en string til vores bufferedWriter
        writer.newLine(); // Forcer et linjeskift så vores writer ved at linjen er færdig
        writer.flush(); // Flusher writeren, så vi ved at data sendes efter hver linje
    
    }
}

