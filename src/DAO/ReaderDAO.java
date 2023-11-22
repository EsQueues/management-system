package DAO;

import Model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {
    private final Connection conn;

    public ReaderDAO(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public int check(Reader reader) throws SQLException {
        String query = "SELECT COUNT(*) FROM Readers WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, reader.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0 ? 1 : 0;
            }
        }
        throw new RuntimeException("Error checking reader existence");
    }


    public void signUp(Reader newReader) {
        String query = "INSERT INTO Readers (name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newReader.getName());
            preparedStatement.setString(2, newReader.getEmail());
            preparedStatement.setString(3, newReader.getPassword());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    newReader.setId(generatedId);  // Set the generated ID to the newReader object
                } else {
                    throw new RuntimeException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reader> getListOfUsers() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Readers");
            ResultSet resultSet = statement.executeQuery();
            List<Reader> readers = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Reader reader = new Reader(id, name, email,password);
                readers.add(reader);
            }

            return readers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
