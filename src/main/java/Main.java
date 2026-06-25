import model.Product;
import ui.InputUtils;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static repository.InventoryRepository chooseRepository(Scanner input) {
        while (true) {
            System.out.println("\n===== STORAGE TYPE =====");
            System.out.println("1 - JSON file");
            System.out.println("2 - TXT file");

            int option = InputUtils.getInt(input, "Choose storage option: ");

            switch (option) {
                case 1 -> {
                    return new repository.JsonRepository();
                }
                case 2 -> {
                    return new repository.FileInventoryRepository();
                }
                default -> System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        service.Inventory inventory = new service.Inventory();

        repository.InventoryRepository repository = chooseRepository(input);

        List<Product> products = repository.read();
        inventory.addProducts(products);
        System.out.println(products.size() + " products loaded.");

        ui.App.run(inventory);

        repository.save(inventory.getAllProducts());
    }
}
