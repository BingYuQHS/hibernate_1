package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * OpenSession每次都会得到一个新的Session对象
 * @author Administrator
 *
 */
public class TestHibernate_33_1 {
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s1 = sf.openSession();
		Session s2 = sf.openSession();
		
		System.out.println(s1==s2);
		
		s1.close();
		s2.close();
		sf.close();
	}
}

