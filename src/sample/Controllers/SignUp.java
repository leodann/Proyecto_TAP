package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.Models.DAO.UserDAO;
import sample.Models.User;
import sample.MySQL;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    List<User> lista;
    int cont;

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
        lista = userdao.findAll();

        for(int i = 1; i< lista.size(); i++)
        {
            aux = lista.get(i);
            if(User.getText().compareTo(aux.getUser()) == 0 || Mail.getText().compareTo(aux.getMail()) == 0)
            {
                cont = cont +1;
            }

                System.out.println(cont);

        }

        if(cont>=1)
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("User or Mail already registered");
            alerta.setContentText("Try Another User or Mail Account Please");
            alerta.show();
            cont = 0;
        }
        else
        {
            aux = new User(Name.getText(), Adress.getText(), Phone.getText(), Mail.getText(), User.getText(), Password.getText());
            userdao.insert(aux);
            try {
                Parent mainscene = FXMLLoader.load(getClass().getResource("../FXML/LogIn.fxml"));
                Stage StageP1;
                Scene scene = new Scene(mainscene, 600, 380);
                StageP1 = Main.homeS;
                StageP1.setScene(scene);


            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}

