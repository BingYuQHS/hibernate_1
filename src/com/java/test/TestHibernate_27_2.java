package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Category;

/**
 * 使用不同的session,都去获取id=1的category,只会访问一次数据库。
 * 因为第二次获取虽然没有从第二个session中拿到缓存，但是从sessionfactory中拿到了Category缓存对象 
 * @author Administrator
 *
 */
public class TestHibernate_27_2 {
	@SuppressWarnings("unused")
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		//开启第一个Session
		Session s = sf.openSession();
		s.beginTransaction();
		
		Category c1 = (Category)s.get(Category.class, 1);
        System.out.println("log1");
        Category c2= (Category)s.get(Category.class, 1);
        System.out.println("log2");
        s.getTransaction().commit();
        s.close();
        
        //开启第二个Session
        Session s2 = sf.openSession();
        s2.beginTransaction();
        Category c3 = (Category)s2.get(Category.class, 1);
        System.out.println("log3");
  
		s2.getTransaction().commit();
		s2.close();
		sf.close();
	}
}
