package org.example.socialse2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @ManyToOne
    //    @JoinColumn(name = "user_id")
    //    private User author;

    @Column(nullable = false)
    private String title;

    private String url;

    private String imageUrl;

    @Lob
    @Column(nullable = false, length = 65535)
    private String content;

    private String shortDescription;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
