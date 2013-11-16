package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProductDao;
import com.model.Mobile;
import com.model.Product;

@SuppressWarnings("unused")
public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductDao pdao = (ProductDao) ctx.getBean("productDao");
		List<Product> productList = (List<Product>) pdao.selectProduct();
		for(Product p :productList){
			System.out.println(p.getModel());
		}
		
	}
}
