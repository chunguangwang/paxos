package paxos.Examples.UDPExec;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws Exception{
        DatagramSocket server_socket = new DatagramSocket(1234);
        BufferedReader server_input = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ip_add = InetAddress.getByName("localhost");
        byte[] out_data = new byte[1024];
        byte[] in_data = new byte[1024];
        while(true){
            DatagramPacket packet2 = new DatagramPacket(in_data, in_data.length);
            server_socket.receive(packet2);
            String str = new String(packet2.getData());
            System.out.println(str);

            InetAddress ip_add1 = packet2.getAddress();
            int port = packet2.getPort();
            String send_str = server_input.readLine();
            out_data = send_str.getBytes();
            DatagramPacket packet3 = new DatagramPacket(out_data, out_data.length, ip_add1, port);
            server_socket.send(packet3);
        }
    }
}
