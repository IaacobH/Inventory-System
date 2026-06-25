package service;

import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    public enum Result {
        OK,
        PRODUCT_NOT_FOUND,
        INVALID_AMOUNT,
        INVALID_PRICE,
        INSUFFICIENT_STOCK,
        DUPLICATE_PRODUCT
    }

    private HashMap<String, Product> products;
    private HashMap<Integer, Category> categories;


    public void addProducts(List<Product> products) {
        for (Product p : products) {
            this.products.put(p.getName(), p);
        }
    }

    public void addCategories(List<Category> categories){
        for(Category c : categories){
            this.categories.put(c.getId(), c);
        }
    }



    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public List<Category> getAllCategories(){
        ArrayList<Category> list = new ArrayList<>(categories.values());
        return list;
    }

    public Category getCategoryById(int id) {
        return categories.get(id);
    }

    public String getCategoryNameById(int id) {
        Category category = getCategoryById(id);

        if (category == null) {
            return null;
        }

        return category.getName();
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

    public List<Product> getProductsSortedByName(){
        ArrayList<Product> list = new ArrayList<>(products.values());
        list.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return list;
    }
    public List<Product> getProductsSortedByPriceAscending(){
        ArrayList<Product> list = new ArrayList<>(products.values());
        list.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return list;
    }

    public List<Product> getProductsSortedByPriceDescending(){
        ArrayList<Product> list = new ArrayList<>(products.values());
        list.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        return list;
    }

    public List<Product> getProductsSortedByStockAscending(){
        ArrayList<Product> list = new ArrayList<>(products.values());
        list.sort((p1, p2) -> Double.compare(p1.getStock(), p2.getStock()));
        return list;

    }

    public List<Product> getProductsSortedByStockDescending(){
        ArrayList<Product> list = new ArrayList<>(products.values());
        list.sort((p1, p2) -> Double.compare(p2.getStock(), p1.getStock()));
        return list;
    }

    public List<Product> getProductsStockBelowX (int maxStock){
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : products.values()){
            if (p.getStock() < maxStock){
                list.add(p);
            }
        }
        list.sort((p1, p2) -> Double.compare(p1.getStock(), p2.getStock()));
        return list;
    }


    private boolean isInvalidAmount(int amount) {
        return amount <= 0;
    }

    public Result addProduct(String name, double price, int stock, Category category) {

        if(products.containsKey(name))return Result.DUPLICATE_PRODUCT;

        Product p = new Product(name,price,stock,category);
        products.put(p.getName(), p);
        return Result.OK;
    }

    public Result deleteProduct(String name){
        if(!products.containsKey(name))return Result.PRODUCT_NOT_FOUND;

        products.remove(name);
        return Result.OK;

    }

    public Result addStock(String name, int amount){
        if (isInvalidAmount(amount)) return Result.INVALID_AMOUNT;

        Product p = getProductOrNull(name);
        if (p==null) return Result.PRODUCT_NOT_FOUND;

        p.addStock(amount);
        return Result.OK;
    }

    public Result updatePrice(String name, double newPrice){

        if(!products.containsKey(name))return Result.PRODUCT_NOT_FOUND;
        if(newPrice <= 0 )return Result.INVALID_PRICE;

        products.get(name).setPrice(newPrice);
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


    public Product findMostExpensive(){
        if (products.isEmpty())return null;
        Product mostExpensive = null;
        for (Product p : products.values()){
            if (mostExpensive == null || p.getPrice()>mostExpensive.getPrice()){
                mostExpensive=p;
            }
        }
        return mostExpensive;
    }
}
