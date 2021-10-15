package com.patnox.config.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.patnox.config.models.*;
import java.util.*;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long>, ConfigRepositoryCustom
{
    @Query("SELECT s FROM config s WHERE s.name = ?1")
    Optional<Config> findConfigByName(String name);
}
