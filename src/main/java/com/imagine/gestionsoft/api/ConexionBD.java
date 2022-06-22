package com.imagine.gestionsoft.api;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ConexionBD {

	private final String databaseUrl;
	private final String databaseDriver;
	private final String databaseUserName;
	private final String databasePassword;

	public ConexionBD() {
		this.databaseUrl = "jdbc:mysql://imaginefour.com:3306/imaginef_gestionsoft";
		this.databaseDriver = "com.mysql.cj.jdbc.Driver";
		this.databaseUserName = "imaginef_gestionsoft";
		this.databasePassword = ".Gestion!.";
	}

	@Bean
	public DataSource dataSource() {

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName(databaseDriver);
		dataSource.setJdbcUrl(databaseUrl);
		dataSource.setUsername(databaseUserName);
		dataSource.setPassword(databasePassword);
		dataSource.setIdleTimeout(120000);// Dos minutos
		dataSource.setMaximumPoolSize(20);
		dataSource.setMinimumIdle(5);
		dataSource.setConnectionTimeout(45000);
		return dataSource;
	}

}
