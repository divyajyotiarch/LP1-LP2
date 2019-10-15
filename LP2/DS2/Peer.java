import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Scanner;

class receiveThread implements Runnable
{
    int my_port;
    static HashMap hm = null;
    public receiveThread(int my_port){
        this.my_port = my_port;
        hm = new HashMap();
    }
    
    public void run() {
        // TODO Auto-generated method stub
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(my_port);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] receive = new byte[65535];
 
        DatagramPacket DpReceive = null;
        while (true)
        {
 
            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);
           
            // Step 3 : receive the data in byte buffer.
            try {
                ds.receive(DpReceive);
                int new_port = DpReceive.getPort();
                if (hm.containsKey(new_port)){
                    System.out.print(hm.get(new_port));
                }
                else
                {
                    hm.put(new_port, data(receive));
                    System.out.println(data(receive));
                }
                //System.out.print(new_port);
               
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
            System.out.println(":-" + data(receive));
 
            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("bye"))
            {
                System.out.println("Client sent bye.....EXITING");
                break;
            }
 
            // Clear the buffer after every message.
            receive = new byte[65535];
        }
    }
     public static StringBuilder data(byte[] a)
        {
            if (a == null)
                return null;
            StringBuilder ret = new StringBuilder();
            int i = 0;
            while (a[i] != 0)
            {
                ret.append((char) a[i]);
                i++;
            }
            return ret;
        }
   
}

public class Peer
{
   
    public static void main(String[] args) throws IOException
    {
        // Step 1 : Create a socket to listen at port 1234
        Thread t1 = new Thread(new receiveThread (1235));
        t1.start();
        int array[] = {1234,1235,1236,1237,1238};
        Scanner sc = new Scanner(System.in);
       
        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();
 
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;
 
        // loop while user not enters "bye"
        System.out.println("Enter your name for first time:");
        while (true)
        {
            String inp = sc.nextLine();
 
            // convert the String input into the byte array.
            buf = inp.getBytes();
 
            // Step 2 : Create the datagramPacket for sending
            // the data.
            for(int port : array)
            {
            DatagramPacket DpSend =
                  new DatagramPacket(buf, buf.length, ip, port);
 
                // Step 3 : invoke the send call to actually send
                // the data.
                ds.send(DpSend);
            }
            // break the loop if user enters "bye"
            if (inp.equals("bye"))
                break;
        }
       
    }
 
    // A utility method to convert the byte array
    // data into a string representation.
   
}
