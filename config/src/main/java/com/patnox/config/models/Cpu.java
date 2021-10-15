package com.patnox.config.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@DynamicInsert
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "cpu")
@Table(name = "cpu")
//@RedisHash("cpu")
public class Cpu {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(
            name = "cpu_sequence",
            sequenceName = "cpu_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cpu_sequence"
    )
    @JsonIgnore
    private Long id;

    @Version
    @Column(name = "VERSION")
    @JsonIgnore
    private long version;

    @Column(name = "enabled" ,columnDefinition = "boolean default false")
    private Boolean enabled;

    @Column(name = "value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Cpu() {
    }

    public Cpu(Boolean enabled, String value) {
        this.enabled = enabled;
        this.value = value;
    }

    public Cpu(Long id, long version, Boolean enabled, String value) {
        this.id = id;
        this.version = version;
        this.enabled = enabled;
        this.value = value;
    }
}
