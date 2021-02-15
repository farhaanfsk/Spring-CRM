package com.fsk.spring.web;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({ "com.fsk.spring" })
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class MVCConfig implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setPackagesToScan("com.fsk.spring");
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(hibernateProperites());
		System.out.println(sessionFactory.getMetadataSources().toString());
		return sessionFactory;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClass"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.user"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public Properties hibernateProperites() {
		Properties properties = new Properties();

		properties.put("hibernate.c3p0.acquire_increment", environment.getProperty("hibernate.initialPoolSize"));
		properties.put("hibernate.c3p0.min_size", environment.getProperty("hibernate.minPoolSize"));
		properties.put("hibernate.c3p0.max_size", environment.getProperty("hibernate.maxPoolSize"));
		properties.put("hibernate.c3p0.timeout", environment.getProperty("hibernate.maxIdleTime"));

		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		return properties;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() throws PropertyVetoException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
