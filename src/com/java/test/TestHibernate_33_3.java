package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 是不同线程，每次获取的都是不同的Session
 * @author Administrator
 *
 */
public class TestHibernate_33_3 {
	static Session s1;
	static Session s2;
	
	public static void main(String[]args)throws InterruptedException{
		final SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Thread t1 = new Thread(){
			public void run(){
				s1 = sf.getCurrentSession();
			}
		};
		t1.start();
		
		Thread t2 = new Thread(){
			public void run(){
				s2 = sf.getCurrentSession();
			}
		};
		t2.start();
		t1.join();
		t2.join();
		System.out.println(s1 == s2);
	}
}
