package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;

/**
 * 根据ID，把对象从表里删除掉
 * @author asus-pc
 *
 */
public class TestHibernate_04 {
	
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		Product p = (Product)s.get(Product.class, 5);
		s.delete(p);
		System.out.println("你已成功删除一条数据！");
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
