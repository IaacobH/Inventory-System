package Repository;

import Model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileInventoryRepository implements InventoryRepository {
    @Override
    public void save(Collection<Product> products) {

        try {
            FileWriter fw = new FileWriter("products.txt");

            for (Product p : products) {
                fw.write(p.toFileString());
                fw.write("\n");
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Error al guardar");
        }
    }

    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                Product p = Product.fromFileString(line);
                products.add(p);
            }

        } catch (IOException e) {
            System.out.println("Error al leer archivo");
        }

        return products;
    }
}
