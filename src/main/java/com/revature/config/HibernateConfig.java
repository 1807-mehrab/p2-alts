package com.revature.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.revature.repository.ClientDao;
import com.revature.repository.EmployeeDao;
import com.revature.repository.GameDao;
import com.revature.repository.ManagerDao;
import com.revature.repository.ReportDao;
import com.revature.repository.RequestDao;
import com.revature.service.ClientService;
import com.revature.service.EmployeeService;
import com.revature.service.GameService;
import com.revature.service.ManagerService;
import com.revature.service.ReportService;
import com.revature.service.RequestService;

@Configuration
@ComponentScan("com.revature")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HibernateConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource myDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.revature" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	Properties hibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 1L;

			{
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			}
		};
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tm = new HibernateTransactionManager();
		tm.setSessionFactory(sessionFactory);

		return tm;
	}

	@Bean
	public ManagerDao managerDao(SessionFactory sessionFactory) {
		ManagerDao dao = new ManagerDao();
		dao.setSessionFactory(sessionFactory);

		return dao;
	}

	@Bean
	public ManagerService managerService(ManagerDao dao) {
		ManagerService service = new ManagerService();
		service.setDao(dao);

		return service;
	}

	@Bean
	public ClientDao clientDao(SessionFactory sessionFactory) {
		ClientDao dao = new ClientDao();
		dao.setSessionFactory(sessionFactory);

		return dao;
	}

	@Bean
	public ClientService clientService(ClientDao dao) {
		ClientService service = new ClientService();
		service.setDao(dao);

		return service;
	}

	@Bean
	public EmployeeDao employeeDao(SessionFactory sessionFactory) {
		EmployeeDao dao = new EmployeeDao();
		dao.setSessionFactory(sessionFactory);

		return dao;
	}

	@Bean
	public EmployeeService employeeService(EmployeeDao dao) {
		EmployeeService service = new EmployeeService();
		service.setDao(dao);

		return service;
	}

	@Bean
	public GameDao gameDao(SessionFactory sessionFactory) {
		GameDao dao = new GameDao();
		dao.setSessionFactory(sessionFactory);

		return dao;
	}

	@Bean
	public GameService gameService(GameDao dao) {
		GameService service = new GameService();
		service.setDao(dao);

		return service;
	}

	@Bean
	public ReportDao reportDao(SessionFactory sessionFactory) {
		ReportDao dao = new ReportDao();
		dao.setSessionFactory(sessionFactory);

		return dao;
	}

	@Bean
	public ReportService reportService(ReportDao dao) {
		ReportService service = new ReportService();
		service.setDao(dao);

		return service;
	}

	@Bean
	public RequestDao requestDao(SessionFactory sessionFactory) {
		RequestDao dao = new RequestDao();
		dao.setSessionFactory(sessionFactory);

		return dao;
	}

	@Bean
	public RequestService requestService(RequestDao dao) {
		RequestService service = new RequestService();
		service.setDao(dao);

		return service;
	}
}