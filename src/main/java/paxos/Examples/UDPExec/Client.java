package paxos.Examples.UDPExec;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws Exception {
        BufferedReader user_input = new BufferedReader((new InputStreamReader(System.in)));
        DatagramSocket client_socket = new DatagramSocket();
        InetAddress ip_add = InetAddress.getByName("localhost");
        byte[] in_data = new byte[1024];
        byte[] out_data = new byte[1024];
        String str = user_input.readLine();
        out_data = str.getBytes();
        DatagramPacket packet1 = new DatagramPacket(out_data, out_data.length, ip_add, 1234);
        client_socket.send(packet1);
        DatagramPacket packet4 = new DatagramPacket(in_data, in_data.length);
        client_socket.receive(packet4);
        String receive_str = new String(packet4.getData());
        System.out.println(receive_str);
        client_socket.close();
    }
}
