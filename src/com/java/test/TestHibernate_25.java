package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Category;
import com.java.pojo.Product;

public class TestHibernate_25 {
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		Category c = (Category)s.get(Category.class, 1);
		//运行代码就会发现，这3个瞬时状态的产品对象虽然没有添加到数据库里，
		//但是在事务提交的时候，会把他们3个持久化。 
		//如果没有cascade="save-update"，就会报错 
		
		System.out.print(c); //注意一定要保证c不为空
		
		Product p1 = new Product();
		p1.setName("product_301");
		Product p2 = new Product();
		p2.setName("product_302");
		Product p3 = new Product();
		p3.setName("product_303");
		
		c.getProducts().add(p1);
		c.getProducts().add(p2);
		c.getProducts().add(p3);
		
		//不执行s.update(c);操作就进行了级联更新，product_表里多了数据
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}

