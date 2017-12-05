package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Category;
import com.java.pojo.Product;

/**
 * 在这个测试例子中，增加了一个新的Category对象"c1" 
 * 并将其设置为id=8的product的category
 * @author asus-pc
 *
 */
public class TestHibernate_11 {
	
	public static void main(String[]args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		//创建一个Category对象，然后用session.save()将其变成持久化对象
		Category c = new Category();
		c.setName("c1");
		s.save(c);
		
		//将id=8的product加上category属性
		Product p = (Product)s.get(Product.class, 8);
		p.setCategory(c);
		s.update(p);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
