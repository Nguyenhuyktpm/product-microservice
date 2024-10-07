package org.example.productservice.Service.Imp;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.DTO.ProductCreationRequest;
import org.example.productservice.DTO.ProductResponse;
import org.example.productservice.Entity.Product;
import org.example.productservice.Entity.ProductDocument;
import org.example.productservice.Mapper.Productmaper;
import org.example.productservice.Repository.ProductRepository;
import org.example.productservice.Repository.ProductSearchRepository;
import org.example.productservice.Service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    ProductRepository productRepository;
    ProductSearchRepository productSearchRepository;
    Productmaper productmaper;

    @CacheEvict(value = "products", key = "#request.category")
    @Override
    public ProductResponse createProduct(ProductCreationRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .category(request.getCategory())
                .build();

        product = productRepository.save(product);

        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .build();

        ProductDocument productDocument = ProductDocument.builder()
                .id(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .build();


        productSearchRepository.save(productDocument);
        return productResponse;
    }

    @Cacheable(value = "products", key = "'all_products'", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<ProductResponse> getProducts() {
        return productmaper.toProductResponseList(productRepository.findAll());
    }

//    @Cacheable(value = "products", key = "'products_by_category'", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<ProductResponse> getProductbycategoryChar(String keyword) {
        List<ProductDocument> products = productSearchRepository.findByCategoryContaining(keyword);



        return products.stream().map(productDocument -> ProductResponse.builder()
                .id(productDocument.getId())
                .category(productDocument.getCategory())
                .name(productDocument.getName())
                .price(productDocument.getPrice())
                .build()).toList();
    }

    @Cacheable(value = "products", key = "'products_by_category'", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<ProductResponse> getProductsByCategoryFull(String keyword) {
        log.warn(keyword);
        return productmaper.toProductResponseList(productSearchRepository.findByCategoryWildcard(keyword));
    }

    @Cacheable(value = "products", key = "'products_by_name'", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<ProductResponse> getProductbyname(String name) {
        return productmaper.toProductResponseList(productSearchRepository.findByNameContaining(name));
    }
}