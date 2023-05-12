package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments_product")
public class ProductComments extends AbstractEntity{

    @Column
    private String text;
    @Column
    private Long productID;
    @Column
    private LocalDateTime date;
    @Column
    private String username;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private Product product;

    @PrePersist
    private void onCreate() { date = LocalDateTime.now(); }
}
