package com.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.Mobile;
import com.model.Retailer;

/**
 * Suning HK 
 * 
 * @author Administrator
 *
 */
public class SuningHKCrawler extends Crawler{

	private String domain = "http://www.cnsuning.com.hk/tch/products/";
	/**Suning JS Paging request*/
	private String nextPage = "http://www.cnsuning.com.hk/Connections/productList.aspx?pc=120461&b=&p=&sort=new&page=";

	public SuningHKCrawler() {
		this.HomePage = "http://www.cnsuning.com.hk/tch/products/default.aspx?c=120461";
	}
	
	public SuningHKCrawler(String homepage) {
		super(homepage);
	}
	
	public static void main(String[] args) {
		SuningHKCrawler nb = new SuningHKCrawler();
		nb.HomePage = "http://www.cnsuning.com.hk/tch/products/default.aspx?c=120461";
		nb.startCollect();
	}


	public List<Object> getData(URL url) {
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Object> suningList = new ArrayList<Object>();
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) { // Delete useless code
				if (line.contains("<ul class=\"products-list")){	//Product list page
					break;
				}
			}
			while ((line = br.readLine()) != null) {
				if (!line.contains("class=\"caption\"") && !line.contains("class=\"sprint") && !line.contains("<div class=\"pager clear")){
					continue;
				} else {
					Mobile suningHK = new Mobile();
					suningHK.setRetailer(Retailer.SUNINGHK.getName());
					String detailURL = "";
					if (line.contains("<div class=\"pager clear\"")) { // Bottom
						while ((line = br.readLine()) != null) {
							if ("".equals(line.trim())) {
								continue;
							}
							if (line.contains("<a rel=\"next")) {
								break;
							}
						}
						if(line != null){
							String next = "";
							Pattern np = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)");
							Matcher nm = np.matcher(line);
							while (nm.find()) {
								next = nm.group(1).replace("javascript:filterProduct(", "").replace(")", "");
							}
							if(!"".equals(next)){
								pendUrls.add(nextPage + next);
							}
						}
					} else {
					if(line.contains("class=\"sprint")){	//Link
						Pattern ap = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)");
						Matcher am = ap.matcher(line);
						while (am.find()) {
							detailURL = domain + am.group(1);
//							suningHK.setLink(detailURL);
							if(!detailURL.contains("javascript")){
//							System.out.println(detailURL);
							suningHK.setPrice(findDetails(new URL(detailURL)));
							}
						}
					}
					while ((line = br.readLine()) != null) {
						if(line.contains("class=\"caption\"")){		//Brand and model
							line = br.readLine().trim().replace("<div>", "").replace("</div>", "");
							suningHK.setBrand(line);
//							System.out.println(line);
							line = br.readLine().trim().replace("<div>", "").replace("</div>", "");
							suningHK.setModel(line);
//							System.out.println(line);
								break;
							}
						}
					suningList.add(suningHK);
					}
				}
			}
			System.out.println("Get web successfully! " + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return suningList;
	}

	private String findDetails(URL url){
		BufferedReader br = null;
		HttpURLConnection conn;
		String price = "";
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("鍍瑰�")){
					price = br.readLine().replace("<dd>", "").replace("</dd>", "").trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return price;
	}
	
}
