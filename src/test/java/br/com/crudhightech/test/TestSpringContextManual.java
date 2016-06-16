package br.com.crudhightech.test;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringContextManual {
	
	public static void main(String[] args) {
		//Carregamento manual do contexto do spring
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/springbeans.xml");
		BasicDataSource bds = (BasicDataSource) ctx.getBean("dataSourceDB");
		System.out.println(bds.getPassword() + " " + bds.getUsername() + " " + bds.getDriverClassName() + " " + bds.getUrl());
		ctx.close();
	}
}
