package database;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLiteCategoryRepository {
    public static List<Category> findAll(){
        List<Category> categories = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = """
                    SELECT * FROM categories
                    """;
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");

                    Category category = new Category(id, name);

                    categories.add(category);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}
