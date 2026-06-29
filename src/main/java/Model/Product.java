package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Product() {
    }

    public Product(int id, String name, double price, int stock, Category category) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

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

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.stock = stock;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String toFileString() {
        return name + ";" + price + ";" + stock + ";" + category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category=" + (category == null ? "No category" : category.getName()) +
                '}';
    }
}
