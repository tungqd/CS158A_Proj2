/**
 * UDP Server class
 * It first creates a socket, then waits for a receivePacket.
 * When the server receives the packet, it returns a message to client
 */
import java.io.*; 
import java.net.*; 
  
class UDPServer { 
    
  //Set the size of message
  public static final int PKT_SIZE = 1;  
  public static void main(String args[]) throws Exception 
    {   
     try
     { 
         
      // Open a datagram socket via port 5555      
      DatagramSocket serverSocket = new DatagramSocket(5555); 
      byte[] receiveData = new byte[PKT_SIZE]; 
      byte[] sendData  = new byte[PKT_SIZE]; 
  
      while(true) 
        { 
  
          receiveData = new byte[1024]; 

          //Create a receivePacket
          DatagramPacket receivePacket = 
             new DatagramPacket(receiveData, receiveData.length); 

          System.out.println("Socket opened. Waiting for datagram packet...");
          
          //Receive packet
          serverSocket.receive(receivePacket); 
          
          //IP address, and port number of the receive packet
                   
          InetAddress IPAddress = receivePacket.getAddress(); 
          int port = receivePacket.getPort(); 
          
          //Acknowledge the receive packet
          System.out.println ("Received " + receivePacket.getLength() 
                                + " byte(s).");

          sendData = receivePacket.getData();
  
          DatagramPacket sendPacket = 
             new DatagramPacket(sendData, sendData.length, IPAddress, port); 
          System.out.println("Return packet to client.");
          serverSocket.send(sendPacket); 

        } 
     }
     //The port is not available
      catch (SocketException ex) {
        System.out.println("UDP Port 5555 is occupied.");
        System.exit(1);
      }
    
    } 
}  
