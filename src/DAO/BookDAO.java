package DAO;

import Adapter.DataSource;
import Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements DataSource {
    private final Connection conn;

    public BookDAO(Connection conn) throws SQLException {
        this.conn = conn;
    }


    public void deleteBook(int id)  {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Books where id=?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(Book book) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Books (title, year, author, isAvailable, genre) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getYear());
            statement.setString(3, book.getAuthor());
            statement.setBoolean(4, book.isAvailable());
            statement.setString(5, book.getGenre());

            // Execute the query
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void assign(Book book) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE Books SET readerId=?, isAvailable=? WHERE id=?");
            statement.setInt(1, book.getReaderId());
            statement.setBoolean(2, book.isAvailable());
            statement.setInt(3, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    @Override
    public List<Book> getData() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Books");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                String author = resultSet.getString("author");
                boolean isAvailable = resultSet.getBoolean("isAvailable");
                String genre = resultSet.getString("genre");
                int readerId=resultSet.getInt("readerId");

                Book book = new Book(id, title, year, author, isAvailable, genre,readerId);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void releaseBook(Book book) throws SQLException {
        PreparedStatement preparedStatement= conn.prepareStatement("UPDATE Books SET readerId=? WHERE bookId=?");
        preparedStatement.setInt(1,book.getReaderId());
        preparedStatement.setInt(2,book.getId());
        preparedStatement.executeUpdate();
    }

    public Book getBookById(int id) throws SQLException {
        String query = "SELECT * FROM Books WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Assuming you have a constructor in the Book class that takes ResultSet as a parameter
                    return new Book(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getInt("year"),
                            resultSet.getString("author"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getString("genre"),
                            resultSet.getInt("readerId"));
                }
            }
        }

        // Return null if no book with the specified ID is found
        return null;
    }

}
