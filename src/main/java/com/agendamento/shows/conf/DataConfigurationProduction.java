package com.agendamento.shows.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile(value = "prod")
public class DataConfigurationProduction {
	@Value("${MYSQL_URL}")
	private String MYSQL_URL;

	@Value("${MYSQLDATABASE}")
	private String MYSQLDATABASE;

	@Value("${MYSQLHOST}")
	private String MYSQLHOST;

	@Value("${MYSQLPASSWORD}")

	private String MYSQLPASSWORD;

	@Value("${MYSQLPORT}")
	private String MYSQLPORT;

	@Value("${MYSQLUSER}")
	private String MYSQLUSER;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = configuraDadosDoMySQL();
		return dataSource;
	}

	private DriverManagerDataSource configuraDadosDoMySQL() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + MYSQLHOST + ":" + MYSQLPORT + "/" + MYSQLDATABASE);
		dataSource.setUsername(MYSQLUSER);
		dataSource.setPassword(MYSQLPASSWORD);
		return dataSource;
	}

	private String determinaOLocalHost() {
		String host;
		if (localHostNaoInformado()) {
			host = "localhost";
		} else {
			host = System.getenv("MYSQL_HOST");
		}
		return host;
	}

	private boolean localHostNaoInformado() {
		return System.getenv("MYSQL_HOST") == null;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("com.agendamento.shows.conf.MySQLDialeto");
		adapter.setPrepareConnection(true);
		return adapter;
	}
}
