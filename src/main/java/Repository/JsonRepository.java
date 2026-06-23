package Repository;

import Model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collection;
import java.util.List;

public class JsonRepository implements InventoryRepository {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void save(Collection<Product> products) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("products.json"), products);
        } catch (Exception e) {
            System.out.println("Error saving JSON file");
        }
    }

    @Override
    public List<Product> read() {
        try {
            return mapper.readValue(
                    new File("products.json"),
                    new TypeReference<List<Product>>() {}
            );
        } catch (Exception e) {
            System.out.println("Error reading JSON file");
            return List.of();
        }
    }
}
