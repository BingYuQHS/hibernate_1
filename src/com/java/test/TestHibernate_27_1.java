package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Category;

/**
 * 二级缓存练习
 * 创建了两个Session
 * 在第一个Session里
 * 第一次获取id=1的Category 会执行SQL语句
 * 第二次获取id=1的Category，不会执行SQL语句，因为有一级缓存
 * 在第二个Session里
 * 获取id=1的Category，会执行SQL语句，因为在第二个Session，没有缓存该对象。 
 * 所以总共会看到两条SQL语句。 
 * @author Administrator
 *
 */
public class TestHibernate_27_1 {
	@SuppressWarnings("unused")
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		//开启第一个Session
		Session s = sf.openSession();
		s.beginTransaction();
		
		Category p1 = (Category)s.get(Category.class,1);
		Category p2 = (Category)s.get(Category.class,1);
		s.getTransaction().commit();
		s.close();
		
		System.out.println("************************************************");
		//开启第二个Session
		Session s2 = sf.openSession();
		s2.beginTransaction();
		Category p3 = (Category)s2.get(Category.class,1);
		
		s2.getTransaction().commit();
		s2.close();
		sf.close();
	}
}
