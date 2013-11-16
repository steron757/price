package com.observer;

import java.util.List;

/**
 * Product Observer</br>
 * Notified when the product lists is collected
 * 
 * @author Gang.chen
 * 
 */
public class ProductObserver implements Observable {

	public void notifyObserver(List<Object> productList) {
		if (productList != null) {
			System.out.println("Size: " + productList.size());
		}
		System.out.println("****************************Status Changed****************************");
	}

}
