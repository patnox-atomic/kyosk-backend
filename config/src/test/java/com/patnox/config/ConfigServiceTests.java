package com.patnox.config;

import com.patnox.config.models.*;
import com.patnox.config.repositories.ConfigRepository;
import com.patnox.config.services.ConfigService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConfigServiceTests {
    @Mock
    private ConfigRepository configRepository;

    @InjectMocks
    private ConfigService configService;

    @Test
    void createConfig()
    {
        Cpu cpu1 = new Cpu(
                1L,
                1,
                true,
                "250m"
        );
        Limits lm1 = new Limits(
                1L,
                1,
                cpu1
        );
        Monitoring mnt1 = new Monitoring(
                1L,
                1,
                true
        );
        Metadata meta1 = new Metadata(
                1L,
                1,
                mnt1,
                lm1
        );
        Config cnf1 = new Config(
                1L,
                1,
                "datacenter-1",
                meta1
        );
        
        assertThat(cnf1.getMetadata().getMonitoring().getEnabled()).isTrue();

    }

}
