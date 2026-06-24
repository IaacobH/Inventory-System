import Model.Product;
import UI.InputUtils;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Repository.InventoryRepository chooseRepository(Scanner input) {
        while (true) {
            System.out.println("\n===== STORAGE TYPE =====");
            System.out.println("1 - JSON file");
            System.out.println("2 - TXT file");

            int option = InputUtils.getInt(input, "Choose storage option: ");

            switch (option) {
                case 1 -> {
                    return new Repository.JsonRepository();
                }
                case 2 -> {
                    return new Repository.FileInventoryRepository();
                }
                default -> System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Service.Inventory inventory = new Service.Inventory();

        Repository.InventoryRepository repository = chooseRepository(input);

        List<Product> products = repository.read();
        inventory.addProducts(products);

        UI.App.run(inventory);

        repository.save(inventory.getAllProducts());
    }
}
