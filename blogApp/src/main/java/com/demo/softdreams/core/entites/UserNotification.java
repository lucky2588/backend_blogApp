package com.demo.softdreams.core.entites;

import com.demo.softdreams.core.entites.Auditable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Slf4j
@SQLDelete(sql = "UPDATE promotions SET deleted = true WHERE id=?")
public class UserNotification extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "target_id")     // GameId
    private Long targetId;

    @Column(name = "type")
    private String type;

    @Column(name = "announce_date")
    private Date announceDate;

    @Column(name = "action")
    private String action;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "content", columnDefinition = "text")
    private String content;
}