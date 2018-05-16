package sample.Controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import jfxtras.scene.layout.HBox;
import sample.Models.DAO.TaskDAO;
import sample.MySQL;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    JFXDatePicker dpTask;
    @FXML
    Label lblDate_Day,lblDate_Month;

    private Date date = new Date();
    private Calendar calendar = Calendar.getInstance();
    private TaskDAO taksdao = new TaskDAO(MySQL.getConnection());
    private String Month,Day,Year = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(calendar.getTime());
        initCalendarlbls();

    }

    private String getStringMonth(){
        switch (calendar.get(Calendar.MONTH)){
            case 0:
                Month = "Enero";
                break;

            case 1:
                Month = "Feberero";
                break;

            case 2:
                Month = "Marzo";
                break;

            case 3 :
                Month = "Abril";
                break;

            case 4:
                Month = "Mayo";
                break;

            case 5:
                Month = "Junio";
                break;

            case 6:
                Month = "Julio";
                break;

            case 7:
                Month = "Agosto";
                break;

            case 8:
                Month = "Septiembre";
                break;

            case 9:
                Month = "Octubre";
                break;

            case 10:
                Month = "Noviembre";
                break;

            case 11:
                Month = "Diciembre";
                break;

        }
        return Month;
    }

    private String getStringDay(){
        Day = Integer.toString(calendar.get(Calendar.DATE));
        return Day;
    }

    private String getStringYear(){
        Year = Integer.toString(calendar.get(Calendar.YEAR));
        return Year;
    }

    private void initCalendarlbls(){
        Date date = new Date();
        calendar.setTime(date);
        lblDate_Day.setText(getStringDay());
        lblDate_Month.setText(getStringMonth()+" "+getStringYear());
    }
}
