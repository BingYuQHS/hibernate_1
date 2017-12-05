package com.java.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

/**
 * 通过标准SQL语句进行查询 
 * Hibernate依然保留了对标准SQL语句的支持
 * 在一些场合，比如多表联合查询，并且有分组统计函数的情况下，标准SQL语句依然是效率较高的一种选择
 * 
 * 使用标准，根据name模糊查询
 * @author asus-pc
 *
 */
public class TestHibernate_08 {
	
	@SuppressWarnings("unchecked")
	public static void main(String []args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		String name = "iphone";
		
		String sql = "select * from product_ p where p.name like '%"+name+"%'";
		//使用Session的createSQLQuery方法执行标准SQL语句
		Query q = s.createSQLQuery(sql);
		//标准SQL语句有可能是从多张表里进行联合查询，
		//返回结果可能是不同表的不同字段，所以无法把结果装在某一个指定的pojo里。
		//所以返回的集合里的每一个元素是一个对象数组。 
		//然后再通过下标把这个对象数组中的数据取出来。
		List<Object[]> list = q.list();
		for (Object[] os:list){
			for (Object filed:os){
				System.out.print(filed+"\t");
			}
			System.out.println();
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
