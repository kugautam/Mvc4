package com.gautam.mvc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ApplicationConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource  dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("db.driverclassname"));
		dataSource.setUrl(environment.getRequiredProperty("db.url"));
		dataSource.setUsername(environment.getRequiredProperty("db.username"));
		dataSource.setPassword(environment.getRequiredProperty("db.password"));
		return  dataSource;
	}
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource)
	{

		return new JdbcTemplate(dataSource);

	}

}
