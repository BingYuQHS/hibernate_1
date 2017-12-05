package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;

/**
 * 都通过id=500去获取对象 
 * get方式会返回null 
 * load方式会抛出异常 
 * @author Administrator
 *
 */
public class TestHibernate_32_2 {
	@SuppressWarnings("unused")
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		System.out.println("log1");
		Product p = (Product)s.get(Product.class, 1);
		System.out.println("log2");
		Product p2 = (Product)s.load(Product.class, 2);
		System.out.println("log3");
		
		//get方式会返回null
		Product p3 = (Product)s.get(Product.class, 500);
		System.out.println("p3="+p3);
		
		//load方式会抛出异常 
//		Product p4 = (Product)s.load(Product.class, 500);
//		System.out.println("p4="+p4);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
