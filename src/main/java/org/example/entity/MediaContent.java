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
@Table(name = "media_content")
public class MediaContent extends AbstractEntity{

    @Column(unique = true)
    private String name;
    @Column
    @Lob
    private byte[] video;
    @Column
    @Lob
    private byte[] previewImage;
    @Column
    private String description;
//    @Column
//    private boolean isChecked;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "content")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "content")
    private List<MediaComments> comments = new ArrayList<>();

    public enum Status{
        ACCEPTED, REJECTED, UNDER_REVIEW
        }
}
