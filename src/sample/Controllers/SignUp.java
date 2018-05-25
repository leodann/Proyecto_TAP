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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;
import sample.Models.DAO.UserDAO;
import sample.Models.User;
import sample.MySQL;

import java.io.*;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SignUp implements Initializable
{
    @FXML
    JFXTextField Name, Adress, Mail, Phone, User;
    @FXML
    JFXPasswordField Password;
    @FXML
    JFXButton Register,btnImage,Cancel;
    @FXML
    ImageView Imageview;

    UserDAO userdao = new UserDAO(MySQL.getConnection());
    User aux;
    List<User> lista;
    int cont;
    private Blob blob;
    private File imgFile;
    private FileInputStream fis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Register.setOnAction(Listener);
        btnImage.setOnAction(Listener);
        Cancel.setOnAction(Listener);
    }

    EventHandler<ActionEvent> Listener = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if( event.getSource() == Register){
                Registrar();
            }
            else if(event.getSource() == btnImage){
                selectImage();
            }

            else if(event.getSource() == Cancel){
                ExitSignOut();
            }
        }
    };

    public void Registrar(){
        /*try{fis = new FileInputStream(imgFile);}catch (Exception e){e.printStackTrace();}
        aux = new User(fis,(int)imgFile.length(),Name.getText(), Adress.getText(), Phone.getText(), Mail.getText(), User.getText(), Password.getText());
        userdao.insert(aux);
        try {
            Parent mainscene = FXMLLoader.load(getClass().getResource("../FXML/LogIn.fxml"));
            Stage StageP1;
            Scene scene = new Scene(mainscene, 600, 380);
            StageP1 = Main.homeS;
            StageP1.setScene(scene);


        }catch (IOException e){
            e.printStackTrace();
        }*/
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
            //aux = new User(,Name.getText(), Adress.getText(), Phone.getText(), Mail.getText(), User.getText(), Password.getText());
            userdao.insert(aux);
            try{fis = new FileInputStream(imgFile);}catch (Exception e){e.printStackTrace();}

            aux = new User(fis,(int)imgFile.length(),Name.getText(), Adress.getText(), Phone.getText(), Mail.getText(), User.getText(), Password.getText());
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

    private void selectImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Search an image");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        Stage stage = new Stage();
        imgFile = fileChooser.showOpenDialog(stage);

        if(imgFile!= null){
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            Imageview.setImage(image);


        }
        System.out.println(""+imgFile.getAbsolutePath());
    }

    private void ExitSignOut(){
        try{
            Parent LogIn = FXMLLoader.load(getClass().getResource("../FXML/LogIn.fxml"));
            Stage StageP1;
            Scene scene = new Scene(LogIn,600,420);
            StageP1 = Main.homeS;
            StageP1.setScene(scene);
            StageP1.setResizable(true);} catch (Exception e ){e.printStackTrace();}
    }
}


