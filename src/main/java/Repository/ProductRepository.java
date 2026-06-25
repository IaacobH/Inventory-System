package repository;

import model.Product;
import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    void save(Collection<Product> products);
    public List<Product> read();
}
