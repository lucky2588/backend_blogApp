//package com.demo.softdreams.config.utilities;
//import org.hibernate.cfg.Environment;
//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//import static jdk.internal.loader.BootLoader.packages;
//
//@EnableJpaRepositories(basePackages = "eu.example", entityManagerFactoryRef = "ds1EntityManagerFactory", transactionManagerRef = "ds1TransactionManager")
//@Configuration
//public class Ds1DataSourceConfiguration {
//
//    private static final String DS1_ENTITY_MANAGER_FACTORY = "ds1EntityManagerFactory";
//    private static final String DS1_TRANSACTION_MANAGER = "ds1TransactionManager";
//
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix = "spring.ds.datasource1")
//    public DataSourceProperties ds1DataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.ds.datasource1")
//    public DataSource ds1DataSource(DataSourceProperties properties) {
//        return properties
//                .initializeDataSourceBuilder()
//                .build();
//    }
//
//    @Primary
//    @Bean(name = DS1_ENTITY_MANAGER_FACTORY)
//    public LocalContainerEntityManagerFactoryBean ds1EntityManagerFactoryBean(
//            EntityManagerFactoryBuilder factoryBuilder, DataSource ds1Ds) {
//        return factoryBuilder.
//                  withDataSource()
//                .build();
//    }
//
//    @Primary
//    @Bean(name = DS1_TRANSACTION_MANAGER)
//    public PlatformTransactionManager ds1TransactionManager(
//            @Qualifier(DS1_ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean managerFactoryBean) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(managerFactoryBean.getObject());
//        return jpaTransactionManager;
//    }
//
//}