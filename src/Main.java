import java.util.List;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        InventoryRepository repository = new FileInventoryRepository();

        List<Product> products = repository.read();
        inventory.addProducts(products);

        App.run(inventory);

        repository.save(inventory.getAllProducts());
    }
}