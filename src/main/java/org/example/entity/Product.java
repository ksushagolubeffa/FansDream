package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product extends AbstractEntity{

    @Column(unique = true)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Column
    @Lob
    private byte[] image;
    @Column
    private String imageName;
    @Column
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductComments> comments = new ArrayList<>();
}
