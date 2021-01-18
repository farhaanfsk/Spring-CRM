package web;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan({ "controller,dao,entity,service" })
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class MVCConfig implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp().prefix("/WEB-INF/view/");
		registry.jsp().suffix(".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "controller,dao,entity,service,web" });
		sessionFactory.setHibernateProperties(hibernateProperites());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		Properties properties = new Properties();
		properties.put("driverClass", environment.getProperty("jdbc.driverClass"));
		properties.put("jdbcUrl", environment.getProperty("jdbc.url"));
		properties.put("user", environment.getProperty("jdbc.user"));
		properties.put("password", environment.getProperty("jdbc.password"));

		properties.put("initialPoolSize", environment.getProperty("hibernate.initialPoolSize"));
		properties.put("minPoolSize", environment.getProperty("hibernate.minPoolSize"));
		properties.put("maxPoolSize", environment.getProperty("hibernate.maxPoolSize"));
		properties.put("maxIdleTime", environment.getProperty("hibernate.maxIdleTime"));
		dataSource.setProperties(properties);
		return dataSource;
	}

	@Bean
	public Properties hibernateProperites() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		return properties;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
