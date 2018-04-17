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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;
import sample.Models.DAO.UserDAO;
import sample.Models.User;
import sample.MySQL;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LogIn implements Initializable
{
    @FXML
    JFXButton signup, login;
    @FXML
    JFXPasswordField Password;
    @FXML
    JFXTextField User;

    int cont;
    UserDAO userdao = new UserDAO(MySQL.getConnection());
    User aux;
    List<User> lista;


    EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==signup)
            {
                try{
                    getSignUp();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if(event.getSource() == login){
                Log();
            }

        }
    };



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        signup.setOnAction(listener);
        login.setOnAction(listener);
    }

    private void getSignUp() throws IOException
    {
        Parent signup = FXMLLoader.load(getClass().getResource("../FXML/SignUp.fxml"));
        Stage StageP1;
        Scene scene = new Scene(signup,600,420);
        StageP1 = Main.homeS;
        StageP1.setScene(scene);
        StageP1.setResizable(false);

    }

    private void Log()
    {
        lista = userdao.findAll();

        for(int i=1; i<lista.size(); i++ )
        {
            aux = lista.get(i);
            if(User.getText().compareTo(aux.getUser()) == 0 && Password.getText().compareTo(aux.getPassword()) == 0)
            {
                cont = cont +1;
            }
        }
        if(cont == 1)
        {
            try {
                Parent mainscene = FXMLLoader.load(getClass().getResource("../FXML/MainScene.fxml"));
                Stage StageP1;
                Scene scene = new Scene(mainscene);
                StageP1 = Main.homeS;
                StageP1.setScene(scene);
                StageP1.setMaximized(true);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else
        {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("EROR");
            alerta.setHeaderText("Wrong Information");
            alerta.setContentText("Incorrect User or Password");
            alerta.show();
        }
    }

}

