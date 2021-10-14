package com.patnox.config.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.patnox.config.repositories.*;
import com.patnox.config.models.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
//@AllArgsConstructor
@Slf4j
public class ConfigService
{
    private final ConfigRepository configRepository;

    @Autowired
    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    //Get all configs
    public List<Config> getAllConfigs()
    {
        log.error("Get all configs");
        return configRepository.findAll();
    }

    //Search configs
    public List<Config> doSearch(String path, String value)
    {
        log.error("search configs using path");
        return configRepository.searchConfigs(path, value);
    }

    //Get a specific config
    public Config getConfig(Long configId)
    {
        log.error("search configs using ID");
        return configRepository.findById(configId).orElseThrow(() -> new IllegalStateException("Config with ID: " + configId + " does not exist"));
    }

    //Get a specific config
    public Config getConfig(String configName)
    {
        log.error("search configs using name");
        return configRepository.findConfigByName(configName).orElseThrow(() -> new IllegalStateException("Config with Name: " + configName + " does not exist"));
    }

    //Add a new config
    public void addNewConfig(Config newConfig)
    {
        log.error("add new config");
        configRepository.save(newConfig);
    }

    //Delete a config using its ID
    @Transactional
    public void deleteConfig(Long configId)
    {
        System.out.println("Request to delete Config ID: " + configId);
        log.debug("Request to delete Config ID: " + configId);
        boolean exists = configRepository.existsById(configId);
        if(!exists)
        {
            System.err.println("Error: Config with ID: " + configId + " does not exist");
            throw new IllegalStateException("Config with ID: " + configId + " does not exist");
        }
        else
        {
            System.out.println("Config with ID: " + configId + " exists so we will proceed");
            Config victimizedConfig = configRepository.findById(configId).orElseThrow(() -> new IllegalStateException("Config with ID: " + configId + " does not exist"));

            configRepository.delete(victimizedConfig);
        }
    }

    //Delete a config using its Name
    @Transactional
    public void deleteConfig(String configName)
    {
        System.out.println("Request to delete Config Name: " + configName);
        log.debug("Request to delete Config Name: " + configName);

        Config victimizedConfig = configRepository.findConfigByName(configName).orElseThrow(() -> new IllegalStateException("Config with Name: " + configName + " does not exist"));

        configRepository.delete(victimizedConfig);
    }

    @Transactional
    public void updateConfig(Config editedConfig)
    {
        String configName = editedConfig.getName();
        System.out.println("Request to update Config Name: " + configName);
        Config victimizedConfig = configRepository.findConfigByName(configName).orElseThrow(() -> new IllegalStateException("Config with Name: " + configName + " does not exist"));

        victimizedConfig.setMetadata(editedConfig.getMetadata());
    }

}
