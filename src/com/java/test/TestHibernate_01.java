package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
import com.java.pojo.Product;

/**
 * 向数据库中插入数据，单条记录插入，批量插入
 * @author asus-pc
 *
 */
public class TestHibernate_01 {
    public static void main(String[] args) {
    	
    	//获取一个SessionFactory对象
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        //通过SessionFactory获取一个Session
        Session s = sf.openSession();
        //在Session的基础上开启一个事务
        s.beginTransaction();
        
//        //创建一个临时对象
//        Product p = new Product();
//        p.setName("iphone8");
//        p.setPrice(8000);
//        //通过调用Session的save方法把对象保存到数据库
//        s.save(p);
        
        //通过Hibernate可以很方便的批量插入数据到数据库中
        for(int i = 0; i<10; i++){
        	Product p = new Product();
        	p.setName("iphone"+i);
        	p.setPrice(10*i);
        	s.save(p);
        }
        
        //提交事务
        s.getTransaction().commit();
        //关闭Session
        s.close();
        //关闭SessionFactory
        sf.close();
    }
 
}

