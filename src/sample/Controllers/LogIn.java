package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable
{
    @FXML
    Button signup;



    EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==signup)
            {
                try{
                    getSignUp();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    };



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        signup.setOnAction(listener);
    }

    private void getSignUp() throws IOException
    {
        Parent signup = FXMLLoader.load(getClass().getResource("../FXML/SignUp.fxml"));
        Stage StageP1;
        Scene scene = new Scene(signup,600,500);
        StageP1 = Main.homeS;
        StageP1.setScene(scene);

    }

}

