package sample.Controllers;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Manage;
import sample.Models.DAO.TaskDAO;
import sample.Models.Task;
import sample.MySQL;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ControllerMain_a implements Initializable {
    @FXML
    JFXHamburger Hamburger;
    @FXML
    JFXDrawer Drawer;
    @FXML
    BorderPane BorderPaneM;
    @FXML
    Button btnAdd,btnSearch, btnPrint;
    @FXML
    JFXListView<VBox> listView;
    @FXML
    BorderPane Center;


    private String namePaneCenter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initHamburger();
        initDrawer();
        namePaneCenter = "../FXML/InboxXML.fxml";
        ReloadCenterContent(namePaneCenter);
        btnAdd.setOnAction(handler);
        btnSearch.setOnAction(handler);
        btnPrint.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource()==btnAdd){
                try {
                    NewTask();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(event.getSource() == btnSearch){
               Searchstage();
            }
            if(event.getSource() == btnPrint){
                Print();
            }
        }
    };

    public void Searchstage(){

        Stage stage = new Stage();
        stage.setTitle("New Task");
        stage.setResizable(false);

        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Search.fxml"));
        CreateTaskController controller = new CreateTaskController();


            Parent parent = loader.load();
            loader.setController(controller);
            Scene scene = new Scene(parent, 465.0,320.0);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                //ReloadCenterContent(namePaneCenter);
            }
        });
    }

    public void NewTask() throws IOException {

        Stage stage = new Stage();
        stage.setTitle("New Task");
        stage.setResizable(false);

        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/CreateTask.fxml"));
        CreateTaskController controller = new CreateTaskController();

            Parent parent = loader.load();
            loader.setController(controller);
            Scene scene = new Scene(parent, 600,750);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                ReloadCenterContent(namePaneCenter);
            }
        });
    }

    private void initHamburger() {
        HamburgerBackArrowBasicTransition HamburgerTransition = new HamburgerBackArrowBasicTransition(Hamburger);
        HamburgerTransition.setRate(-1);
        Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HamburgerTransition.setRate(HamburgerTransition.getRate() * -1);
                HamburgerTransition.play();

                if (Drawer.isShown()) {
                    Drawer.close();
                } else {
                    Drawer.open();
                }
            }
        });
    }

    private void initDrawer(){
        try {
            VBox boxD = FXMLLoader.load(getClass().getResource("../FXML/DrawerXML.fxml"));
            Drawer.setSidePane(boxD);

            for(Node node : boxD.getChildren()){
                if(node.getAccessibleText() != null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            switch (node.getAccessibleText()){
                                case "btnInbox" :
                                    namePaneCenter = "../FXML/InboxXML.fxml";
                                    ReloadCenterContent(namePaneCenter);
                                    break;

                                case "btnToday":
                                    namePaneCenter = "../FXML/Today.fxml";
                                    ReloadCenterContent(namePaneCenter);
                                    break;

                                case "btnN7":
                                    namePaneCenter = "../FXML/Next7Days.fxml";
                                    ReloadCenterContent(namePaneCenter);
                                    break;

                                case "btnOnF":
                                    namePaneCenter = "../FXML/OnFocus.fxml";
                                    ReloadCenterContent(namePaneCenter);
                                    break;

                                case "bntCalendar":
                                    namePaneCenter = "../FXML/Calendar.fxml";
                                    ReloadCenterContent(namePaneCenter);
                                    break;

                            }
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Parent getParentCenter(String name){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(""+name));
        }catch (IOException e){e.printStackTrace();}
        return root;
    }

    private void ReloadCenterContent(String name){
        System.out.println("Reload");
        BorderPaneM.setCenter(getParentCenter(name));
    }

    public void Print(){
        Stage stage = new Stage();
        stage.setTitle("Report");
        stage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Print.fxml"));
        PrintController controller = new PrintController();

        try {
            Parent parent = loader.load();
            loader.setController(controller);
            Scene scene = new Scene(parent, 600, 350);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
