package com.videorentalsrus;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.videorentalsrus.dao.CustomerDao;

@SpringBootApplication
@MapperScan(annotationClass = Mapper.class, basePackages = "com.videorentalsrus.dao")
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private CustomerDao customerDao;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("Test");
		System.out.println(customerDao.findCustomersByFullName("Testy", "Tester"));
	}
}
