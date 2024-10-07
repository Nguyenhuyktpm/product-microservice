package org.example.productservice.Service;

import org.example.productservice.DTO.ProductCreationRequest;
import org.example.productservice.DTO.ProductResponse;
import org.example.productservice.Entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);

    List<ProductResponse> getProducts();

    List<ProductResponse> getProductbycategoryChar(String keyword);

    List<ProductResponse> getProductsByCategoryFull(String keyword);

    List<ProductResponse> getProductbyname(String name);
}
