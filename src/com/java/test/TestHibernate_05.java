package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;

/**
 * 修改一个对象的属性，并更新到数据库
 * @author asus-pc
 *
 */
public class TestHibernate_05 {
	
	public static void main(String []args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		//根据ID获取该对象
		Product p = (Product)s.get(Product.class, 6);
		System.out.println("修改前的名称："+p.getName());
		
		//修改该对象的属性
		p.setName("iphone-modified");
		//通过Session的updtata方法把变化更新导数据库中
		s.update(p);
		
		s.getTransaction().commit();
		System.out.println("commit后的名称："+p.getName());
		s.close();
		sf.close();
	}
}
