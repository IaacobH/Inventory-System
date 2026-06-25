package repository;

import model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JsonRepository implements InventoryRepository {

    private static final String FILE_PATH = "products.json";
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void save(Collection<Product> products) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), products);
        } catch (IOException e) {
            System.out.println("Error saving JSON file: "+e.getMessage());
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
            return objectMapper.readValue(
                    file,
                    new TypeReference<List<Product>>() {}
            );
        } catch (IOException e) {
            System.out.println("Error reading JSON file: "+ e.getMessage());
            return List.of();
        }
    }
}
