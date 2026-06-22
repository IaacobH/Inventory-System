import Model.Product;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Product arroz = new Product("arroz",50,10);



        Service.Inventory inventory = new Service.Inventory();
        Repository.InventoryRepository repository = new Repository.FileInventoryRepository();

        List<Product> products = repository.read();
        inventory.addProducts(products);

        UI.App.run(inventory);

        repository.save(inventory.getAllProducts());
    }
}