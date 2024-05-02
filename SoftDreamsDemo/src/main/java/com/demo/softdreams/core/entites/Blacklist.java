package com.demo.softdreams.core.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "blacklist")
public class Blacklist extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "note")
    private String note;
    @Column(name = "reason")
    private String reason;
    @Column(name = "tag")
    private String tag;
    @Column(name = "active")
    private Boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;




}