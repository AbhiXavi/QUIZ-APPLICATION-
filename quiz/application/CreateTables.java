package quiz.application;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/quiz";
        String username = "root";
        String password = "frgrabsa@1407";
        

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement(); 

            // Create user table
            String createUserTableSQL = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "score INT)";
            stmt.executeUpdate(createUserTableSQL);

            // Create exam1 table
//            String createExam1TableSQL = "CREATE TABLE IF NOT EXISTS exam1 ("
//                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
//                    + "question VARCHAR(255) NOT NULL,"
//                    + "option1 VARCHAR(255) NOT NULL,"
//                    + "option2 VARCHAR(255) NOT NULL,"
//                    + "option3 VARCHAR(255) NOT NULL,"
//                    + "option4 VARCHAR(255) NOT NULL,"
//                    + "correct_answer VARCHAR(255) NOT NULL)";
//            stmt.executeUpdate(createExam1TableSQL);

            //System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}