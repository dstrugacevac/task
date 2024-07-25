package ingemark.assignment.task.ui.rest.product.controller;

import ingemark.assignment.task.product.application.FetchProductsCommandHandler;
import ingemark.assignment.task.ui.rest.product.mapper.ProductUIMapper;
import ingemark.assignment.task.ui.rest.product.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class FetchProductsController {
    private final FetchProductsCommandHandler commandHandler;
    private final ProductUIMapper mapper;

    public FetchProductsController(FetchProductsCommandHandler commandHandler, ProductUIMapper mapper) {
        this.commandHandler = commandHandler;
        this.mapper = mapper;
    }

    @Operation(tags = "Product", summary = "Fetch Products", method = "GET")
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> fetch(@PageableDefault(value = 2_000) Pageable pageable) {

        var result = commandHandler.handle(mapper.toCommand(pageable));

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toResponse(result, pageable));
    }

}
