package com.demo.softdreams.core.entites;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
//@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//@SQLDelete(sql = "update #{#entityName} e set e.is_deleted=false where e.id = ?") //||UPDATE table_product SET is_deleted = true WHERE id=?
public abstract class Auditable{
    @Column(name = "created_date", updatable = false)
    @Temporal(TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_modified_date",updatable = false)
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private Date lastModifiedDate;

    @CreatedBy
    @Column(name="created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name="modified_by")
    protected String modifiedBy;
    @Column(name="deleted", updatable = false)
    public boolean deleted = Boolean.FALSE;


}
