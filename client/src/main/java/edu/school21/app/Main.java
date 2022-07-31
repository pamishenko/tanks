package edu.school21.app;

import com.beust.jcommander.JCommander;
import edu.school21.utils.Args;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;

public class Main  extends Application {
    public static void main(String[] args) throws IOException {
        Args arguments = new Args();
        JCommander jCommander = new JCommander();
        jCommander.addObject(arguments);
        jCommander.parse(args);
        jCommander.getParameters();
//        String ip = arguments.getServer();
//        int port = arguments.getPort();
//        String username = arguments.getName();

        Application.launch(); // запускаем окно

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


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tanks!");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);

        InputStream iconStream = getClass().getResourceAsStream("/images/player.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

        Label helloWorldLabel = new Label("Hello world!");
        helloWorldLabel.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(helloWorldLabel);
        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }

}
