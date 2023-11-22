package Controllers;

import DAO.BookDAO;
import DAO.ReaderDAO;
import Model.Reader;
import NotificationSystem.Decorator.TimeStampDecorator;
import NotificationSystem.Observer.LibraryNotificationSystem;
import NotificationSystem.Observer.UserObserver;

import java.sql.SQLException;
import java.util.Scanner;

public class ReaderController {
    private final Scanner sc;
    private final ReaderDAO readerDAO;
    private final BookDAO bookDAO;
    private Reader reader;

    public ReaderController(Scanner sc, ReaderDAO readerDAO, BookDAO bookDAO) {
        this.sc = sc;
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
    }

    public synchronized Reader signIn() throws SQLException {
        System.out.println("Enter your email");
        String email = sc.next();

        System.out.println("Enter your password");
        String password = sc.next();

        reader = new Reader(email, password);
        if (readerDAO.check(reader) == 1) {
            System.out.println("Successfully signed in");
        } else {
            System.out.println("Error! Not correct email or password! Try again");
        }
        return reader;
    }

    public synchronized void signUp() {
        LibraryNotificationSystem libraryNotificationSystem = new LibraryNotificationSystem();
        System.out.println("Enter your name: ");
        String name = sc.next();

        System.out.println("Enter your email: ");
        String email = sc.next();

        System.out.println("Enter new password: ");
        String password = sc.next();

        reader = new Reader(name, email, password);

        System.out.println("Do you want to get notifications about new releases (Y/N)?");
        String yOrN = sc.next();
        if (yOrN.equals("Y")) {
            System.out.println("Would you like to get messages with real date and time?");
            yOrN = sc.next();
            if (yOrN.equals("Y")) {
                UserObserver timestampedObserver = new TimeStampDecorator(reader);
                libraryNotificationSystem.addObserver(timestampedObserver);
            } else {
                libraryNotificationSystem.addObserver(reader);
            }
        }
        readerDAO.signUp(reader);
    }

    public synchronized void removeUser() {
        // Implementation for removing a user
    }
}
