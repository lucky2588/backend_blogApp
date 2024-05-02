package com.demo.softdreams.core.entites;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "blog")
public class Blog  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "view")
    private Long view = 0L;

    @Column(name = "title")
    private String title;

    @Column(name = "active")
    private String active; // public or private

    @Column(name = "image")
    private String image;

    @Column(name = "description", columnDefinition="text")
    private String description;
    @Column(name = "created_date")
    private LocalDate createdDate;

    @ManyToMany
    @JoinTable(name = "blog_categories",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Set<Category> categories = new HashSet<>();
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "content", columnDefinition="text")
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(name = "announce_method")
//    private String announceMethod;  // email || notification || all
//    @Column(name = "type")
//    private String type;
//    @Column(name = "target")
//    private String target;  // promotion || notification
    //    @Column(name = "announce_target")
//    private String announceTarget;

//    @Column(name = "announce_date")
//    private Date announceDate;






}