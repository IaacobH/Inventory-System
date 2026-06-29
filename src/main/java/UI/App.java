package ui;

import model.Category;
import model.Product;
import service.Inventory;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

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
                case 5 -> showAllProducts(inventory, input);
                case 6 -> searchProduct(inventory, input);
                case 7 -> showMostExpensive(inventory);
                case 8 -> showBelowXStock(inventory, input);
                case 9 -> deleteProduct(inventory, input);
                case 10 -> updateProductPrice(inventory, input);
                case 11 -> running = false;
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n===== INVENTORY SYSTEM =====");
        System.out.println("1 - Add product");
        System.out.println("2 - Add stock");
        System.out.println("3 - Remove stock");
        System.out.println("4 - Show product");
        System.out.println("5 - Show all products");
        System.out.println("6 - Search product");
        System.out.println("7 - find most expensive product");
        System.out.println("8 - show low stock products");
        System.out.println("9 - Delete product");
        System.out.println("10 - update product price");
        System.out.println("11 - save and exit");
        System.out.print("Choose option: ");
    }

    public static void addProductUI(Inventory inventory, Scanner input) {
        String name = InputUtils.getString(input, "Product name: ");
        double price = InputUtils.getDouble(input, "Price: ");
        int stock = InputUtils.getInt(input, "Stock: ");

        showCategories(inventory.getAllCategories());
        int categoryId = InputUtils.getInt(input, "select category id: ");
        Category category = inventory.getCategoryById(categoryId);

        Inventory.Result r = inventory.addProduct(name, price, stock, category);
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

    public static void updateProductPrice(Inventory inventory, Scanner input) {
        String productName = InputUtils.getString(input, "product name: ");
        System.out.println(inventory.getProduct(productName));

        double newPrice = InputUtils.getDouble(input, "new price: ");
        Inventory.Result r = inventory.updatePrice(productName, newPrice);

        printResult(r);
        System.out.println(inventory.getProduct(productName));
    }

    public static void deleteProduct(Inventory inventory, Scanner input) {
        String name = InputUtils.getString(input, "name of the product to delete: ");
        Inventory.Result r = inventory.deleteProduct(name);

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

    public static void showAllProducts(Inventory inventory, Scanner input) {
        int option = InputUtils.getInt(input, """
                                How would you like to sort the products?
                
                1 - Name
                2 - Price (Low to High)
                3 - Price (High to Low)
                4 - Stock (Low to High)
                5 - Stock (High to Low)
                
                Option: """);

        switch (option) {
            case 1 -> showProducts(inventory.getProductsSortedByName());
            case 2 -> showProducts(inventory.getProductsSortedByPriceAscending());
            case 3 -> showProducts(inventory.getProductsSortedByPriceDescending());
            case 4 -> showProducts(inventory.getProductsSortedByStockAscending());
            case 5 -> showProducts(inventory.getProductsSortedByStockDescending());
            default -> System.out.println("Invalid option");
        }
    }

    public static void searchProduct(Inventory inventory, Scanner input) {
        String text = InputUtils.getString(input, "first letters of the name: ");
        ArrayList<Product> products = inventory.searchProduct(text);

        if (products.isEmpty()) {
            System.out.println("Producto no encontrado");
        } else {
            showProducts(products);
        }
    }

    public static void showMostExpensive(Inventory inventory) {
        Product p = inventory.findMostExpensive();
        System.out.println("most expensive: " + p);
    }

    public static void showBelowXStock(Inventory inventory, Scanner input) {
        int maxStock = InputUtils.getInt(input, "show products with stock below: ");
        showProducts(inventory.getProductsStockBelowX(maxStock));
    }

    public static void showProducts(List<Product> list) {
        for (Product p : list) {
            System.out.println(p);
        }
    }

    public static void showCategories(List<Category> categories) {
        for (Category c : categories) {
            System.out.println(c);
        }
    }

    public static void printResult(Inventory.Result r) {
        switch (r) {
            case OK -> System.out.println("accion ejecutada con exito");
            case PRODUCT_NOT_FOUND -> System.out.println("Producto no existe");
            case INVALID_AMOUNT -> System.out.println("Cantidad inválida");
            case INVALID_PRICE -> System.out.println("invalid price");
            case INVALID_STOCK -> System.out.println("invalid stock");
            case INSUFFICIENT_STOCK -> System.out.println("stock insuficiente");
            case DUPLICATE_PRODUCT -> System.out.println("el producto ya existe");
            case CATEGORY_NOT_FOUND -> System.out.println("categoria invalida");
        }
    }
}
