package sample.Models.DAO;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.User;


public class UserDAO {

    Connection conn;
    public UserDAO(Connection conn)
    {
        this.conn = conn;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        try {
            String query = "SELECT * FROM users";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User p = null;
            int cont =0;
            while(rs.next()) {
                File file = new File("C:\\Users\\alexs\\Pictures\\image"+cont+".png");
                FileOutputStream fos = new FileOutputStream(file);
                byte bytes [];
                Blob blob = rs.getBlob("image");
                bytes = blob.getBytes(1,(int)blob.length());
                fos.write(bytes);
                fos.close();


                p = new User(
                        rs.getBlob("image"),
                        //rs.getBinaryStream("image"),
                        rs.getString("Name"),
                        rs.getString("Adress"),
                        rs.getString("Phone"),
                        rs.getString(  "Mail"),
                        rs.getString("User"),
                        rs.getString("Password")
                );
                users.add(p);
                cont++;
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }catch (IOException E){E.printStackTrace();}
        return users;
    }

    public ObservableList<User> fetchAll(){
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM users";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User p = null;
            int cont =0;
            while(rs.next()) {
                File file = new File("C:\\Users\\Loenardo Villanueva\\Desktop\\image"+cont+".png");
                FileOutputStream fos = new FileOutputStream(file);
                byte bytes [];
                Blob blob = rs.getBlob("image");
                bytes = blob.getBytes(1,(int)blob.length());
                fos.write(bytes);
                fos.close();


                p = new User(
                        rs.getBlob("image"),
                        //rs.getBinaryStream("image"),
                        rs.getString("Name"),
                        rs.getString("Adress"),
                        rs.getString("Phone"),
                        rs.getString(  "Mail"),
                        rs.getString("User"),
                        rs.getString("Password")
                );
                users.add(p);
                cont++;
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }catch (IOException E){E.printStackTrace();}
        return users;
    }

    public User fetch(String name_user){
        User e = null;
        try {
            String query = "SELECT * FROM users where User = '" + name_user + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User p = null;
            int cont =0;
            while(rs.next()) {
                File file = new File("C:\\Users\\Loenardo Villanueva\\Desktop\\image"+cont+".png");
                FileOutputStream fos = new FileOutputStream(file);
                byte bytes [];
                Blob blob = rs.getBlob("image");
                bytes = blob.getBytes(1,(int)blob.length());
                fos.write(bytes);
                fos.close();


                e = new User(
                        rs.getBlob("image"),
                        //rs.getBinaryStream("image"),
                        rs.getString("Name"),
                        rs.getString("Adress"),
                        rs.getString("Phone"),
                        rs.getString(  "Mail"),
                        rs.getString("User"),
                        rs.getString("Password")
                );
                cont++;
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }catch (IOException E){E.printStackTrace();}

        return e;
    }


    public Boolean insert(User user) {
        try {
            String query = "insert into users "
                    + " (image,Name, Adress, Phone, Mail, User, Password)"
                    + " values (? ,?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setBinaryStream(1, user.getFis(),user.getLengthBytes());
            st.setString(  2, user.getName());
            st.setString(  3, user.getAdress());
            st.setString(  4, user.getPhone());
            st.setString(  5, user.getMail());
            st.setString(  6, user.getUser());
            st.setString(  7, user.getPassword());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

   /* public Boolean update(Employee employee) {
        try {
            String query = "update employees "
                    + " set birth_date = ?, first_name = ?, last_name = ?, gender = ?, hire_date = ?"
                    + " where emp_no=?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setDate(  1, employee.getBirth_date());
            st.setString(2, employee.getFirst_name());
            st.setString(3, employee.getLast_name());
            st.setString(4, String.valueOf(employee.getGender()));
            st.setDate(  5, employee.getHire_date());
            st.setInt(   6, employee.getEmp_no());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
*/
}
