package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScene implements Initializable
{
    @FXML
    JFXButton add;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
      add.setOnAction(listener);
    }

    EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == add)
            {
                try{
                    NewTask();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    };

    public void NewTask() throws IOException
    {

        Stage stage = new Stage();
        stage.setTitle("New Task");
        stage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/TaskScene.fxml"));
        TaskController controller = new TaskController();

        try {
            Parent parent = loader.load();
            loader.setController(controller);
            Scene scene = new Scene(parent, 600,750);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
