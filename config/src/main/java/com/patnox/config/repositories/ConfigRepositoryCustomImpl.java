package com.patnox.config.repositories;

import com.patnox.config.models.Config;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.*;

@Repository
public class ConfigRepositoryCustomImpl implements ConfigRepositoryCustom
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Config> searchConfigs(String path, String value) 
    {
        String query = "select d from config d where d." + path + " = " + value;
        TypedQuery<Config> typedQuery = entityManager.createQuery(query, Config.class);

        return typedQuery.getResultList();
    }
}
