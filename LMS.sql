CREATE DATABASE libraymanagementsystem;
USE libraymanagementsystem;

CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(50) NOT NULL,
    Email VARCHAR(40) UNIQUE NOT NULL,
    Password VARCHAR(25) NOT NULL,
    Role ENUM('Admin', 'User', 'Other') NOT NULL,
    Phone VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE Books (
    BookID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(50) NOT NULL,
    Author VARCHAR(50) NOT NULL,
    Status ENUM('Available','Borrowed') DEFAULT 'Available',
    ISBN Varchar(50) Unique
);

CREATE TABLE Transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    BookID INT,
	IssueDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ReturnDate TIMESTAMP NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

INSERT INTO books (BookId, Title, Author, Status, ISBN) VALUES
(2, 'Harry Potter and the Sorcerer’s Stone', 'J.K. Rowling', 'Available', '9780747532699'),
(3, 'The Hobbit', 'J.R.R. Tolkien', 'Borrowed', '9780261103283'),
(4, 'To Kill a Mockingbird', 'Harper Lee', 'Available', '9780061120084'),
(5, '1984', 'George Orwell', 'Borrowed', '9780451524935'),
(6, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Available', '9780743273565'),
(7, 'Moby Dick', 'Herman Melville', 'Available', '9781503280786'),
(8, 'Pride and Prejudice', 'Jane Austen', 'Borrowed', '9781503290563'),
(9, 'The Catcher in the Rye', 'J.D. Salinger', 'Available', '9780316769488'),
(10, 'The Lord of the Rings', 'J.R.R. Tolkien', 'Available', '9780544003415'),
(11, 'The Alchemist', 'Paulo Coelho', 'Borrowed', '9780061122415'),
(12, 'Life of Pi' , 'Yann Martel' , 'Available' , '9780857865533');

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO users (UserID, Name, Email, Password, Role, Phone) VALUES
(1, 'Aniket Paul', 'aniketpaul2k20@gmail.com', 'pass1234', 'Admin', '8012456578'),
(2, 'Sayak Das', 'sayakdas2021@gmail.com', 'sadsad45', 'User', '1234567890'),
(3, 'Sudip Kumar', 'sudipkr456@yahoomail.com', 'sudip456', 'User', '4567891230'),
(4, 'Mehuli Basu', 'mehulibasu123@gmail.com', 'meh12meh', 'User', '4561237890'),
(5, 'Sumon Kumar', 'sumonkumar78@gmail.com', 'sumonkumar78', 'User', '8974561230');

INSERT INTO transactions (TransactionID, UserID, BookID, IssueDate, ReturnDate) VALUES
(1, 2, 1, '2025-03-14 23:15:22', NULL),
(2, 4, 11, '2025-03-16 20:58:10', '2025-03-16 21:49:31'),
(3, 4, 6, '2025-03-16 21:03:51', NULL),
(4, 2, 3, '2025-03-17 02:38:19', NULL),
(5, 5, 5, '2025-03-17 10:19:18', NULL);

SET FOREIGN_KEY_CHECKS=1;
