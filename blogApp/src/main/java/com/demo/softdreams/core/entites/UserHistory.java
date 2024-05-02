package com.demo.softdreams.core.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_history")
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "target_type")
    private String targetType;

    @Column(name = "target_name")
    private String targetName;

    @Column(name = "action")
    private String action;

    @Column(name = "status")
    private Integer status;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "success_log", columnDefinition="text")
    private String successLog;

    @Column(name = "fail_log", columnDefinition="text")
    private String failLog;

    @Column(name = "token")
    private String token;

    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    
    
}