package edu.school21.app;

import com.beust.jcommander.JCommander;
import edu.school21.utils.Args;

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
        // sout

        String ip = "127.0.0.1"; // FIXME - это заглушка для локальных тестов
        int port = 8001;
        String username = "User";



        Socket clientSocket = new Socket(ip, port);
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(clientSocket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // FIXME - Заглушка пока нет javaFX и клавиш

        while (true){
            String request = reader.readLine() + "\n";
            outputStreamWriter.write(request);
            outputStreamWriter.flush();
            System.out.println("-> " + bufferedReader.readLine());
        }
    }
}
