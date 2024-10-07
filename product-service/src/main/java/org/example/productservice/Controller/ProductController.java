package org.example.productservice.Controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.productservice.DTO.ProductCreationRequest;
import org.example.productservice.DTO.ProductResponse;
import org.example.productservice.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ProductController {
    ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreationRequest request){
        return ResponseEntity.ok().body(productService.createProduct(request));
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/char")
    public ResponseEntity<List<ProductResponse>> getAllProductsByCategoryChar(@RequestParam String keyword){
        return ResponseEntity.ok().body(productService.getProductbycategoryChar(keyword));
    }

//    @GetMapping("/word")
//    public ResponseEntity<List<ProductResponse>> getAllProductsByCategoryFull(@RequestParam String keyword){
//        return ResponseEntity.ok().body(productService.getProductsByCategoryFull(keyword));
//    }
}
