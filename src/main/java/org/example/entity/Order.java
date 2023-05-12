package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order extends AbstractEntity{

    @Column
    private UUID userID;
    @Column
    private UUID productID;
    @Column
    private boolean isExecuted;
    @Column
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    public Order(UUID userID, UUID productID, boolean isExecuted){
        this.userID = userID;
        this.productID = productID;
        this.isExecuted = isExecuted;
    }

    @PrePersist
    private void onCreate() { date = LocalDateTime.now(); }
}
