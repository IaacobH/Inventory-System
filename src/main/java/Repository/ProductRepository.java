package repository;

import model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    List<Product> read();

    void save(Collection<Product> products);
}
