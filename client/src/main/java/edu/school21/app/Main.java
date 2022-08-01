package edu.school21.app;

import com.beust.jcommander.JCommander;
import edu.school21.service.Windows;
import edu.school21.utils.Args;
import javafx.application.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Args arguments = new Args();
        JCommander jCommander = new JCommander();
        jCommander.addObject(arguments);
        jCommander.parse(args);
        jCommander.getParameters();
//        String ip = arguments.getServer();
//        int port = arguments.getPort();
//        String username = arguments.getName();

        String ip = "127.0.0.1"; // FIXME - это заглушка для локальных тестов
        int port = 8001;
        String username = "User";



        Socket clientSocket = new Socket(ip, port);
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(clientSocket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // FIXME - Заглушка пока нет javaFX и клавиш



        Windows win = new Windows();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                win.launch();
            }
        });
        thread.start();

        Thread threadSend = new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println("Task #1 is running");
                while (true){
                    char command = win.getKey();  // FIXME сюда нужно вставить метод читающий с клавиатуры и возвращающий нужную букву
//                        char[] temp = new char[1];  // удалить после подключения FX
//                        reader.read(temp);          // удалить после подключения FX
//                        command = temp[0];          // удалить после подключения FX
//                        outputStreamWriter.write(command);
//                        outputStreamWriter.flush();
                    // FIXME сдесь нужно вызвать интерпретатор для собственных событий
                }
            }
        });

        Thread threadReciev = new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println("Task #2 is running");
                while (true){
                    try {
                        char command;               // удалить после подключения FX
                        char[] temp = new char[1];  // удалить после подключения FX
                        bufferedReader.read(temp);  // удалить после подключения FX
                        command = temp[0];
                        if (temp[0] != '\n')// удалить после подключения FX
                            System.out.println(command); // удалить после подключения FX
                        // FIXME сдесь нужно вызвать интерпретатор для событий противника
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        threadSend.start();
        threadReciev.start();

        try {
            thread.join();
            threadSend.join();
            threadReciev.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
