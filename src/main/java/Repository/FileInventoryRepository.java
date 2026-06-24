package Repository;
import Model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileInventoryRepository implements InventoryRepository {

    private static final String FILE_PATH = "products.txt";

    @Override
    public void save(Collection<Product> products) {

        try (FileWriter fw = new FileWriter(FILE_PATH)){
            for (Product p : products) {
                fw.write(p.toFileString());
                fw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar");
        }
    }

    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No TXT file found. Starting with empty inventory.");
            return List.of();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {
                try {
                    Product p = Product.fromFileString(line);
                    products.add(p);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer archivo");
        }

        return products;
    }
}
