package com.patnox.config.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@DynamicInsert
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "config")
@Table(name = "config")
//@RedisHash("config")
public class Config {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(
            name = "config_sequence",
            sequenceName = "config_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "config_sequence"
    )
    @JsonIgnore
    private Long id;

    @Version
    @Column(name = "VERSION")
    @JsonIgnore
    private long version;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "metadata_id", nullable = false, referencedColumnName = "id")
    private Metadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Config(Long id, long version, String name, Metadata metadata) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.metadata = metadata;
    }

    public Config(String name, Metadata metadata) {
        this.name = name;
        this.metadata = metadata;
    }

    public Config() {
    }
}
