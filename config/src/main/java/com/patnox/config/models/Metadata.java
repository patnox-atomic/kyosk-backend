package com.patnox.config.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@DynamicInsert
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "metadata")
@Table(name = "metadata")
//@RedisHash("metadata")
public class Metadata {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(
            name = "metadata_sequence",
            sequenceName = "metadata_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "metadata_sequence"
    )
    @JsonIgnore
    private Long id;

    @Version
    @Column(name = "VERSION")
    @JsonIgnore
    private long version;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "monitoring_id", nullable = false, referencedColumnName = "id")
    private Monitoring monitoring;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "limit_id", nullable = false, referencedColumnName = "id")
    private Limits limits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Monitoring getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(Monitoring monitoring) {
        this.monitoring = monitoring;
    }

    public Limits getLimits() {
        return limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Metadata(Long id, long version, Monitoring monitoring, Limits limits) {
        this.id = id;
        this.version = version;
        this.monitoring = monitoring;
        this.limits = limits;
    }

    public Metadata(Monitoring monitoring, Limits limits) {
        this.monitoring = monitoring;
        this.limits = limits;
    }

    public Metadata(Long id, Monitoring monitoring, Limits limits) {
        this.id = id;
        this.monitoring = monitoring;
        this.limits = limits;
    }

    public Metadata() {
    }
}
