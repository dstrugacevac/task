package ingemark.assignment.task.product.application.command;

import java.math.BigDecimal;

public record CreateProductCommand(String name, String code, BigDecimal euroPrice, Boolean isAvailable) {
}
