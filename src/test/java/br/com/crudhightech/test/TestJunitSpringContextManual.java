package br.com.crudhightech.test;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJunitSpringContextManual {

	@Test
	public void testeContextoSpring(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/springbeans.xml");
		BasicDataSource bds = (BasicDataSource)ctx.getBean("dataSourceDB");
		System.out.println(bds.getUsername() + " " + bds.getPassword() + " " + bds.getDriverClassName() + " " + bds.getUrl());
		ctx.close();
	}
}
