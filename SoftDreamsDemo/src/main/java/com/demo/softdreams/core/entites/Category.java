package com.demo.softdreams.core.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "label_name")
    private String labelName;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbail")
    private String thumbail;
    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

//    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Blog> blogs = new ArrayList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Collection<Blog> blogs;

}