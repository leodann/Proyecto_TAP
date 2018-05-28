package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.Manage;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    JFXButton btnSearch;
    @FXML
    ToggleGroup toggleSearch;
    @FXML
    JFXTextField  tfSearch;

    private JFXRadioButton selected;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Manage.static_search = "tittle";
        btnSearch.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnSearch){
                selected = (JFXRadioButton) toggleSearch.getSelectedToggle();
                if(selected!= null){
                    Manage.static_search = ""+selected.getText();
                    Manage.static_word = tfSearch.getText();
                    System.out.println(Manage.static_search);
                }else{
                    Manage.static_search = "tittle";
                    Manage.static_word = tfSearch.getText();
                    System.out.println(Manage.static_search);
                }
            }
        }
    };

}
