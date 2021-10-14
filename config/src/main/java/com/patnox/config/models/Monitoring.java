package com.patnox.config.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@DynamicInsert
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "monitoring")
@Table(name = "monitoring")
//@RedisHash("monitoring")
public class Monitoring {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(
            name = "monitoring_sequence",
            sequenceName = "monitoring_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "monitoring_sequence"
    )
    @JsonIgnore
    private Long id;

    @Version
    @Column(name = "VERSION")
    @JsonIgnore
    private long version;

    @Column(name = "enabled" ,columnDefinition = "boolean default false")
    private Boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Monitoring(Long id, long version, Boolean enabled) {
        this.id = id;
        this.version = version;
        this.enabled = enabled;
    }

    public Monitoring(Boolean enabled) {
        this.enabled = enabled;
    }

    public Monitoring() {
        this.enabled = false;
    }
}
