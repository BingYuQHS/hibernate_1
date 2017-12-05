package com.java.pojo;

import java.util.Set;

/**
 * 类型属性
 * @author asus-pc
 *
 */
public class Category {
	
	int id; //类型编号
	String name;//类型名称
	
	Set<Product> products;//增加set用来存储相同Category的不同Product,相同型号的不同商品
	
	public Set<Product> getProducts(){
		return products;
	}
	public void setProducts(Set<Product> products){
		this.products = products;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
