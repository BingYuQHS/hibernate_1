package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * delete级联
 * cascade="delete"
 * 删除分类的时候，会把分类下对应的产品都删除掉，否则只会把产品对应的cid设置为空
 */
import com.java.pojo.Category;

public class TestHibernate_24 {
	public static void main(String [] args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		//删除分类的时候，会把分类下对应的产品都删除掉
		Category c = (Category)s.get(Category.class, 1);
		s.delete(c);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
