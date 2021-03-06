
package tcpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        while(true){
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            //Enter data using BufferReader 
            BufferedReader reader =   new BufferedReader(new InputStreamReader(System.in)); 
            // Reading data using readLine 
            String command = null; 
            try {
                command = reader.readLine();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
  
            // Printing the read line 
            System.out.println(command);  
            System.out.println("Sending request to Socket Server");
            
            oos.writeObject(command);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(10000);
        }
    }
    
}

