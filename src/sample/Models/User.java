package sample.Models;

import java.io.FileInputStream;
import java.sql.Blob;

public class User
{
    String Name, Adress, Mail, User, Password, Phone;
    byte [] image;


    public User(byte[]image,String name, String adress, String phone, String mail, String user, String password) {
        this.image = image;
        this.Name = name;
        this.Adress = adress;
        this.Mail = mail;
        this.User = user;
        this.Password = password;
        this.Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
