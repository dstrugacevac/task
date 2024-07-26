package ingemark.assignment.task.product.infrastructure;

import ingemark.assignment.task.product.domain.Code;
import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.product.domain.ProductRepository;
import ingemark.assignment.task.shared.common.infrastructure.date.DateUtil;
import ingemark.assignment.task.shared.id.Id;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ingemark.assignment.task.shared.common.infrastructure.db.jooq.generated.Tables.PRODUCT;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final DSLContext dslContext;

    private final ProductPersistenceMapper mapper;
    private final List<TableField> PRODUCT_SELECT_FIELDS = List.of(
            PRODUCT.ID, PRODUCT.NAME, PRODUCT.CODE, PRODUCT.PRICE_EUR, PRODUCT.IS_AVAILABLE
    );

    public ProductRepositoryImpl(DSLContext dslContext, ProductPersistenceMapper mapper) {
        this.dslContext = dslContext;
        this.mapper = mapper;
    }

    @Override
    public Optional<Product> save(Product product) {
        Record savedRecord = dslContext.insertInto(PRODUCT)
                .columns(PRODUCT.ID, PRODUCT.NAME, PRODUCT.CODE, PRODUCT.PRICE_EUR,
                        PRODUCT.IS_AVAILABLE, PRODUCT.CREATED_AT, PRODUCT.UPDATED_AT)
                .values(product.getId().id(), product.getName().value(), product.getCode().value(),
                        product.getPriceEur().value(), product.getIsAvailable().value(),
                        DateUtil.now(), DateUtil.now())
                .returningResult(PRODUCT.ID)
                .fetchOne();

        if (savedRecord == null) {
            return Optional.empty();
        }

        UUID savedRecordId = savedRecord.get(PRODUCT.ID);

        return findById(Id.of(savedRecordId));
    }

    @Override
    public Optional<Product> findById(Id id) {
        Record record = dslContext.select(PRODUCT_SELECT_FIELDS)
                .from(PRODUCT)
                .where(PRODUCT.ID.eq(id.id()))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(mapper.toDomain(record));
    }

    @Override
    public List<Product> fetch(Pageable pageable) {
        // Na ovu metodu se dodaje i filter i search opcija
        Record[] records = dslContext.select(PRODUCT_SELECT_FIELDS)
                .from(PRODUCT)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchArray();

        return mapper.toDomain(records);
    }

    @Override
    public Long total() {
        // Ovdje se primjenjuje isti filter i search opcija kao i kod fetcha()
        return dslContext.selectCount()
                .from(PRODUCT)
                .where(PRODUCT.DELETED_AT.isNull())
                .fetchOne(0, Long.class);
    }

    @Override
    public boolean isCodeExists(Code code) {
        Long count = dslContext.selectCount()
                .from(PRODUCT)
                .where(PRODUCT.CODE.eq(code.value()))
                .limit(1)
                .fetchOne(0, Long.class);

        return count == 1;
    }
}
