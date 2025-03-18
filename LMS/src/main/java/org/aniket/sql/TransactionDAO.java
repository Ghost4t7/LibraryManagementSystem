package org.aniket.sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionDAO {
    public static void issueBook(int UserId, int BookId) {
        String query = "INSERT INTO transactions (UserID, BookID, IssueDate, ReturnDate) VALUES (?, ?, NOW(), NULL)";
        String updateBookStatus = "UPDATE books SET status = 'Borrowed' WHERE BookId = ?";
        
        try (Connection con = LMS.dbConnect();
             PreparedStatement stmt = con.prepareStatement(query);
             PreparedStatement updateStmt = con.prepareStatement(updateBookStatus)) {

            stmt.setInt(1, UserId);
            stmt.setInt(2, BookId);
            stmt.executeUpdate();
            
            updateStmt.setInt(1, BookId);
            updateStmt.executeUpdate();
            
            LocalDate Date = LocalDate.now();
            System.out.println("Book issued successfully!");
            System.out.println("Return Date is " + Date);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void returnBook(int BookId) {
        String query = "UPDATE transactions SET ReturnDate = NOW() WHERE BookID = ? AND ReturnDate IS NULL";
        String updateBookStatus = "UPDATE books SET status = 'Available' WHERE BookID = ?";
        
        try (Connection con = LMS.dbConnect();
             PreparedStatement stmt = con.prepareStatement(query);
             PreparedStatement updateStmt = con.prepareStatement(updateBookStatus)) {

            stmt.setInt(1, BookId);
            stmt.executeUpdate();
            
            updateStmt.setInt(1, BookId);
            updateStmt.executeUpdate();
            
            System.out.println("Book returned successfully!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void overdueBooks() {
        String query = "SELECT u.Name, b.Title, t.IssueDate FROM Transactions t " +
                       "JOIN users u ON t.UserID = u.UserID " +
                       "JOIN books b ON t.BookID = b.BookID " +
                       "WHERE t.ReturnDate IS NULL AND t.IssueDate < (NOW() - INTERVAL 14 DAY)"; 
        
        try (Connection con = LMS.dbConnect();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("User: " + rs.getString("Name") +
                                   ", Book: " + rs.getString("Title") +
                                   ", Issued on: " + rs.getDate("IssueDate"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
