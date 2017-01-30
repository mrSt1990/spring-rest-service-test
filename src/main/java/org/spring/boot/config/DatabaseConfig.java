package org.spring.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Aleksey Stoyokha on 27.01.17.
 */
@Configuration
public class DatabaseConfig {

    /**
     * Environment in which the current application is running.
     */
    @Autowired
    private Environment environment;

    /**
     * Create a new instance of DriverManagerDataSource.
     * Fill it by database properties from environment.
     *
     * @return DriverManagerDataSource instance
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     * Declare entityManagerFactory. Fill it by dataSource and Hibernate properties.
     *
     * @return LocalContainerEntityManagerFactoryBean instance
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(environment.getProperty("entityManager.packagesToScan"));
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(buildHibernateProperties());
        entityManagerFactory.setValidationMode(ValidationMode.NONE);
        return entityManagerFactory;
    }

    /**
     * Build Hibernate properties from environment.
     *
     * @return Properties instance.
     */
    private Properties buildHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        return hibernateProperties;
    }

    /**
     * Declare the transaction manager.
     *
     * @return JpaTransactionManager instance
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

}
