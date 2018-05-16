package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Models.DAO.TaskDAO;
import sample.Models.Task;
import sample.MySQL;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class TaskController implements Initializable {

    @FXML
    JFXDatePicker DateStart,DateFinish;

    @FXML
    JFXButton btndone,btncancel;

    @FXML
    ToggleGroup togglePriority;

    @FXML
    JFXTextField FieldTitle,FieldNotes,FieldEstimatedTime,FieldTags,FieldCategory;

    private TaskDAO taskDAO = new TaskDAO(MySQL.getConnection());
    private Task task;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btndone.setOnAction(Listener);
        btncancel.setOnAction(Listener);
    }

    EventHandler<ActionEvent> Listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btncancel){
                CancelTask();
            }
            else if(event.getSource() == btndone){
                SaveTask();
            }
        }
    };

    private void SaveTask(){
        task = new Task();
        LocalDate Date_S = DateStart.getValue();
        LocalDate Date_F = DateFinish.getValue();
        int ByFinish = Integer.parseInt(FieldEstimatedTime.getText());
        RadioButton selected = (RadioButton) togglePriority.getSelectedToggle();
        char C_priority = selected.getText().charAt(0);

        task.setTitle(FieldTitle.getText());
        task.setNotes(FieldNotes.getText());
        task.setStarFrom(Date.valueOf(Date_S));
        task.setFinishBy(Date.valueOf(Date_F));
        task.setEstimated_Time(ByFinish);
        task.setTags(FieldTags.getText());
        task.setCategory(FieldCategory.getText());
        task.setPriority(C_priority);

        SaveTask(task);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task Done");
        alert.setHeaderText("Task saved");
        alert.setContentText("Ya estaria");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Stage stage = (Stage) btndone.getScene().getWindow();
            stage.close();
        }
    }

    private boolean SaveTask(Task task){
        return taskDAO.insert(task);
    }

    private void CancelTask(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel task confirmation");
        alert.setHeaderText("Cancel Task");
        alert.setContentText("Are you ok with this");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            Stage stage = (Stage) btncancel.getScene().getWindow();
            stage.close();
        }
    }
}
