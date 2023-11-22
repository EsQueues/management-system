package View;

import Adapter.ListToExcelAdapter;
import Controllers.BookController;
import Controllers.NewspaperController;
import Controllers.ReaderController;
import DAO.BookDAO;
import DAO.ReaderDAO;
import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class System1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Connection connection = DatabaseConnection.getInstance().getConnection()) {

            ReaderDAO readerDAO = new ReaderDAO(connection);
            BookDAO bookDAO = new BookDAO(connection);
            BookController bookController = new BookController();
            NewspaperController newspaperController = new NewspaperController();
            ReaderController readerController = new ReaderController(sc, readerDAO, bookDAO);
            ListToExcelAdapter listToExcelAdapter = new ListToExcelAdapter();

            while (true) {
                System.out.println("Management for librarians");
                System.out.println("1.Add literature");
                System.out.println("2.Add newspaper");
                System.out.println("3.Sign up reader");
                System.out.println("4.Assign reader to book");
                System.out.println("5.Release a book");
                System.out.println("6.Give analyze to workers");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> bookController.createLiterature();
                    case 2 -> newspaperController.createLiterature();
                    case 3 -> readerController.signUp();
                    case 4 -> bookController.assignLiterature();
                    case 5 -> bookController.releaseBook();
                    case 6 -> listToExcelAdapter.exportToExcel("C:\\Users\\Lenovo\\Desktop\\libraryData.xlsx");
                    default -> System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }
}
