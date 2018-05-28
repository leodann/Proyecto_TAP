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
import sample.Manage;
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
    {   //Se inicializa la variable static_user en null
        Manage.statci_user = null;
        signup.setOnAction(listener);
        login.setOnAction(listener);
    }

    private void getSignUp() throws IOException
    {
        Parent signup = FXMLLoader.load(getClass().getResource("../FXML/SignUp.fxml"));
        Stage StageP1;
        Scene scene = new Scene(signup,650,440);
        StageP1 = Main.homeS;
        StageP1.setScene(scene);
        StageP1.setResizable(true);

    }

    private void Log()
    {
        //lista = userdao.findAll();
        aux = userdao.fetch(User.getText(),Password.getText());
        if(aux!= null){
            Manage.statci_user = aux;
            System.out.println(""+Manage.statci_user.getUser());
            try {
                Parent mainscene = FXMLLoader.load(getClass().getResource("../FXML/Main_a.fxml"));
                Stage StageP1;
                Scene scene = new Scene(mainscene,1120,720);
                StageP1 = Main.homeS;
                StageP1.setScene(scene);
                StageP1.setMaximized(true);
                StageP1.setResizable(true);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        /*for(int i=0; i<lista.size(); i++ )
        {
            aux = lista.get(i);
            if(User.getText().compareToIgnoreCase(aux.getUser()) == 0 && Password.getText().compareToIgnoreCase(aux.getPassword()) == 0)
            {
                cont = cont +1;
            }
        }
        if(cont == 1)
        {
            //Se guarda el usuario que ingreso en la variable static_user para poderlo usar posteriormente.
            Manage.statci_user = aux;
            System.out.println(""+Manage.statci_user.getUser());
            try {
                Parent mainscene = FXMLLoader.load(getClass().getResource("../FXML/Main_a.fxml"));
                Stage StageP1;
                Scene scene = new Scene(mainscene,1120,720);
                StageP1 = Main.homeS;
                StageP1.setScene(scene);
                StageP1.setMaximized(true);
                StageP1.setResizable(true);

            }catch (IOException e){
                e.printStackTrace();
            }
        }*/
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

