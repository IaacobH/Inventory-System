import java.util.Collection;
import java.util.List;

public class JsonRepository implements InventoryRepository{
    @Override
    public void save(Collection<Product> products) {

    }

    @Override
    public List<Product> read() {
        return List.of();
    }
}
