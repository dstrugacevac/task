package ingemark.assignment.task.product.domain;

import ingemark.assignment.task.shared.id.Id;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> save(Product product);

    Optional<Product> findById(Id id);

    List<Product> fetch(Pageable pageable);

    Long total();

    boolean isCodeExists(Code code);
}
