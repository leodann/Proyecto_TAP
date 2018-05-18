package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.Models.DAO.TaskDAO;
import sample.Models.Task;
import sample.MySQL;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.*;

public class InboxController implements Initializable {
    @FXML
    JFXListView <VBox> listView;

    private ObservableList<VBox> listVbox = FXCollections.observableArrayList();
    private ObservableList<Task> listTask = FXCollections.observableArrayList();
    private TaskDAO taskDao = new TaskDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeListVbox();

    }

    private void makeListVbox(){
        initTaskList();
        for(int i = 0; i<listTask.size();i++){
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            Label lblTitulo = new Label(listTask.get(i).getTitle().toString());
            lblTitulo.setFont(new Font("Arial",30));
            Label lblNotas = new Label(listTask.get(i).getNotes().toString());
            lblNotas.setFont(new Font("Arial",15));
            JFXButton btn = new JFXButton(""+i);
            vbox.getChildren().addAll(lblTitulo,lblNotas,btn);
            listVbox.add(vbox);
        }
        listTask.clear();
        listView.setItems(listVbox);
    }

    private ObservableList initTaskList(){
        listTask = taskDao.fetchAll();
        return listTask;
    }
}
