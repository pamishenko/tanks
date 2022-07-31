package edu.school21.app;

import com.beust.jcommander.JCommander;
import edu.school21.utils.Args;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Args arguments = new Args();
        JCommander jCommander = new JCommander();
        jCommander.addObject(arguments);
        jCommander.parse(args);
        jCommander.getParameters();
//        int port = arguments.getPort();
        String ip = "127.0.0.1";
        int port = 8001; // FIXME - это заглушка для локальных тестов

        ServerSocket serverSocket = new ServerSocket(port);
        Socket client1Socket = serverSocket.accept();
        System.out.println("client1 connected");
        Socket client2Socket = serverSocket.accept();
        System.out.println("client2 connected");

        BufferedReader bufferedReader1 =
                new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
        OutputStreamWriter outputStreamWriter1 =
                new OutputStreamWriter(client1Socket.getOutputStream());
        BufferedReader bufferedReader2 =
                new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
        OutputStreamWriter outputStreamWriter2 =
                new OutputStreamWriter(client1Socket.getOutputStream());

        while (true){
            char[] buf = new char[1024];
            bufferedReader1.read(buf);
            String signal1 = buf[0] + "\n";
            bufferedReader2.read(buf);
            String signal2 = buf[0] + "\n";
            outputStreamWriter1.write(signal2);
            outputStreamWriter1.flush();
            outputStreamWriter2.write(signal1);
            outputStreamWriter2.flush();
        }
    }
}
