package sample.Models.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Manage;
import sample.Models.Task;
import sample.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO
{
    Connection conn;
    public TaskDAO(Connection conn)
    {
        this.conn = conn;
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            String query = "SELECT * FROM tasks limit 1000";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Task p = null;
            while(rs.next()) {
                p = new Task(
                        rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getInt("Estimated_Time"),
                        rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),
                        rs.getString("Tags"),
                        rs.getString("Priority").charAt(0),
                        rs.getString("Category"),
                        rs.getString("Notes"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Focus"),
                        rs.getString("User")
                );
                tasks.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return tasks;
    }

    public Boolean update(Task task) {
        try {
            String query = "update tasks "
                    + " set Title = ?, Estimated_Time = ?, StarFrom = ?, FinishBy = ?, Tags = ?, Priority = ?, Category = ?, Notes = ?, Status = ?, Focus = ? "
                    + " where id=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, task.getTitle());
            st.setInt   (  2, task.getEstimated_Time());
            st.setDate  (  3, (Date) task.getStarFrom());
            st.setDate  (  4, (Date) task.getFinishBy());
            st.setString(  5, task.getTags());
            st.setString(  6, String.valueOf(task.getPriority()));
            st.setString(  7, task.getCategory());
            st.setString(  8, task.getNotes());
            st.setBoolean( 9,task.isStatus());
            st.setBoolean(10,task.isFocus());
            st.setInt   (  11, task.getID());
            st.execute();
            System.out.println("NICE");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public ObservableList<Task> fetchAll() {
        ObservableList<Task> task = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM tasks";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Task p = null;
            while(rs.next()) {
                p = new Task(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getInt("Estimated_Time"),
                        rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),
                        rs.getString("Tags"),
                        rs.getString("Priority").charAt(0),
                        rs.getString("Category"),
                        rs.getString("Notes"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Focus"),
                        rs.getString("User")
                );
                task.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return task;
    }

    public ObservableList<Task> fetchTags(String tittle) {
        ObservableList<Task> task = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM tasks where Tittle = '" + tittle + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Task p = null;
            while(rs.next()) {
                p = new Task(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getInt("Estimated_Time"),
                        rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),
                        rs.getString("Tags"),
                        rs.getString("Priority").charAt(0),
                        rs.getString("Category"),
                        rs.getString("Notes"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Focus"),
                        rs.getString("User")
                );
                task.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return task;
    }

    public ObservableList<Task> fetchByDate(String das) {
        ObservableList<Task> task = FXCollections.observableArrayList();
        System.out.println(das+"fetchall");
        String date = "'2018-05-26'";
        try {
            String query = "SELECT * FROM tasks WHERE StarFrom LIKE "+ das;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Task p = null;
            while(rs.next()) {
                p = new Task(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getInt("Estimated_Time"),
                        rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),
                        rs.getString("Tags"),
                        rs.getString("Priority").charAt(0),
                        rs.getString("Category"),
                        rs.getString("Notes"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Focus"),
                        rs.getString("User")
                );
                task.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        System.out.println("qweqweqweqwe");
        return task;
    }

    public ObservableList<Task> fetchToday() {
        ObservableList<Task> task = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM tasks WHERE StarFrom = CAST(CURRENT_TIMESTAMP AS DATE)";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Task p = null;
            while(rs.next()) {
                p = new Task(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getInt("Estimated_Time"),
                        rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),
                        rs.getString("Tags"),
                        rs.getString("Priority").charAt(0),
                        rs.getString("Category"),
                        rs.getString("Notes"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Focus"),
                        rs.getString("User")
                );
                task.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return task;
    }

    public ObservableList<Task> fetchNext7() {
        ObservableList<Task> task = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM tasks WHERE StarFrom >= NOW() AND StarFrom <= NOW() + INTERVAL 7 DAY";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Task p = null;
            while(rs.next()) {
                p = new Task(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getInt("Estimated_Time"),
                        rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),
                        rs.getString("Tags"),
                        rs.getString("Priority").charAt(0),
                        rs.getString("Category"),
                        rs.getString("Notes"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Focus"),
                        rs.getString("User")
                );
                task.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return task;
    }


    public ObservableList<Task> fetchFocus() {
        ObservableList<Task> task = FXCollections.observableArrayList();
        System.out.println("ONFOCUSFETCH");
        try {
            String query = "SELECT * FROM tasks WHERE Focus = '1'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Task p = null;
            while(rs.next()) {
                p = new Task(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getInt("Estimated_Time"),
                        rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),
                        rs.getString("Tags"),
                        rs.getString("Priority").charAt(0),
                        rs.getString("Category"),
                        rs.getString("Notes"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Focus"),
                        rs.getString("User")
                );
                task.add(p);
            }
            rs.close();
            st.close();
            System.out.println(task.get(0).isFocus());

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return task;
    }

    public Boolean insert(Task task) {
        try {
            String query = "insert into tasks "
                    + " (Title, Estimated_Time, StarFrom, FinishBy, Tags, Priority, Category, Notes, User)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, task.getTitle());
            st.setInt   (  2, task.getEstimated_Time());
            st.setDate  (  3, (Date) task.getStarFrom());
            st.setDate  (  4, (Date) task.getFinishBy());
            st.setString(  5, task.getTags());
            st.setString(  6, String.valueOf(task.getPriority()));
            st.setString(  7, task.getCategory());
            st.setString(  8, task.getNotes());
            st.setString(9,task.getUser());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
