/**
 * UDP Client class
 * It first creates a socket, send a one byte packet
 * to the server, and waits for server to reply back. 
 * The client prints out the ACK if succeeds or times out after 5 seconds
 * 
 */
import java.io.*; 
import java.net.*; 
  
class UDPClient { 
    
    public static final int PKT_SIZE = 1;
    public static void main(String args[]) throws Exception 
    { 
     try {
   
      byte[] sendData = new byte[PKT_SIZE]; 
      byte[] receiveData = new byte[PKT_SIZE]; 
      
      //Open a client socket
      DatagramSocket clientSocket = new DatagramSocket(); 
  
      InetAddress IPAddress = InetAddress.getByName("127.0.0.1"); 
      System.out.println ("Connecting to " + IPAddress + " via UDP port 5555");
      
      //Create a one byte message
      byte[] message = new byte[1];
      sendData = message;
      System.out.println ("Sending data of " + sendData.length + 
                          " byte(s) to server.");
     
      //Create send packet
      DatagramPacket sendPacket = 
         new DatagramPacket(sendData, sendData.length, IPAddress, 5555); 
  
      //Send a datagram packet from clientSocket
      clientSocket.send(sendPacket); 
      
      //Create receive packet
      DatagramPacket receivePacket = 
         new DatagramPacket(receiveData, receiveData.length); 
  
      System.out.println ("Waiting for returned packet");
      // Set 5 second time out
      clientSocket.setSoTimeout(5000);

      try {
           clientSocket.receive(receivePacket); 
  
           InetAddress returnIPAddress = receivePacket.getAddress();
     
           int port = receivePacket.getPort();

           System.out.println(receivePacket.getLength() + "byte received");

          }
      catch (SocketTimeoutException ex)
          {
           System.out.println ("Connection timeout.");
      }
      //Close socket
      clientSocket.close(); 
     }
   catch (UnknownHostException ex) { 
     System.err.println(ex);
    }
   catch (IOException ex) {
     System.err.println(ex);
    }
  } 
} 