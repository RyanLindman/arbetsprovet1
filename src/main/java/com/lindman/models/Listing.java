package com.lindman.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * implementation of Soft delete.
 * in this case, the deleted data won't show when fetching a listing(s).
 */
@SQLDelete(sql = "UPDATE listing SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Listing implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Many-to-one relationship with the User Entity
     * A listing will only have one creator, the user, but the user may have several listings.
     * It's also using the timestamp annotations for time data
     */
    @ManyToOne
    private ApplicationUser user;
    private String title;
    private String description;
    private boolean deleted = Boolean.FALSE;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

}
