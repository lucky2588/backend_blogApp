package com.demo.softdreams.core.entites;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Favorites")
@Builder
@org.hibernate.annotations.Cache(region = "favorites", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Favorites implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "userId")
    private Long userId;


    @Column(name = "target")
    private Long targetBlog;


}
