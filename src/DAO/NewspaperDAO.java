package DAO;

import Model.Newspaper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewspaperDAO {

    private Connection conn;

    public NewspaperDAO(Connection conn) {
        this.conn = conn;
    }

    public void deleteNewspaper(Newspaper newspaper) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM newspapers WHERE id = ?");
            statement.setInt(1, newspaper.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewspaper(Newspaper newspaper) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO newspapers (title, year, publisher, available) VALUES (?, ?, ?, ?)");
            statement.setString(1, newspaper.getTitle());
            statement.setInt(2, newspaper.getYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnAllList() {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id, title, year FROM newspapers");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");

                System.out.println("Newspaper ID: " + id);
                System.out.println("Title: " + title);
                System.out.println("Year: " + year);
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
