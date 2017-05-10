package com.gcit.library;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.library.dao.AuthorDAO;
import com.gcit.library.dao.BookDAO;
import com.gcit.library.dao.BorrowerDAO;
import com.gcit.library.dao.BranchDAO;
import com.gcit.library.dao.CopiesDAO;
import com.gcit.library.dao.GenreDAO;
import com.gcit.library.dao.LoanDAO;
import com.gcit.library.dao.PublisherDAO;
import com.gcit.library.service.AdminService;

@Configuration
public class LMSConfig {

	private static String usrName = "javaUser";
	private static String password = "javaPass";
	private static String url = "jdbc:mysql://localhost/library";
	private static String driver = "com.mysql.jdbc.Driver";

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(usrName);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	public PlatformTransactionManager txManager() {
		DataSourceTransactionManager dsManager = new DataSourceTransactionManager();
		dsManager.setDataSource(dataSource());
		return dsManager;
	}

	@Bean
	public JdbcTemplate template() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public AuthorDAO adao() {
		return new AuthorDAO();
	}

	@Bean
	public BookDAO bdao() {
		//System.out.println("book bean");
		return new BookDAO();
	}

	@Bean
	public BorrowerDAO bordao() {
		return new BorrowerDAO();
	}

	@Bean
	public BranchDAO brdao() {
		return new BranchDAO();
	}

	@Bean
	public CopiesDAO cdao() {
		return new CopiesDAO();
	}

	@Bean
	public GenreDAO gdao() {
		return new GenreDAO();
	}

	@Bean
	public LoanDAO ldao() {
		return new LoanDAO();
	}

	@Bean
	public PublisherDAO pdao() {
		return new PublisherDAO();
	}

	@Bean
	public AdminService adminService() {
		return new AdminService();
	}
}
