package com.inventarysystem.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories("com.inventarysystem.repository")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        source.setUrl(env.getRequiredProperty("spring.datasource.url"));
        source.setUsername(env.getRequiredProperty("spring.datasource.username"));
        source.setPassword(env.getRequiredProperty("spring.datasource.password"));
        return source;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.inventarysystem.models.entity");
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto","none");
        jpaProperties.put("hibernate.default_schema",env.getRequiredProperty("spring.jpa.hibernate.schema"));
        jpaProperties.put("hibernate.physical_naming_strategy","com.vladmihalcea.hibernate.type.util.CamelCaseToSnakeCaseNamingStrategy");
        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.show_sql",true);
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

}
