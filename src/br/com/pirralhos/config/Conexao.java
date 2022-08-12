package br.com.pirralhos.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conexao extends DriverManagerDataSource {

	public Conexao() {

		this.setDriverClassName("com.mysql.jdbc.Driver");
		this.setUrl("jdbc:mysql://localhost:3306/pirralhos");
		this.setUsername(ApplicationBundleReader.getString("mysql_user"));
		this.setPassword(ApplicationBundleReader.getString("mysql_password"));

	}

}
