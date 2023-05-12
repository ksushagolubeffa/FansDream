package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends AbstractEntity{
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;
    @Column
    private UUID contentId;
    @Column
    private UUID commentId;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Message message;

    public Notification(User user, UUID contentId, UUID commentId){
        this.commentId = commentId;
        this.user = user;
        this.contentId = contentId;
    }

    public Notification(User user, UUID contentId, Message message){
        this.message = message;
        this.user = user;
        this.contentId = contentId;
    }

    public enum Message{
        ACCEPTED, REJECTED
    }
}
