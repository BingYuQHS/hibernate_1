package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 如果是同一个线程(本例是在主线程里)，每次获取的都是相同的Session 
 * 注意：只需要关闭一次即可
 * @author Administrator
 *
 */
public class TestHibernate_33_2 {
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s1 = sf.getCurrentSession();
		Session s2 = sf.getCurrentSession();
		
		System.out.println(s1==s2);
		
		s1.close(); //此处没有定义事务，还得手动关闭session
//		s2.close();
		sf.close();
	}
}
