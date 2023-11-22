package Controllers;

import DAO.BookDAO;
import Model.Book;
import Model.Reader;
import NotificationSystem.Observer.LibraryNotificationSystem;
import NotificationSystem.Strategy.NotificationType;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookController implements LiteratureController {
    private Scanner sc = new Scanner(System.in);
    private BookDAO bookDAO;
    private Book book;
    private ReaderController readerController;
    private LibraryNotificationSystem libraryNotificationSystem;

    @Override
    public void createLiterature() {
        System.out.println("Enter the title of the book:");
        String title = sc.next();

        System.out.println("Enter the year of the release: ");
        int year = sc.nextInt();

        System.out.println("Enter the author of the book: ");
        String author = sc.next();

        System.out.println("Enter the genre of the book: ");
        String genre = sc.next();

        book = new Book(title, year, author, true, genre);

        bookDAO.addBook(book);
        libraryNotificationSystem.notifyObservers("Hi, we have a message for you! \nAdded new book: " + book.toString(), NotificationType.NEW_BOOK);
    }



    public void releaseBook() throws SQLException {
        returnAllBook();
        System.out.println("Input id:");
        int id = sc.nextInt();

        try {
            book = bookDAO.getBookById(id);
            bookDAO.releaseBook(book);
            libraryNotificationSystem.notifyObservers("Just released " + book.toString(), NotificationType.RELEASED_BOOK);
        } catch (SQLException e) {
            System.out.println("Error releasing the book: " + e.getMessage());
        }
    }

    public void returnAllBook() {
        List<Book> list = bookDAO.getData();
        for (Book book1 : list) {
            System.out.println(book1.toString());
        }
    }

    public void assignLiterature() throws SQLException {
        returnAllBook();
        System.out.println("Input the id of literature: ");
        int id = sc.nextInt();

        try {
            book = bookDAO.getBookById(id);
            Reader reader = readerController.signIn();
            book.setAvailable(false);
            book.setReaderId(reader.getId());
            bookDAO.assign(book);
        } catch (SQLException e) {
            System.out.println("Error assigning the book: " + e.getMessage());
        }
    }
}
