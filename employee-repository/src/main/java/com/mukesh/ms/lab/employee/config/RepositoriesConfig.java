package com.mukesh.ms.lab.employee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages="com.mukesh.ms.lab.employee.repository")
public class RepositoriesConfig
{

}