package com.java.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;
/**
 * 首先通过Query的iterator把所有满足条件的Product的id查出来
 * 然后再通过it.next()查询每一个对象
 * 如果这个对象在缓存中，就直接从缓存中取了
 * 否则就从数据库中获取
 * N+1中的1，就是指只返回id的SQL语句，N指的是如果在缓存中找不到对应的数据，就到数据库中去查 
 * @author Administrator
 *
 */
public class TestHibernate_34 {

	@SuppressWarnings("unchecked")
	public static void main(String[]args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		String name = "iphone";
		Query q = s.createQuery("from Product p where p.name like ?");
		q.setString(0, "%"+name+"%");
		
		Iterator<Product> it = q.iterate();
		while(it.hasNext()){
			Product p = it.next();
			System.out.print(p.getName());
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
