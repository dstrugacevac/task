package ingemark.assignment.task.product.infrastructure;

import ingemark.assignment.task.product.domain.*;
import ingemark.assignment.task.shared.id.Id;
import ingemark.assignment.task.shared.name.Name;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ingemark.assignment.task.shared.common.infrastructure.db.jooq.generated.Tables.PRODUCT;
import static java.util.Objects.isNull;


@Component
public class ProductPersistenceMapper {

    public List<Product> toDomain(Record[] records) {
        if (isNull(records)) {
            return new ArrayList<>();
        }
        return Stream.of(records)
                .map(this::toDomain)
                .toList();
    }

    public Product toDomain(Record record) {
        if (record == null) {
            return null;
        }

        return Product.builder()
                .id(Id.of(record.get(PRODUCT.ID)))
                .name(Name.of(record.get(PRODUCT.NAME)))
                .code(Code.of(record.get(PRODUCT.CODE)))
                .priceEur(PriceEur.of(record.get(PRODUCT.PRICE_EUR)))
                .priceUsd(PriceUsd.of(record.get(PRODUCT.PRICE_USD)))
                .isAvailable(IsAvailable.of(record.get(PRODUCT.IS_AVAILABLE)))
                .build();
    }
}
