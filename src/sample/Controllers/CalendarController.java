package sample.Controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.Models.DAO.TaskDAO;
import sample.MySQL;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    JFXDatePicker dpTask;
    @FXML
    Label lblDate;

    private Date date = new Date();
    private Calendar calendar = Calendar.getInstance();
    private TaskDAO taksdao = new TaskDAO(MySQL.getConnection());


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ShowDate();
        lblDate.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+"\nMayo");

    }

    private void ShowDate(){
       /* SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        lblDate.setText(formatDate.format(date));*/
    }


}
