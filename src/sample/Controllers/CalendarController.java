package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jfxtras.scene.layout.HBox;
import sample.Models.DAO.TaskDAO;
import sample.Models.Task;
import sample.MySQL;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    @FXML
    JFXDatePicker dpTask;
    @FXML
    Label lblDate_Day,lblDate_Month;
    @FXML
    JFXButton btnGo;
    @FXML
    JFXListView<VBox> listView;

    private ObservableList<VBox> listVbox = FXCollections.observableArrayList();
    private ObservableList<Task> listTask = FXCollections.observableArrayList();
    private TaskDAO taskDao = new TaskDAO(MySQL.getConnection());

    private Calendar calendar = Calendar.getInstance();
    private String Month,Day,Year = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCalendarlbls();
        btnGo.setOnAction(handler);

    }
    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==btnGo){
                makeListVbox();
            }
        }
    };

    private void makeListVbox(){
        initTaskList();
        if(listTask!=null) {
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
            for (int i = 0; i < listTask.size(); i++) {
                VBox vbox = new VBox();
                vbox.setSpacing(20);
                Label lblTitulo = new Label(listTask.get(i).getTitle().toString());
                lblTitulo.setFont(new Font("Arial", 30));
                Label lblNotas = new Label(listTask.get(i).getNotes().toString());
                lblNotas.setFont(new Font("Arial", 20));
                javafx.scene.layout.HBox hbox1 = new javafx.scene.layout.HBox(new Label("Category: " + listTask.get(i).getCategory().toString()),
                        new Label("Tags: " + listTask.get(i).getTags().toString()),
                        new Label("Priority: " + Character.toString(listTask.get(i).getPriority())));
                hbox1.setSpacing(10);
                hbox1.setAlignment(Pos.CENTER);
                javafx.scene.layout.HBox hbox2 = new javafx.scene.layout.HBox(new Label("started: " + formater.format(listTask.get(i).getStarFrom())),
                        new Label("finish by: " + formater.format(listTask.get(i).getFinishBy())),
                        new Label("Estimated time: " + Integer.toString(listTask.get(i).getEstimated_Time()) + " hrs"));
                hbox2.setSpacing(10);
                hbox2.setAlignment(Pos.CENTER);
                javafx.scene.layout.HBox hbox3 = new javafx.scene.layout.HBox();
                hbox3.setSpacing(20);
                JFXRadioButton rb = new JFXRadioButton("Done");
                boolean bool;
                if(listTask.get(i).isStatus() != false){
                    bool = true;
                }else{
                    bool = false;
                }
                rb.setSelected(bool);
                Task ta = listTask.get(i);
                rb.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(ta.isStatus() ==  true) {
                            ta.setStatus(false);
                        }else{ta.setStatus(true);}

                        taskDao.update(ta);
                        System.out.println(ta.isStatus());
                    }
                });
                JFXRadioButton rbF = new JFXRadioButton("Focus");
                boolean boolF;
                if(listTask.get(i).isFocus() != false){
                    boolF = true;
                }else{
                    boolF = false;
                }
                rbF.setSelected(boolF);
                rbF.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(ta.isFocus() ==  true) {
                            ta.setFocus(false);
                        }else{ta.setFocus(true);}

                        taskDao.update(ta);
                        System.out.println(ta.isFocus());
                    }
                });
                hbox3.getChildren().addAll(rb,rbF);
                vbox.getChildren().addAll(lblTitulo, lblNotas, hbox1, hbox2, hbox3);
                listVbox.add(vbox);
            }
            initListView();
        }
        else{
            System.out.println("adasdasd");
        }
    }

    private ObservableList initTaskList(){
        LocalDate localDate = dpTask.getValue();
        if(localDate!=null) {
            java.sql.Date das = java.sql.Date.valueOf(localDate);
            String d = "'" + das.toString() + "'";
            listTask = taskDao.fetchByDate(d);
        }else{ Alert();}
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

    private void Alert(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("EROR");
        alerta.setHeaderText("Wrong Information");
        alerta.setContentText("Please set a date");
        alerta.show();

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
