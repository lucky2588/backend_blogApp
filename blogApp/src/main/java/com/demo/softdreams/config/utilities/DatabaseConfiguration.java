//package com.demo.softdreams.config.utilities;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import java.util.Arrays;
//import javax.inject.Inject;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.ApplicationContextException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableJpaRepositories({"com.demo.softdreams.core.respository"})
//@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
//@EnableTransactionManagement
//
//public class DatabaseConfiguration {
//
//
//    @Inject
//    private Environment env;
//
//    @Primary
//    @Bean(name = "dataSource")
//    @ConditionalOnExpression("#{!environment.acceptsProfiles('cloud') && !environment.acceptsProfiles('heroku')}")
//    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
//
//        if (dataSourceProperties.getUrl() == null) {
//
//
//            throw new ApplicationContextException("Database connection pool is not configured correctly");
//        }
//        HikariConfig config = new HikariConfig();
//        config.setDataSourceClassName(dataSourceProperties.getDriverClassName());
//        config.addDataSourceProperty("url", dataSourceProperties.getUrl());
//        if (dataSourceProperties.getUsername() != null) {
//            config.addDataSourceProperty("user", dataSourceProperties.getUsername());
//        } else {
//            config.addDataSourceProperty("user", ""); // HikariCP doesn't allow null user
//        }
//        if (dataSourceProperties.getPassword() != null) {
//            config.addDataSourceProperty("password", dataSourceProperties.getPassword());
//        } else {
//            config.addDataSourceProperty("password", ""); // HikariCP doesn't allow null password
//        }
//        config.setJdbcUrl(dataSourceProperties.getUrl());
////        config.setAutoCommit(env.getProperty("spring.datasource.hikari.auto-commit", Boolean.class));
////        config.setMaximumPoolSize(env.getProperty("spring.datasource.hikari.maximumPoolSize", Integer.class));
////        config.setMaxLifetime(env.getProperty("spring.datasource.hikari.maxLifetime", Long.class));
////        config.setConnectionTimeout(env.getProperty("spring.datasource.hikari.connectionTimeout", Long.class));
////                config.setMinimumIdle(env.getProperty("spring.datasource.hikari.minimumIdle", Integer.class));
////                config.setIdleTimeout(env.getProperty("spring.datasource.hikari.idleTimeout", Long.class));
//        config.setAllowPoolSuspension(false);
////        config.setPoolName(env.getProperty("spring.datasource.poolName"));
//        config.setLeakDetectionThreshold(120000);
//
//        return new HikariDataSource(config);
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public jakarta.persistence.EntityManagerFactory entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("dataSource") DataSource dataSource
//    ) {
//        return builder.dataSource(dataSource).packages("com.demo.softdreams").persistenceUnit("persistenUnitName").build().getObject();
//    }
//
//    @Primary
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory());
//    }
//
//
//
//
//
//
//}
