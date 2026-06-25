package repository;

import model.Category;
import model.Product;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository {
    void save(Collection<Category> categories);
    public List<Category> read();
}
