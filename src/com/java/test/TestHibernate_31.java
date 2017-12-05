package com.java.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.java.pojo.Product;

/**
 * 使用Criteria进行分页查询 
 * 无论你使用的是Oracle,Mysql,NoSQL还是DB2，分页查询的代码写法都是一样的
 * @author Administrator
 *
 */
public class TestHibernate_31 {
	@SuppressWarnings("unchecked")
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		String name = "iphone";
		
		Criteria c = s.createCriteria(Product.class);
		c.add(Restrictions.like("name", "%"+name+"%"));
		//表示从第2条数据开始
		c.setFirstResult(2);
		//表示一共查询5条数据 
		c.setMaxResults(3);
		
		List<Product> ps = c.list();
		for (Product p:ps){
			System.out.println(p.getName());
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
