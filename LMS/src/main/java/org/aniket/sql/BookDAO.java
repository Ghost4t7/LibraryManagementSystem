package org.aniket.sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    
public static void addBook(String title, String author, String isbn) {
    String query = "INSERT INTO books (Title, Author, ISBN, Status) VALUES (?, ?, ?, 'Available')";
    
    try (Connection con = LMS.dbConnect();
         PreparedStatement stmt = con.prepareStatement(query)) {

        stmt.setString(1, title);
        stmt.setString(2, author);
        stmt.setString(3, isbn);
        
        stmt.executeUpdate();
        System.out.println("Book added successfully!");
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void viewBooks() {
    String query = "SELECT * FROM books";
    
    try (Connection con = LMS.dbConnect();
    PreparedStatement stmt = con.prepareStatement(query);
    ResultSet rs = stmt.executeQuery()) {
    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("| BookID |                       Title                        |                        Author                      |      ISBN     |   Status  |");
    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    String  title, author, isbn, status;
    int bookId;
    while (rs.next()) {
        bookId = rs.getInt("BookID");
        title = rs.getString("Title");
        author = rs.getString("Author");
        isbn = rs.getString("ISBN");
        status = rs.getString("Status");
        System.out.printf("| %6d | %-50s | %-50s | %-13s | %-9s |%n", bookId, title, author, isbn, status);
    }
    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void availableBooks() {
    String query = "SELECT * FROM books where status = 'Available'";
    
    try (Connection con = LMS.dbConnect();
    PreparedStatement stmt = con.prepareStatement(query);
    ResultSet rs = stmt.executeQuery()) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| BookID |                       Title                        |                        Author                      |      ISBN     |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        String  title, author, isbn;
        int bookId;
        while (rs.next()) {
            bookId = rs.getInt("BookID");
            title = rs.getString("Title");
            author = rs.getString("Author");
            isbn = rs.getString("ISBN");
            System.out.printf("| %6d | %-50s | %-50s | %-13s |%n", bookId, title, author, isbn);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
           
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static int[] borrowedBooks(int UserID) {
int[] Books = new int[100];
int i = 0;

    String query = "SELECT b.* " + 
               "FROM Books b " + 
               "JOIN Transactions t ON b.BookID = t.BookID " + 
               "JOIN users u ON t.UserID = u.UserID " + 
               "WHERE b.Status = 'Borrowed' AND u.UserID = ?";
    
    try (Connection con = LMS.dbConnect();
    PreparedStatement stmt = con.prepareStatement(query)) {
    stmt.setInt(1, UserID);
    ResultSet rs = stmt.executeQuery();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| BookID |                       Title                        |                        Author                      |      ISBN     |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        String  title, author, isbn;
        int bookId;
        while (rs.next()) {
            bookId = rs.getInt("BookID");
            title = rs.getString("Title");
            author = rs.getString("Author");
            isbn = rs.getString("ISBN");
            System.out.printf("| %6d | %-50s | %-50s | %-13s |%n", bookId, title, author, isbn);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Books[i] = rs.getInt("BookID");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Books;
    }
}
