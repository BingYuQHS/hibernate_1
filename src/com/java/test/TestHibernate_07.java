package com.java.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.java.pojo.Product;

/**
 * 使用Criteria进行数据查询。 
 * 与HQL和SQL的区别是Criteria 完全是 面向对象的方式在进行数据查询，将不再看到有sql语句的痕迹
 * 
 * 使用Criteria，根据name模糊查询
 * @author asus-pc
 *
 */
public class TestHibernate_07 {
	
	@SuppressWarnings("unchecked")
	public static void main(String []args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		
		String name = "iphone";
		
		//1. 通过session的createCriteria创建一个Criteria 对象
		Criteria c = s.createCriteria(Product.class);
		//2. Criteria.add 增加约束。 在本例中增加一个对name的模糊查询(like)
		c.add(Restrictions.like("name","%"+name+"%"));
		//3. 调用list()方法返回查询结果的集合
		List<Product> ps = c.list();
		for (Product p:ps){
			System.out.println(p.getName());
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
