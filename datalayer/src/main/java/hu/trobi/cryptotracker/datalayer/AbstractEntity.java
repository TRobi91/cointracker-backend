package hu.trobi.cryptotracker.datalayer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created")
    private Date creationTimestamp;
    @Column(name = "created_by")
    private Long creationUid;
    @Column(name = "modified")
    private Date modificationTimestamp;
    @Column(name = "modified_by")
    private Long modificationUid;

    @PrePersist
    public void prePersist() {
        final Date now = new Date();
        //TODO
        //final Long userId = SecurityUtil.getUserId();
        Long userId = 0l;
        setCreationFields(now, userId);
        setModificationFields(now, userId);
    }

    @PreUpdate
    public void preUpdate() {
        final Date now = new Date();
        //final Long userId = SecurityUtil.getUserId();
        Long userId = 0l;

        setModificationFields(now, userId);
    }

    private void setModificationFields(Date now, Long userId) {
        this.modificationTimestamp = now;
        this.modificationUid = userId;
    }

    private void setCreationFields(Date now, Long userId) {
        this.creationTimestamp = now;
        this.creationUid = userId;
    }

}