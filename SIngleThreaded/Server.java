import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server{

    public void run() throws IOException,UnknownHostException{
        int port=8010;
        // no IP address is specified, so it will listen on all available IP addresses
        ServerSocket socket=new ServerSocket(port);
        // socket will end in 20 seconds if no connection is made
        socket.setSoTimeout(10000);

        while(true){
            System.out.println("Server is listening on port "+port+" and waiting for a connection");
            // new socket will be created for each connection
            Socket acceptedConnection=socket.accept();
            System.out.println("Server is connected to "+acceptedConnection.getRemoteSocketAddress());
            PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream(),true);
            BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            toClient.println("Hello Client! Server this side");
            String line=fromClient.readLine();
            System.out.println("Message from Client: " + line);
            toClient.close();
            fromClient.close();
            acceptedConnection.close();
        }

    }

    public static void main(String[] args){
        Server server=new Server();
        try{
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
} 
        
        