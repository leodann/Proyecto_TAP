package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    JFXButton btnSearch;
    @FXML
    ToggleGroup toggleSearch;

    private JFXRadioButton selected;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSearch.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnSearch){
                selected = (JFXRadioButton) toggleSearch.getSelectedToggle();
                if(selected!= null){
                    btnSearch.setAccessibleText(""+selected.getText());
                }else{
                    System.out.println("WRONG¡");
                }
            }
        }
    };

    private void Search(){

    }
}
