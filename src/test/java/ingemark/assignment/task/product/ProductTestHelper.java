package ingemark.assignment.task.product;

import ingemark.assignment.task.product.application.command.CreateProductCommand;
import ingemark.assignment.task.product.domain.*;
import ingemark.assignment.task.shared.id.Id;
import ingemark.assignment.task.shared.name.Name;

import java.math.BigDecimal;

public class ProductTestHelper {

    public static CreateProductCommand createProductCommand(){
        return new CreateProductCommand("Test product name", "TestCode12", BigDecimal.valueOf(12.25), true);
    }

    public static Product mockProduct(){
        return Product.builder()
                .id(Id.of("42737c47-c5f5-443c-bda4-03f2634e53b6"))
                .name(Name.of("Test name"))
                .code(Code.of("TestCode21"))
                .priceEur(PriceEur.of(BigDecimal.valueOf(10)))
                .priceUsd(PriceUsd.of(BigDecimal.valueOf(10.86)))
                .isAvailable(IsAvailable.of(true))
                .build();
    }
}