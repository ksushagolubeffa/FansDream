package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDTO {
    private Long orderID;
    private String productName;
    private Integer productPrice;
}
