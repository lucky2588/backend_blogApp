package com.demo.softdreams.core.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role", schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;
    @Column(name = "role_code")
    private String roleCode;
    @OneToMany(mappedBy = "roles")
    @JsonIgnore
    private Collection<Permissions> permissions;




    public Collection<Permissions> getPermissions(){
        return permissions;
    }


}