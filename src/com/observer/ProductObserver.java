package com.observer;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProductDao;
import com.model.Product;

/**
 * Product Observer</br>
 * Notified when the product lists is collected
 * 
 * @author Gang.chen
 * 
 */
public class ProductObserver implements Observable {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	ProductDao pdao = (ProductDao) ctx.getBean("productDao");
	
	public void notifyObserver(List<Object> productList) {
		if (productList == null) {
			return;
		}
		for(Object p : productList) {
			System.out.println("---------------------------");
			System.out.println(((Product) p).getBrand() + ((Product) p).getModel() + ((Product) p).getPrice());
		}
		for(Object o : productList){
			Product p = (Product) o;
			if("http://www.fortress.com.hk/tc/product/details.php?productfamily=ASENMOMENTUMBK".equals(p.getLink())){
				pdao.insertProduct(p);
			}else
			pdao.insertProduct(p);
		}

		System.out.println("****************************Status Changed****************************");
	}

}
