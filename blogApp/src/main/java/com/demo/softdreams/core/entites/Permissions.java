package com.demo.softdreams.core.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import java.util.UUID;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE permissions SET deleted = true WHERE id=?")
@Table(name = "permissions", schema = "public")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "permission_name", nullable = false)
    private String permissionName;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role roles;

    public Role getRole() {
        return roles;
    }

}