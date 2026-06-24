package Repository;

import Model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collection;
import java.util.List;

public class JsonRepository implements InventoryRepository {

    private static final String FILE_PATH = "products.json";
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void save(Collection<Product> products) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), products);
        } catch (Exception e) {
            System.out.println("Error saving JSON file");
        }
    }

    @Override
    public List<Product> read() {
        File file = new File(FILE_PATH);
        if(!file.exists()){
            System.out.println("No JSON file found. Starting with empty inventory.");
            return List.of();
        }

        try {
            return mapper.readValue(
                    file,
                    new TypeReference<List<Product>>() {}
            );
        } catch (Exception e) {
            System.out.println("Error reading JSON file");
            return List.of();
        }
    }
}
