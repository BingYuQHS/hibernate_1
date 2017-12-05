package com.java.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Category;
import com.java.pojo.Product;

/**
 * 测试one-to-many关系
 * @author asus-pc
 *
 */
public class TestHibernate_12 {
	public static void main(String []args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Category c = (Category)s.get(Category.class, 1);
		Set<Product> ps = c.getProducts();
		for (Product p:ps){
			System.out.println(p.getName());
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
