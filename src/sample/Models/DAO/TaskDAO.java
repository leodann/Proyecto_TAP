package sample.Models.DAO;

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
                p = new Task(rs.getString("title"),rs.getInt("Estimated_Time"),rs.getDate("StarFrom"),
                        rs.getDate("FinishBy"),rs.getString("Tags"),rs.getString("Priority").charAt(0),
                        rs.getString("Category"),rs.getString("Notes")
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


    /* public ObservableList<Employee> fetchAll() {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM employees limit 1000";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Employee p = null;
            while(rs.next()) {
                p = new Employee(
                        rs.getInt("emp_no"), rs.getDate("birth_date"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("gender").charAt(0), rs.getDate("hire_date")
                );
                employees.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return employees;
    }

    public Employee fetch(String no_emp) {
        ResultSet rs = null;
        Employee e = null;
        try {
            String query = "SELECT * FROM employees where emp_no = '" + no_emp + "'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            e = new Employee(
                    rs.getInt("no_emp"), rs.getDate("birth_date"),
                    rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("gender").charAt(0), rs.getDate("hire_date")
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public Boolean delete(int no_employee) {
        try {
            String query = "delete from employees where emp_no = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, no_employee);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
*/
    public Boolean insert(Task task) {
        try {
            String query = "insert into tasks "
                    + " (Title, Estimated_Time, StarFrom, FinishBy, Tags, Priority, Category)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, task.getTitle());
            st.setInt(2, task.getEstimated_Time());

            st.setDate(   3, (Date) task.getStarFrom());
            st.setDate(4, (Date) task.getFinishBy());
            st.setString(5, task.getTags());
            st.setString(  6, String.valueOf(task.getPriority()));
            st.setString(7,task.getCategory());
            st.setString(8,task.getNotes());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
