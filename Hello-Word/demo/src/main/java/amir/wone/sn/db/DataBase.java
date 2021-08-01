package amir.wone.sn.db;

import amir.wone.sn.models.Etudiant;
import amir.wone.sn.models.EtudiantSportif;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataBase {
    protected static String url = "jdbc:postgresql://localhost:5432/gstudent";
    protected static String user = "odoo";
    protected static String password = "odoo";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        return DriverManager.getConnection(url, props);
    }

    public static ArrayList<Etudiant> getStudents() throws SQLException{
        ArrayList<Etudiant> studentList = new ArrayList<Etudiant>();
        String query = "SELECT * FROM student";
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Etudiant etudiant = new Etudiant(rs.getInt("id"), rs.getString("name"), rs.getString("first_name"),
                    rs.getString("email"), rs.getString("address"), rs.getString("date_of_birth"));
            studentList.add(etudiant);
        }
        rs.close();
        preparedStatement.close();
        return studentList;
    }

    public static ArrayList<Etudiant> getAtleticStudents() throws SQLException{
        ArrayList<Etudiant> studentSportif = new ArrayList<>();
        String query = "SELECT * FROM student INNER JOIN student_sportif ON student.id = student_sportif.student_id";
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            EtudiantSportif etudiantSportif = new EtudiantSportif(rs.getInt("id"), rs.getString("name"), rs.getString("first_name"),
                    rs.getString("email"), rs.getString("address"), rs.getString("date_of_birth"), rs.getString("sport_name"));
            studentSportif.add(etudiantSportif);
        }
        rs.close();
        preparedStatement.close();
        return studentSportif;
    }

}
