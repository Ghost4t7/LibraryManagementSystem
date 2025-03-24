package org.aniket.sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static int UserID = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Display.show();
        while (true) {
            System.out.println("\n==== Library Management System ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    if (loginUser()) {
                        showMenu();
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Role (Admin/User): ");
        String role = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        UserDAO.registerUser(name, email, password, role, phone);
    }

    private static boolean loginUser() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean loggedIn = UserDAO.loginUser(email, password);
        if (loggedIn) {
            System.out.println("Login successful!");


            String query = "SELECT UserID FROM users WHERE email = ? AND password = ?";
        
        try (Connection con = LMS.dbConnect();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserID = rs.getInt("UserID");
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }

        } else {
            System.out.println("Invalid credentials!");
        }
        return loggedIn;
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\n==== Library Menu ====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Overdue Books");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    BookDAO.viewBooks();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    TransactionDAO.overdueBooks();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        BookDAO.addBook(title, author, isbn);
    }

    private static void issueBook() {

        BookDAO.availableBooks();
        System.out.print("Enter Book ID: ");
        int BookID = scanner.nextInt();

        TransactionDAO.issueBook(UserID, BookID);
    }

    private static void returnBook() {
        int flag=0;
        int[] Books = new int[100];
        Books=BookDAO.borrowedBooks(UserID);
        System.out.print("Enter Book ID: ");
        int BookID = scanner.nextInt();        

        int len=Books.length;
        for(int i=0;i<len;i++)
        {
            if(BookID==Books[i]){
            TransactionDAO.returnBook(BookID);
            flag=1;
            break;
            }
        }
        if(flag==0)
        System.out.println("Invalid Choice");


        
    }
}
