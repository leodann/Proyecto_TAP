package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    JFXListView<String> listView;
    @FXML
    JFXButton btnSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
