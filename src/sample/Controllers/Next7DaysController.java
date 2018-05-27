package sample.Controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.Manage;
import sample.Models.DAO.TaskDAO;
import sample.Models.Task;
import sample.MySQL;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Next7DaysController implements Initializable {
    @FXML
    JFXListView<VBox> listView;
    @FXML
    Label lblTittle;

    private ObservableList<VBox> listVbox = FXCollections.observableArrayList();
    private ObservableList<Task> listTask = FXCollections.observableArrayList();
    private TaskDAO taskDao = new TaskDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeListVbox();

    }

    private void makeListVbox(){
        initTaskList();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        for(int i = 0; i<listTask.size();i++){
            VBox vbox = new VBox();
            vbox.setSpacing(20);
            Label lblTitulo = new Label(listTask.get(i).getTitle().toString());
            lblTitulo.setFont(new Font("Arial",30));
            Label lblNotas = new Label(listTask.get(i).getNotes().toString());
            lblNotas.setFont(new Font("Arial",20));
            HBox hbox1 = new HBox(   new Label("Category: "+listTask.get(i).getCategory().toString()),
                    new Label("Tags: "+listTask.get(i).getTags().toString()),
                    new Label("Priority: "+Character.toString(listTask.get(i).getPriority())));
            hbox1.setSpacing(10);
            hbox1.setAlignment(Pos.CENTER);
            HBox hbox2 = new HBox(  new Label("started: "+formater.format(listTask.get(i).getStarFrom())),
                    new Label("finish by: "+ formater.format(listTask.get(i).getFinishBy())),
                    new Label("Estimated time: "+ Integer.toString(listTask.get(i).getEstimated_Time())+" hrs"));
            hbox2.setSpacing(10);
            hbox2.setAlignment(Pos.CENTER);
            JFXRadioButton rb = new JFXRadioButton("Done");
            vbox.getChildren().addAll(lblTitulo,lblNotas,hbox1,hbox2,rb);
            vbox.setAccessibleText(""+i);
            listVbox.add(vbox);
        }
        initListView();
    }

    private ObservableList initTaskList(){
        listTask = taskDao.fetchNext7();

        return listTask;
    }

    private ListView initListView(){
        listView.setAccessibleText("1");
        listView.depthProperty().setValue(1);
        listView.setVerticalGap(30.0);
        listView.setExpanded(true);
        listView.getItems().clear();
        listView.setItems(listVbox);
        return listView;
    }
}
