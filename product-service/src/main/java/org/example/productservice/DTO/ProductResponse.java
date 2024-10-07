package org.example.productservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse implements Serializable {
    Long id;
    String name;
    String category;
    double price;
}
