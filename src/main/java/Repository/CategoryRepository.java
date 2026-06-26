package repository;

import model.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository {
    List<Category> read();

    void save(Collection<Category> categories);
}
