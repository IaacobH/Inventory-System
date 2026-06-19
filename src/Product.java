public class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {

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
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
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
                '}';
    }

    public String toFileString(){
        return name+";"+price+";"+stock;
    }
    public static Product fromFileString(String file){
        String[] parts = file.split(";");
        return new Product(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2])
        );
    }
}
