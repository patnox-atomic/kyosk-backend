package com.patnox.config.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@DynamicInsert
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "limits")
@Table(name = "limits")
//@RedisHash("limits")
public class Limits {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(
            name = "limits_sequence",
            sequenceName = "limits_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "limits_sequence"
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
    @JoinColumn(name = "cpu_id", nullable = false, referencedColumnName = "id")
    private Cpu cpu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Limits(Long id, long version, Cpu cpu) {
        this.id = id;
        this.version = version;
        this.cpu = cpu;
    }

    public Limits(Cpu cpu) {
        this.cpu = cpu;
    }

    public Limits() {
    }
}
