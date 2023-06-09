package csis3275.project.seasell.product;

import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.service.ProductService;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/client/products")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProductsExceptCurrentUser();
    }

    @GetMapping("/latest")
    public List<ProductDto> getLatestProducts() {
        return productService.getLatestProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestParam("name") String name,
            @RequestParam("description") String description, @RequestParam("condition") String condition,
            @RequestParam("price") Double price, @RequestParam("images") MultipartFile file) throws IOException {
        productService.addProduct(name, description, price, condition, file);
        return ResponseEntity.status(201).build();
    }
}
