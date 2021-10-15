package com.patnox.config.repositories;

import com.patnox.config.models.Config;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepositoryCustom {
    List<Config> searchConfigs(String path, String value);
}
