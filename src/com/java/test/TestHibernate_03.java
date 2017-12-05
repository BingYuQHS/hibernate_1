package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;

/**
 * 通过ID获取一个对象
 * @author asus-pc
 *
 */
public class TestHibernate_03 {
	
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		//调用Session的get方法，根据id获取对象。
		//除了id之外，还需要传递一个类对象，毕竟需要知道获取的是哪个对象
		Product p = (Product)s.get(Product.class,6);
		System.out.println("id=6的产品名称是："+p.getName());
		
		s.getTransaction().commit();
		s.clear();
		sf.close();
	}
}
