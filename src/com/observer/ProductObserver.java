package com.observer;

import java.util.List;

import com.model.Product;

/**
 * Product Observer</br>
 * Notified when the product lists is collected
 * 
 * @author Gang.chen
 * 
 */
public class ProductObserver implements Observable {

	/**
	 * ALL PRODUCT LIST
	 */
	public void notifyObserver(List<Object> productList) {
		if (productList == null) {
			return;
		}
		for(Object p : productList) {
			System.out.println("---------------------------");
			System.out.println(((Product) p).getBrand() + ((Product) p).getModel() + ((Product) p).getPrice());
		}

		System.out.println("****************************Status Changed****************************");
	}

	/**
	 * HOT PRODUCT LIST
	 * @param hotproductList
	 * @param i
	 */
	public void notifyHotProduct(List<Product> hotproductList) {
		if (hotproductList == null) {
			return;
		}
		for(Product p : hotproductList) {
			System.out.println("---------------------------");
			System.out.println(p.getBrand() + p.getModel() + p.getPrice());
		}

		System.out.println("****************************Hot Product Finished****************************");
	}
}
