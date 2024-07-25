package ingemark.assignment.task.ui.rest.product.controller;

import ingemark.assignment.task.product.application.CreateProductCommandHandler;
import ingemark.assignment.task.product.domain.Product;
import ingemark.assignment.task.shared.common.application.result.CreateCommandResult;
import ingemark.assignment.task.ui.rest.product.mapper.ProductUIMapper;
import ingemark.assignment.task.ui.rest.product.request.CreateProductRequest;
import ingemark.assignment.task.ui.rest.product.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class CreateProductController {

    private final CreateProductCommandHandler commandHandler;
    private final ProductUIMapper mapper;

    public CreateProductController(CreateProductCommandHandler commandHandler, ProductUIMapper mapper) {
        this.commandHandler = commandHandler;
        this.mapper = mapper;
    }

    @Operation(tags = "Product", summary = "Create Product", method = "POST")
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest request) {

        CreateCommandResult<Product> result = commandHandler.handle(mapper.toCommand(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toResponse(result.model()));
    }

}
