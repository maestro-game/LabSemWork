package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.TemplateExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.itis.javalab.repository")
public class AppConfig {
    private final Environment environment;
    private final ApplicationContext appContext;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        return hikariConfig;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan("ru.itis.javalab.repository", "ru.itis.javalab.model");
        bean.setPersistenceUnitName("default");
        bean.setDataSource(dataSource());
        bean.setJpaVendorAdapter(jpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        bean.setJpaProperties(properties);
        return bean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        var bean = new HibernateJpaVendorAdapter();
        bean.setGenerateDdl(true);
        bean.setShowSql(true);
        bean.setDatabasePlatform(environment.getProperty("hibernate.database.platform"));
        return bean;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        var bean = new JavaMailSenderImpl();
        bean.setUsername(environment.getProperty("mail.username"));
        bean.setPassword(environment.getProperty("mail.password"));
        bean.setHost(environment.getProperty("mail.host"));
        bean.setPort(Integer.parseInt(environment.getProperty("mail.port")));
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", environment.getProperty("mail.properties.transport.protocol"));
        properties.put("mail.smtp.auth", environment.getProperty("mail.properties.mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", environment.getProperty("mail.properties.mail.smtp.starttls.enable"));
        properties.put("mail.debug", environment.getProperty("mail.properties.mail.debug"));
        properties.put("mail.smtp.allow8bitmime", environment.getProperty("mail.smtp.allow8bitmime"));
        properties.put("mail.smtp.ssl.trust", environment.getProperty("mail.smtp.ssl.trust"));
        bean.setJavaMailProperties(properties);
        return bean;
    }

    @Bean
    freemarker.template.Configuration configuration() {
        var configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
