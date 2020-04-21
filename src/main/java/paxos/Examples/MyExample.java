package paxos.Examples;

import paxos.BasicGroup;
import paxos.Receiver;
import paxos.communication.Member;
import paxos.communication.Members;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MyExample {


    /**
     *
     * output
     * "/Applications/IntelliJ IDEA CE.app/Contents/jbr/Contents/Home/bin/java" -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:51129,suspend=y,server=n -javaagent:/Users/chunguangwang/Library/Caches/IdeaIC2019.3/captureAgent/debugger-agent.jar -Dfile.encoding=UTF-8 -classpath "/Users/chunguangwang/MyDocuments/paxos/target/classes:/Users/chunguangwang/.m2/repository/javax/validation/validation-api/1.1.0.Final/validation-api-1.1.0.Final.jar:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar" paxos.MyExample
     * Connected to the target VM, address: '127.0.0.1:51129', transport: 'socket'
     * Chunguangs-MacBook-Pro.local:2440: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2441: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2442: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2442: I am the leader
     * I am not the leader
     * Chunguangs-MacBook-Pro.local:2442: taking leadership
     * Chunguangs-MacBook-Pro.local:2442: taking leadership
     * Chunguangs-MacBook-Pro.local:2441: taking leadership
     * Chunguangs-MacBook-Pro.local:2440: taking leadership
     * Chunguangs-MacBook-Pro.local:2441: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2442: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2442: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2441: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2440: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2442: I am the leader
     * Chunguangs-MacBook-Pro.local:2440: setting leader to Chunguangs-MacBook-Pro.local:2442
     * Chunguangs-MacBook-Pro.local:2442: I am the leader
     * received Hello
     * received Hello
     * received Hello
     * Chunguangs-MacBook-Pro.local:2442: taking leadership
     * Chunguangs-MacBook-Pro.local:2442: taking leadership
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // this is the list of members
        Members members = new Members(
                new Member(), // this is a reference to a member on the localhost on default port (2440)
                new Member(2441) );// this one is on localhost with the specified port
               // new Member(InetAddress.getLocalHost(), 2442)); // you can specify the address and port manually



        // this actually creates the members
        BasicGroup group1 = new BasicGroup(members.get(0), new MyReceiver());
        BasicGroup group2 = new BasicGroup(members.get(1), new MyReceiver());
//        BasicGroup group3 = new BasicGroup(members.get(2), new MyReceiver());

        // this will cause all receivers to print "received Hello"
//        group1.broadcast("Hello");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();
        Thread.sleep(10); // allow the members to receive the message

        group1.close(); //group2.close(); group3.close();

    }

}

// we need to define a receiver
class MyReceiver implements Receiver {
    // we follow a reactive pattern here
    public void receive(Serializable message) {
        System.out.println("received " + message.toString());
    }
};
