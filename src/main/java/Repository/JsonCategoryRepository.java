package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Category;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JsonCategoryRepository implements CategoryRepository {

    private static final String FILE_PATH = "categories.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Category> read() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("No JSON file found. Starting with empty inventory.");
            return List.of();
        }

        try {
            return objectMapper.readValue(
                    file,
                    new TypeReference<List<Category>>() {}
            );
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public void save(Collection<Category> categories) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), categories);
        } catch (IOException e) {
            System.out.println("Error saving JSON file: " + e.getMessage());
        }
    }
}
