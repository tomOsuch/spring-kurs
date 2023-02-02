package pl.springkurs.shop;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.springkurs.shop.payments.adapters.time.SystemTimeProvider;
import pl.springkurs.shop.payments.ports.TimeProvider;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@PropertySource("classpath:jdbc.properties")
@EnableWebMvc
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ShopConfiguration implements WebMvcConfigurer {

    @Bean
    public TimeProvider systemTimeProvider() {
        return new SystemTimeProvider();
    }

    @Bean
    public DataSource dataSource(Environment environment) {
        var datasource = new HikariDataSource();
        datasource.setUsername(environment.getProperty("database.username"));
        datasource.setPassword(environment.getProperty("database.password"));
        datasource.setJdbcUrl(environment.getProperty("database.url"));
        datasource.setDriverClassName(environment.getProperty("database.driver"));
        datasource.setMaximumPoolSize(20);
        return datasource;
    }

    @Bean
    public PropertiesFactoryBean jpaProperties() {
        var factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("jpa.properties"));
        return factoryBean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties jpaProperties) {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaProperties(jpaProperties);
        factoryBean.setPackagesToScan("pl.springkurs.shop");
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:4200");
    }

    @Bean
    public MessageSource messageSource() {
        var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("exceptions");
        return messageSource;
    }
}
