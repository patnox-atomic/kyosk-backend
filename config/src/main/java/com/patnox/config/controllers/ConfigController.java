package com.patnox.config.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.time.*;

import com.patnox.config.services.*;
import com.patnox.config.models.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping(path = "/")
public class ConfigController
{
    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    //query for all configs
    @GetMapping("configs")
    public List<Config> getAll()
    {
        log.debug("Got a GET request for all configs");
        return configService.getAllConfigs();
    }

//    //query for one config given its id
//    @GetMapping(path = "configs/{configId}")
//    public Config getConfig(@PathVariable("configId") Long configId) {
//        return configService.getConfig(configId);
//    }

    //query for one config given its name
    @GetMapping(path = "configs/{configName}")
    public Config getConfig(@PathVariable("configName") String configName)
    {
        log.debug("Got a GET request for a config with name " + configName);
        return configService.getConfig(configName);
    }

    //insert a new config
    @PostMapping("configs")
    public void createNewConfig(@RequestBody Config newConfig)
    {
        log.debug("Got a POST with a new config: " + newConfig.getName());
        configService.addNewConfig(newConfig);
    }

//    //delete a config using its ID
//    @DeleteMapping(path = "configs/{configId}")
//    public void deleteConfig(@PathVariable("configId") Long configId)
//    {
//        configService.deleteConfig(configId);
//    }

    //delete a config using its name
    @DeleteMapping(path = "configs/{configName}")
    public void deleteConfig(@PathVariable("configName") String configName)
    {
        log.debug("Got a DELETE for the config: " + configName);
        configService.deleteConfig(configName);
    }

    //search
    @GetMapping("search")
    public List<Config> doSearch(HttpServletRequest searchRequest)
    {
        Map<String, String[]> requestParameterMap = searchRequest.getParameterMap();
        SortedMap<String, String> sm = new TreeMap<>();
        for(String key : requestParameterMap.keySet())
        {
            System.out.println("Search Parameter Key : " + key + ", Value: " + requestParameterMap.get(key)[0]);
            sm.put(key, requestParameterMap.get(key)[0]);
        }
        if(sm.size() > 0)
        {
            String path = (String) sm.keySet().toArray()[0];
            String value = (String) sm.get(path);
            System.out.println("Search using path : " + path + ", Value: " + value);
            return(configService.doSearch(path, value));
        }
        //System.out.println("Search Parameters = " + searchRequest.getParams().size());
        throw new IllegalStateException("Config does not exist");
        //return(null);
    }

    //edit a config
    @PutMapping("configs/{configName}")
    public void editConfig(@PathVariable("configName") String configName,
                               @RequestBody Config editedConfig)
    {
        log.debug("Got a PUT with edited config: " + configName);
        configService.updateConfig(editedConfig);
    }

    //edit a config
    @PatchMapping("configs/{configName}")
    public void patchConfig(@PathVariable("configName") String configName,
                                @RequestBody Config editedConfig)
    {
        log.debug("Got a PATCH with edited config: " + configName);
        configService.updateConfig(editedConfig);
    }

}
