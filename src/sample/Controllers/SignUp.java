package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.Models.DAO.UserDAO;
import sample.Models.User;
import sample.MySQL;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable
{
    @FXML
    JFXTextField Name, Adress, Mail, Phone, User;
    @FXML
    JFXPasswordField Password;
    @FXML
    JFXButton Register;

    UserDAO userdao = new UserDAO(MySQL.getConnection());
    User aux;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Register.setOnAction(Listener);
    }

    EventHandler<ActionEvent> Listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if( event.getSource() == Register){
                Registrar();
            }
        }
    };

    public void Registrar(){
        int numero = Integer.parseInt(Phone.getText());
        aux = new User(Name.getText(), Adress.getText(), numero, Mail.getText(), User.getText(), Password.getText());
        userdao.insert(aux);
    }
}
