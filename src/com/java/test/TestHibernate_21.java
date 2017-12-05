package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;

/**
 * 例子，执行了两个操作
 * 第一个，删除id=1的产品，这个是会成功的
 * 第二个，修改id=2的产品，使得其产品名称超过了数据库中设置的长度30，这个是会失败的。
 * 因为这两个操作都是在一个事务中，而且第二个操作失败了，所以最后的结果是两个操作都没有生效
 * @author Administrator
 *
 */
public class TestHibernate_21 {
	public static void main(String []args){
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		//删除操作正常
		Product p = (Product)s.get(Product.class, 1);
		s.delete(p);
		
		//更新操作出错
		Product p2 = (Product)s.get(Product.class, 2);
		p2.setName("长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称");
		s.update(p2);
		//由于两次操作属于同一个事物，因此都不能成功
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
