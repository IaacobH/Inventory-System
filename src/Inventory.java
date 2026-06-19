import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    public enum Result {
        OK,
        PRODUCT_NOT_FOUND,
        INVALID_AMOUNT,
        INSUFFICIENT_STOCK,
        DUPLICATE_PRODUCT
    }

    private HashMap<String, Product> products;

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Inventory() {
        this.products = new HashMap<>();
    }

    private Product getProductOrNull(String name) {
        return products.get(name);
    }

    public Product getProduct(String name){
        return products.get(name);
    }



    private boolean isInvalidAmount(int amount) {
        return amount <= 0;
    }

    public Result addProduct(String name, double price, int stock) {

        if(products.containsKey(name))return Result.DUPLICATE_PRODUCT;

        Product p = new Product(name,price,stock);
        products.put(p.getName(), p);
        return Result.OK;
    }

    public Result addStock(String name, int amount){
        if (isInvalidAmount(amount)) return Result.INVALID_AMOUNT;

        Product p = getProductOrNull(name);
        if (p==null) return Result.PRODUCT_NOT_FOUND;

        p.addStock(amount);
        return Result.OK;
    }


    public Result removeStock(String name, int amount) {

        if (isInvalidAmount(amount)) {
            return Result.INVALID_AMOUNT;
        }

        Product p = getProductOrNull(name);
        if (p == null) {
            return Result.PRODUCT_NOT_FOUND;
        }

        if (p.getStock()<amount) {
            return Result.INSUFFICIENT_STOCK;
        }

        p.removeStock(amount);
        return Result.OK;
    }




    public ArrayList<Product> searchProduct(String text){
        ArrayList<Product> results = new ArrayList<>();
        text = text.toLowerCase();
        for (Product p : products.values()){
            if (p.getName().toLowerCase().contains(text)){
                results.add(p);
            }
        }
        return results;
    }

    public void addProducts(List<Product> products) {
        for (Product p : products) {
            this.products.put(p.getName(), p);
        }
    }
}
