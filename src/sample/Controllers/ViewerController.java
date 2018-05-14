package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewerController implements Initializable{
    @FXML
    JFXButton btnInbox,btnToday,btnN7,btnOnf,btnCalendar;
    @FXML
    VBox Vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnInbox.setOnAction(Listener);
        btnToday.setOnAction(Listener);
        btnN7.setOnAction(Listener);
        btnOnf.setOnAction(Listener);
        btnCalendar.setOnAction(Listener);
    }

    EventHandler<ActionEvent> Listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == btnInbox){
                LoadPanel("InboxXML.fxml");
            }
        }
    };

    private void LoadPanel(String name){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../FXML/"+name));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(ViewerController.class.getName()).log(Level.SEVERE,null,e);
        }


    }
}
