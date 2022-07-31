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

        InputStreamReader bufferedReader1 = new InputStreamReader(client1Socket.getInputStream());
        OutputStreamWriter outputStreamWriter1 =
                new OutputStreamWriter(client1Socket.getOutputStream());

        InputStreamReader bufferedReader2 = new InputStreamReader(client2Socket.getInputStream());

        OutputStreamWriter outputStreamWriter2 =
                new OutputStreamWriter(client2Socket.getOutputStream());

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println("Task #1 is running");
                while (true){
                    try {
                        outputStreamWriter2.write(bufferedReader1.read());
                        outputStreamWriter2.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println("Task #2 is running");
                while (true){
                    try {
                        outputStreamWriter1.write(bufferedReader2.read());
                        outputStreamWriter1.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
