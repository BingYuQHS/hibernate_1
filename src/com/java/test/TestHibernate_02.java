package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;

/**
 * 对象的状态（临时状态，持久化状态【托管】，游离状态【脱管】）
 * @author asus-pc
 *
 */
public class TestHibernate_02 {

	public static void main(String []args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		Product p = new Product();
		p.setName("p2");
		System.out.println("此时p是临时状态");
		s.save(p);
		System.out.println("此时p是持久化状态");
		s.getTransaction().commit();//事务提交
		s.close();
		System.out.println("此时p是脱管状态");
		sf.close();
	}
}
