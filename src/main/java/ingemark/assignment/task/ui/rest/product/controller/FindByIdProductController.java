package ingemark.assignment.task.ui.rest.product.controller;

import ingemark.assignment.task.product.application.FindByIdProductCommandHandler;
import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.shared.common.application.result.FindByIdCommandResult;
import ingemark.assignment.task.ui.rest.product.mapper.ProductUIMapper;
import ingemark.assignment.task.ui.rest.product.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/products")
public class FindByIdProductController {

    private final FindByIdProductCommandHandler commandHandler;
    private final ProductUIMapper mapper;

    public FindByIdProductController(FindByIdProductCommandHandler commandHandler, ProductUIMapper mapper) {
        this.commandHandler = commandHandler;
        this.mapper = mapper;
    }

    @Operation(tags = "Product", summary = "Find Product by ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") UUID id) {

        FindByIdCommandResult<Product> result = commandHandler.handle(mapper.toCommand(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toResponse(result.model()));
    }

}
