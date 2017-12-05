package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Category;

/**
 * lazy="true"
 * 延迟加载又叫lazyload，在one-many many-many的时候都可以使用关系的延迟加载
 * 
 * 比如有的页面只需要显示分类信息，
 * 这个时候倘若没有开启延迟加载，那么就会把分类下的产品也查询出来了，增加了不必要的开销
 * @author Administrator
 *
 */
public class TestHibernate_23 {
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		//执行该行的时候，只会查询Category表的信息，不会查询product_表
		Category c = (Category)s.get(Category.class, 1);
		
		System.out.println("log1");
		//只有在执该行，通过category取products的时候，才会进行对product_表的查询 
		System.out.println(c.getProducts());
		System.out.println("log2");
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
