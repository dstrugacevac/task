package ingemark.assignment.task.product.domain;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import ingemark.assignment.task.shared.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static ingemark.assignment.task.product.ProductTestHelper.mockProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
class ProductRepositoryTest extends BaseTest {

    private static final String BASE_DATASET_PATH = "task/product/domain";


    @Autowired
    private ProductRepository productRepository;


    @Test
    @ExpectedDataSet(value = BASE_DATASET_PATH + "/expected/single_product.yml", orderBy = "id")
    void save() {
        Optional<Product> result = productRepository.save(mockProduct());
        assertThat(result).isPresent();
    }

}