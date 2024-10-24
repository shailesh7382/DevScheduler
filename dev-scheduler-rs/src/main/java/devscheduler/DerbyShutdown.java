package devscheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyShutdown {
    public static void main(String[] args) {
        try {
            // Shutdown the Derby database
            DriverManager.getConnection("jdbc:derby:/Users/shailesh/IdeaProjects/DevScheduler/data/devdb;shutdown=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("XJ015")) {
                System.out.println("Derby database shut down normally.");
            } else {
                e.printStackTrace();
            }
        }
    }
}