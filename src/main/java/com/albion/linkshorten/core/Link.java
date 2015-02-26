package com.albion.linkshorten.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Date;

@Entity
@Table(name = "links")
@NamedQueries({

        @NamedQuery(
                name = "com.albion.linkshorten.core.Link.findAll",
                query = "SELECT l FROM Link l"
        ),

        @NamedQuery(
                name = "com.albion.linkshorten.core.Link.findByShortened",
                query = "SELECT l FROM Link l WHERE l.shortened = :shortened"
        ),

        @NamedQuery(
                name = "com.albion.linkshorten.core.Link.findByOriginal",
                query = "SELECT l FROM Link l WHERE l.original = :original"
        )
})
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @URL
    @NotBlank
    @Column(name = "original", nullable = false)
    private String original;

    @Column(name = "shortened", nullable = false)
    private String shortened;

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getOriginal() {
        return original;
    }

    @JsonProperty
    public void setOriginal(String original) {
        this.original = original;
    }

    @JsonProperty
    public String getShortened() {
        return shortened;
    }

    @JsonProperty
    public void setShortened(String shortened) {
        this.shortened = shortened;
    }


    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createdAt = new Date();

    @JsonProperty
    public Date getCreatedAt() {
        return createdAt;
    }

    @JsonProperty
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Link() {}

    public Link(String original) {
        this.original = original;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        final Link that = (Link) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.original, that.original) &&
                Objects.equals(this.shortened, that.shortened);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, original, shortened);
    }    
}
