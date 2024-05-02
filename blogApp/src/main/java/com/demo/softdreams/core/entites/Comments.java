package com.demo.softdreams.core.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "comments")
public class Comments{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "target_name")
    private String targetName;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;


    @PrePersist
    void saveCreateDate(){
        this.createdDate = LocalDateTime.now();
        this.deleted = false;
    }



}