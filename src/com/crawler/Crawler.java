package com.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.Product;
import com.observer.ProductObserver;

/**
 * Crawler</br>
 * Main job is collect data via URL, and its subclasses </br>
 * is responsible for convert data to Object</br>
 * 
 * 12/11/2013
 * 
 * @author Gang.Chen
 *
 */
public class Crawler extends ProductObserver{

	/** URL in pending */
	List<String> pendUrls = new ArrayList<String>();
	/** Home page */
	String HomePage;

	public static List<String> urllist = new ArrayList<String>();
	
	public List<Object> productList;
	public List<Product> hotproductList;
	
	public Crawler(String homepage) {
		super();
		this.HomePage = homepage;
	}
	
	public Crawler() {
		super();
		urllist.clear();
	}
	
	public String getDomain() {
		String reg = "(?<=http\\://[a-zA-Z0-9]{0,100}[.]{0,1})[^.\\s]*?\\.(com|cn|net|org|biz|info|cc|tv)";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(HomePage);
		boolean blnp = m.find();
		if (blnp == true) {
			return m.group(0);
		}
		return null;
	}
	
	public synchronized void startCollect() {
		for(String urls : urllist){
			pendUrls.add(urls);
			System.out.println("Start!");
			String tmp = getAUrl();
			if(tmp != null){
				URL url = null;
				try {
					url = new URL(tmp);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				productList = this.getData(url);
			}
			while (!pendUrls.isEmpty()) {
				try {
					String tmp2 = getAUrl();
					if(tmp2 != null){
						URL url = null;
						url = new URL(tmp2);
						List<?> next = this.getData(url);
						for(Object p : next) {
							productList.add(p);
						}
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
			this.notifyObserver(productList);
		}
		
		hotproductList = this.getHotProduct();
		this.notifyHotProduct(hotproductList);
	}


	public synchronized String getAUrl() {
		String tmpAUrl = null;
		try {
			tmpAUrl = pendUrls.get(0);
		} catch (Exception e) {
			return null;
		}
		pendUrls.remove(0);
		return tmpAUrl;
	}

	public synchronized List<Product> getHotProduct() {
		return hotproductList;
	}
	
	public synchronized List<Object> getData(URL url) {
		return productList;
	}
	

}