package database;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLiteProductRepository {

    public static int save(Product product){
        try(Connection connection = DatabaseConnection.getConnection()){

            String sql = """
                INSERT INTO products (name, price, stock, category_id)
                VALUES (?, ?, ?, ?)
                """;

            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.setInt(3, product.getStock());
                statement.setInt(4, product.getCategory().getId());

                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }

                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static List<Product> findAll(){
        List<Product> products = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = """

                    SELECT
                            products.id AS productId,
                            products.name AS productName,
                            products.price,
                            products.stock,
                            categories.id AS category_id,
                            categories.name AS category_name
                        FROM products
                        JOIN categories
                        ON products.category_id = categories.id               
                    """;
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int productId = resultSet.getInt("productId");
                    String name = resultSet.getString("productName");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");
                    int categoryId = resultSet.getInt("category_id");
                    String categoryName = resultSet.getString("category_name");

                    Category category = new Category(categoryId, categoryName);

                    Product product = new Product(productId, name, price, stock, category);

                    products.add(product);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public static int getProductId (String name){
        try(Connection connection = DatabaseConnection.getConnection()){

            String sql = """
                SELECT id
                FROM products 
                    WHERE name = ?
                """;

            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setString(1, name);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt("id");
                    return id;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public static Product findById(int id) {

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = """
                    
                        SELECT
                            products.id AS productId,
                            products.name AS productName,
                            products.price,
                            products.stock,
                            categories.id AS category_id,
                            categories.name AS category_name
                        FROM products
                        JOIN categories
                        ON products.category_id = categories.id
                        WHERE products.id = ?;
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int productId = resultSet.getInt("productId");
                    String name = resultSet.getString("productName");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");
                    int categoryId = resultSet.getInt("category_id");
                    String categoryName = resultSet.getString("category_name");

                    Category category = new Category(categoryId, categoryName);

                    return new Product(productId, name, price, stock, category);
                }
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static void updateStockById(int id, int amount){
        try(Connection connection = DatabaseConnection.getConnection()){

            String sql = """
                UPDATE products 
                    SET stock = (stock - ?)
                    WHERE id = ?
                """;

            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setInt(1, amount);
                statement.setInt(2, id);

                statement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updatePriceById(int id, double newPrice){
        try(Connection connection = DatabaseConnection.getConnection()){

            String sql = """
                UPDATE products 
                    SET price = ?
                    WHERE id = ?
                """;

            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setDouble(1, newPrice);
                statement.setInt(2, id);

                statement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteById(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = """
                    DELETE FROM products WHERE id = ?
                    """;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
