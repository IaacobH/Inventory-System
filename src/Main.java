import java.util.List;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        InventoryRepository repository = new InventoryRepository();

        List<Product> products = repository.loadFromFile();
        inventory.addProducts(products);

        App.run(inventory);

        repository.saveToFile(inventory.getAllProducts());
    }
}