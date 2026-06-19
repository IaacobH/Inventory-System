import java.util.ArrayList;
import java.util.Scanner;
public class App {

    public static void showMenu() {
        System.out.println("\n===== INVENTORY SYSTEM =====");
        System.out.println("1 - Add product");
        System.out.println("2 - Add stock");
        System.out.println("3 - Remove stock");
        System.out.println("4 - Show product");
        System.out.println("5 - Show all products");
        System.out.println("6 - Search product");
        System.out.println("7 - find most expensive product");
        System.out.println("8 - Save & exit");
        System.out.print("Choose option: ");
    }

    public static void printResult(Inventory.Result r){
        switch (r) {
            case OK -> System.out.println("Stock actualizado");
            case DUPLICATE_PRODUCT -> System.out.println("el producto ya existe");
            case PRODUCT_NOT_FOUND -> System.out.println("Producto no existe");
            case INVALID_AMOUNT -> System.out.println("Cantidad inválida");
            case INSUFFICIENT_STOCK -> System.out.println("stock insuficiente");
        }
    }

    public static void addProductUI(Inventory inventory, Scanner input) {
        String name = InputUtils.getString(input, "Product name: ");
        double price = InputUtils.getDouble(input, "Price: ");
        int stock = InputUtils.getInt(input, "Stock: ");

        Inventory.Result r =inventory.addProduct(name, price, stock);

        printResult(r);
    }

    public static void addStockUI(Inventory inventory, Scanner input) {
        String name = InputUtils.getString(input, "Product name: ");
        int amount = InputUtils.getInt(input, "Amount: ");

        Inventory.Result r = inventory.addStock(name, amount);

        printResult(r);

    }

    public static void removeStockUI(Inventory inventory, Scanner input) {
        String name = InputUtils.getString(input, "Product name: ");
        int amount = InputUtils.getInt(input, "Amount: ");
        Inventory.Result r = inventory.removeStock(name, amount);
        printResult(r);
    }

    public static void showProductUI(Inventory inventory, Scanner input) {
        String name = InputUtils.getString(input, "Product name: ");
        Product p = inventory.getProduct(name);

        if (p == null) {
            System.out.println("Producto no encontrado");
        } else {
            System.out.println(p);
        }
    }

    public static void searchProduct(Inventory inventory, Scanner input){
        String text = InputUtils.getString(input, "first letters of the name: ");
        ArrayList<Product> products = inventory.searchProduct(text);

        if (products.isEmpty()) {
            System.out.println("Producto no encontrado");
        } else {
            for (Product p : products){
                System.out.println(p);
            }
        }
    }

    public static void showAllProducts(Inventory inventory){
        for (Product p : inventory.getAllProducts()) {
            System.out.println(p);
        }
    }

    public static void showMostExpensive(Inventory inventory){
        Product p = inventory.findMostExpensive();
        System.out.println("most expensive: "+p);
    }

    public static void run(Inventory inventory) {

        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {

            showMenu();
            int option = InputUtils.getInt(input, "");

            switch (option) {
                case 1 -> addProductUI(inventory, input);
                case 2 -> addStockUI(inventory, input);
                case 3 -> removeStockUI(inventory, input);
                case 4 -> showProductUI(inventory, input);
                case 5 -> showAllProducts(inventory);
                case 6 -> searchProduct(inventory, input);
                case 7 -> showMostExpensive(inventory);
                case 8 -> running = false;
            }
        }
    }
}
