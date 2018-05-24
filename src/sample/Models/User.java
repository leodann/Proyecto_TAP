package sample.Models;

import java.io.FileInputStream;
import java.sql.Blob;

public class User
{
    String Name, Adress, Mail, User, Password, Phone;
    FileInputStream fis;
    int lengthBytes;


    public User(FileInputStream fis,int lengthBytes,String name, String adress, String phone, String mail, String user, String password) {
        this.fis = fis;
        this.lengthBytes = lengthBytes;
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

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    public int getLengthBytes() {
        return lengthBytes;
    }

    public void setLengthBytes(int lengthBytes) {
        this.lengthBytes = lengthBytes;
    }
}
