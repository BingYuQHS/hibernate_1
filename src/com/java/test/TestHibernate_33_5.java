package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.pojo.Product;
 /**
  * 在事务关闭后，试图关闭session,就会报session已经关闭的异常 
  * @author Administrator
  *
  */
public class TestHibernate_33_5 {
    public static void main(String[] args) throws InterruptedException {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s1 = sf.getCurrentSession();
        s1.beginTransaction();
        s1.get(Product.class, 5);
        s1.getTransaction().commit();      
//        s1.close();
        sf.close();
    }

}

