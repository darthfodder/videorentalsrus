package com.videorentalsrus.dao;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao extends SqlSessionDaoSupport {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@PostConstruct
	public void baseDaoPostConstruct()
	{
		this.setSqlSessionFactory(sqlSessionFactory);
	}

}
