package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Product(String name, double price, int stock, Category category) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Product (){}

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(double price) {

        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative"
            );
        }

        this.price = price;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock = stock;
    }

    public void addStock(int amount) {
        if (amount < 0) {
            return;
        }
        this.stock += amount;
    }

    public void removeStock(int amount) {
        if (amount < 0 || amount > stock) {
            return;
        }
        stock -= amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category="+ category+
                '}';
    }

    public String toFileString(){
        return name+";"+price+";"+stock+";"+category;
    }

}
