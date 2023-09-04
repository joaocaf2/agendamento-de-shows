package com.agendamento.shows.conf;

import org.hibernate.dialect.MySQL5Dialect;

public class MySQLDialeto extends MySQL5Dialect{

	@Override
	public String getTableTypeString() {
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}
}
