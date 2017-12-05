package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;

/**
 * 如果是做增加，修改，删除是必须放在事务里进行的。
 *  但是如果是查询或者get，
 *  那么对于openSession而言就不需要放在事务中进行
 *  
 * @author Administrator
 *
 */
public class TestHibernate_33_4 {
	public static void main(String[]args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		 
		Product p = (Product)s.get(Product.class, 3);
		System.out.println(p.getName());
		s.close();
		sf.close();
	}
}
