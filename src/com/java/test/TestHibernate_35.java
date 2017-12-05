package com.java.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate_35 {

	public static void main(String[]args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		String name = "iphone";
		
		//据这条SQL语句创建一个Query对象，调用Query对象的uniqueResult()方法，返回一个long型的数据，即查询总数。
		//这是HQL的写法，用的是类名，真正执行的SQL会自动转换为表名的
		Query q = s.createQuery("select count(*) from Product p where p.name like ?");
		q.setString(0, "%"+name+"%");
		long total = (Long)q.uniqueResult();
		System.out.println(total);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
		System.out.println("第一次修改提交Git!");
	}
}
