package com.niit.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.UserDao;
import com.niit.Dao.UserDaoImpl;
import com.niit.model.User;



@Configuration
@ComponentScan("com.niit.BackEnd")
@EnableTransactionManagement
public class ApplicationConfig {
	
	@Autowired
	@Bean(name="dataSources")
	 public DataSource getH2DataSource() {
		 DriverManagerDataSource dataSource=new DriverManagerDataSource();
		 dataSource.setDriverClassName("org.h2.Driver");
	    	dataSource.setUrl("jdbc:h2:tcp://localhost/~/test2");
	    	dataSource.setUsername("sa");
	    	dataSource.setPassword("");
	    	return dataSource;
	    }
	    
	private Properties getHibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.format_sql", "true");
		
		return properties;
		}
	 @Autowired
	    @Bean(name = "sessionFactory")
	    public SessionFactory getSessionFactory(DataSource dataSource) {
	    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    	sessionBuilder.addProperties(getHibernateProperties());
	    	sessionBuilder.addAnnotatedClasses(User.class);
	    	
			return sessionBuilder.buildSessionFactory();
	    }
	 @Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(
				SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(
					sessionFactory);

			return transactionManager;
		}
	 
	 
@Autowired(required=true)
@Bean(name="userDao")
public UserDao getUserDao(SessionFactory sessionFactory)
{
	return new UserDaoImpl(sessionFactory);
	
}

}
