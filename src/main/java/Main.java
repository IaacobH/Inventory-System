import database.SQLiteCategoryRepository;
import database.SQLiteProductRepository;
import model.Category;
import model.Product;
import repository.CategoryRepository;
import repository.JsonCategoryRepository;
import repository.JsonProductRepository;
import repository.ProductRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        service.Inventory inventory = new service.Inventory();

        //json:
//        ProductRepository productRepository = new JsonProductRepository();
//        CategoryRepository categoryRepository = new JsonCategoryRepository();
//        List<Product> products = productRepository.read();
//        List<Category> categories = categoryRepository.read();
        //sql:
        List<Product> products = SQLiteProductRepository.findAll();
        List<Category> categories = SQLiteCategoryRepository.findAll();

        inventory.addProducts(products);
        inventory.addCategories(categories);
        System.out.println(products.size() + " products loaded.");

        ui.App.run(inventory);

//        productRepository.save(inventory.getAllProducts());
//        categoryRepository.save(inventory.getAllCategories());
    }
}
