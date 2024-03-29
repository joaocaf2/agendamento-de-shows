package com.agendamento.shows.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile(value = "dev")
public class DataConfiguration {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = configuraDadosDoMySQL();
		return dataSource;
	}

	private DriverManagerDataSource configuraDadosDoMySQL() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		String host, port;
		host = determinaOLocalHost();
		port = determinaAPorta();
		dataSource.setUrl("jdbc:mysql://" + host + ":" + port + "/shows?createDatabaseIfNotExist=true");
		dataSource.setUsername(System.getenv("userBd"));
		dataSource.setPassword(System.getenv("passwdBd"));
		return dataSource;
	}

	private String determinaAPorta() {
		if (portaNaoInformada()) {
			return "3306";
		} else {
			return System.getenv("MYSQLPORT");
		}
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

	private boolean portaNaoInformada() {
		return System.getenv("MYSQLPORT") == null;
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
