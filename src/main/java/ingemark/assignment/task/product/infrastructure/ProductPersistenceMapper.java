package ingemark.assignment.task.product.infrastructure;

import ingemark.assignment.task.product.domain.*;
import ingemark.assignment.task.shared.common.infrastructure.date.DateUtil;
import ingemark.assignment.task.shared.hnb.HnbExchangeRateResponse;
import ingemark.assignment.task.shared.hnb.HnbUtil;
import ingemark.assignment.task.shared.id.Id;
import ingemark.assignment.task.shared.name.Name;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static ingemark.assignment.task.shared.common.infrastructure.db.jooq.generated.Tables.PRODUCT;
import static java.util.Objects.isNull;


@Component
public class ProductPersistenceMapper {

    private final HnbUtil hnbUtil;

    public ProductPersistenceMapper(HnbUtil hnbUtil) {
        this.hnbUtil = hnbUtil;
    }


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
        PriceEur euroPrice = PriceEur.of(record.get(PRODUCT.PRICE_EUR));
        BigDecimal usdPrice = getUsdPrice(euroPrice.value());

        return Product.builder()
                .id(Id.of(record.get(PRODUCT.ID)))
                .name(Name.of(record.get(PRODUCT.NAME)))
                .code(Code.of(record.get(PRODUCT.CODE)))
                .priceEur(euroPrice)
                .priceUsd(PriceUsd.of(usdPrice))
                .isAvailable(IsAvailable.of(record.get(PRODUCT.IS_AVAILABLE)))
                .build();
    }

    private BigDecimal getUsdPrice(BigDecimal euroPrice) {
        if (euroPrice == null) {
            return null;
        }

        Optional<HnbExchangeRateResponse> exchangeRate = hnbUtil.getExchangeRate(DateUtil.now().toLocalDate(), "USD");
        if (exchangeRate.isEmpty()) {
            return null;
        }
        BigDecimal usdExchangeRate = exchangeRate.get().getSrednjiTecaj();
        return euroPrice.multiply(usdExchangeRate);
    }
}
