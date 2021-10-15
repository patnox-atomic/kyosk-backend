package com.patnox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.*;

import com.patnox.config.models.*;
import com.patnox.config.repositories.*;

@Configuration
public class DataConfig 
{
	
	@Bean
	CommandLineRunner productDataInjector(ConfigRepository configRepository)
	{
		return args -> {
			//Sample CPU Data
			Cpu cpu1 = new Cpu(
					1L,
					1,
					true,
					"250m"
			);
			Cpu cpu2 = new Cpu(
					2L,
					2,
					false,
					"300m"
			);
			Cpu cpu3 = new Cpu(
					3L,
					3,
					false,
					"680m"
			);
			//Sample Limits Data
			Limits lm1 = new Limits(
					1L,
					1,
					cpu1
			);
			Limits lm2 = new Limits(
					2L,
					2,
					cpu2
			);
			Limits lm3 = new Limits(
					3L,
					3,
					cpu3
			);
			//Sample Monitoring Data
			Monitoring mnt1 = new Monitoring(
					1L,
					1,
					true
			);
			Monitoring mnt2 = new Monitoring(
					2L,
					2,
					false
			);
			//Sample META Data
			Metadata meta1 = new Metadata(
					1L,
					1,
					mnt1,
					lm1
			);
			Metadata meta2 = new Metadata(
					2L,
					2,
					mnt1,
					lm2
			);
			Metadata meta3 = new Metadata(
					3L,
					3,
					mnt2,
					lm3
			);
			//Sample Config Data
			Config cnf1 = new Config(
					1L,
					1,
					"datacenter-1",
					meta1
			);
			Config cnf2 = new Config(
					2L,
					2,
					"datacenter-2",
					meta2
			);
			Config cnf3 = new Config(
					3L,
					3,
					"datacenter-3",
					meta3
			);
			//Commit Data
			configRepository.saveAll(
					List.of(cnf1, cnf2, cnf3)
			);
		};
	}
	
}
