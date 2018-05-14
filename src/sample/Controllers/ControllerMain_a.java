package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain_a implements Initializable {
    @FXML
    JFXButton btnAdd, btnDelete, btnEdit, btnSearch, btnPrint;
    @FXML
    JFXHamburger Hamburger;
    @FXML
    JFXDrawer Drawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initHamburger();
        initDrawer();
        btnAdd.setOnAction(Listener);

    }

    EventHandler <ActionEvent> Listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == btnAdd){
                try {
                    NewTask();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void NewTask() throws IOException
    {

        Stage stage = new Stage();
        stage.setTitle("New Task");
        stage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/TaskScene.fxml"));
        TaskController controller = new TaskController();

        try {
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
    }


    private void initHamburger() {
        HamburgerBackArrowBasicTransition HamburgerTransition = new HamburgerBackArrowBasicTransition(Hamburger);
        HamburgerTransition.setRate(-1);
        Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            HamburgerTransition.setRate(HamburgerTransition.getRate() * -1);
            HamburgerTransition.play();


            if (Drawer.isShown()) {
                Drawer.close();
            } else {
                Drawer.open();
            }
        });
    }

        private void initDrawer(){
        try {
            VBox boxD = FXMLLoader.load(getClass().getResource("../FXML/DrawerXML.fxml"));
            Drawer.setSidePane(boxD);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
