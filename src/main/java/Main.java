import Model.Product;
import UI.InputUtils;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Service.Inventory inventory = new Service.Inventory();
        Repository.InventoryRepository repository = null;

        boolean validOption = false;
        while(!validOption) {
            System.out.println("\n===== STORAGE TYPE =====");
            System.out.println("1 - JSON file");
            System.out.println("2 - TXT file");
            int option = InputUtils.getInt(input, "Choose storage option: ");

            switch (option) {
                case 1 -> {
                    repository = new Repository.JsonRepository();
                    validOption=true;
                }
                case 2 -> {
                    repository = new Repository.FileInventoryRepository();
                    validOption=true;
                }
                default -> System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }

        List<Product> products = repository.read();
        inventory.addProducts(products);

        UI.App.run(inventory);

        repository.save(inventory.getAllProducts());
    }
}
