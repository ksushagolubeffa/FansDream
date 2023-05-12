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
@Table(name = "comments_media")
public class MediaComments extends AbstractEntity {

    @Column
    private String text;
    @Column
    private Long mediaID;
    @Column
    private String username;
    @Column
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private MediaContent content;
    @PrePersist
    private void onCreate() { date = LocalDateTime.now(); }

}
