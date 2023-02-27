package com.juaracoding.dujavaweb.configuration;/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition)
IntelliJ IDEA 2022.3.2 (Community Edition)
Build #IC-223.8617.56, built on January 26, 2023
@Author USER a.k.a. Deby Utari
Java Developer
Created on 27/02/2023 22:20
@Last Modified 27/02/2023 22:20
Version 1.0
*/

import com.juaracoding.dujavaweb.core.Crypto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class MainConfiguration {

    @Autowired
    private Environment environment;

    @Primary
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(environment.getProperty("spring.datasource.driverClassName"));
        dataSourceBuilder.url(environment.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(environment.getProperty("spring.datasource.username"));//environment.getProperty("spring.datasource.user")
        dataSourceBuilder.password(Crypto.performDecrypt(environment.getProperty("spring.datasource.password")));//environment.getProperty("spring.datasource.password")
        return dataSourceBuilder.build();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
