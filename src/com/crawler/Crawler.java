package com.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.Product;

public class Crawler {

	/** URL in pending */
	List<String> pendUrls = new ArrayList<String>();
	/** Home page */
	String HomePage;
	
	public static List<Object> productList;
	
	public Crawler(String homepage) {
		super();
		this.HomePage = homepage;
	}
	
	public Crawler() {
		super();
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
	
	public void startCollect() {
		pendUrls.add(HomePage);
		System.out.println("Start!");
		String tmp = getAUrl();
		if(tmp != null){
			URL url = null;
			try {
				url = new URL(tmp);
				// System.out.println(url.toString());System.exit(0);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			productList = this.getData(url);
			for(Object p : productList) {
				System.out.println("---------------------------");
				System.out.println(((Product) p).getBrand() + ((Product) p).getModel() + ((Product) p).getPrice());
			}
		}
		int i = 0;
		for (i = 0; i < 8; i++) {
			new Thread(new Processer(this)).start();
		}
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

	public List<Object> getData(URL url) {
		return productList;
	}
	
	class Processer implements Runnable {
		Crawler nb;
		String s;

		public Processer(Crawler nb) {
			this.nb = nb;
		}

		public Processer(Crawler nb, String s) {
			this.nb = nb;
			this.s = s;
		}

		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			while (!pendUrls.isEmpty()) {
				try {
					String tmp = getAUrl();
					if(tmp != null){
						URL url = null;
						url = new URL(tmp);
						List<?> next = nb.getData(url);
						for(Object p : next) {
							productList.add(p);
							System.out.println("---------------------------");
							System.out.println(((Product) p).getBrand() + ((Product) p).getModel() + ((Product) p).getPrice());
						}
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		}
	}

}