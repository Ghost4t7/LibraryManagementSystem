package org.aniket.sql;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author aniket
 */
public class LMS {
    private static Connection con =null;
    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraymanagementsystem", "root", "Admin");
            System.out.println("connection established.....");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    return con;
    }
}
