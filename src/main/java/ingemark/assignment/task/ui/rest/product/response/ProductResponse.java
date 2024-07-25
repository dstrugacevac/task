package ingemark.assignment.task.ui.rest.product.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductResponse {

    private UUID id;

    private String name;

    private String code;

    private BigDecimal euroPrice;

    private BigDecimal usdPrice;

    private Boolean isAvailable;
}
