package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Models.DAO.UserDAO;
import sample.Models.User;

public class Main extends Application {
    public static Stage homeS;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/LogIn.fxml"));
        homeS = primaryStage;
        primaryStage.setTitle("LogIn Task Manager");
        primaryStage.setScene(new Scene(root, 600, 380));
        primaryStage.setMaximized(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("Pusheado90");


    }
}
