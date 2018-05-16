package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class ViewerController implements Initializable{
    @FXML
    JFXButton btnInbox,btnToday,btnN7,btnOnf,btnCalendar;

    //private String nameP;


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

        }
    };

}
