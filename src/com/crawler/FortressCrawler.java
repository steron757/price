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
 * Fortress Crawler</br>
 * 12/11/2013
 * 
 * @author Gang.Chen
 *
 */
public class FortressCrawler extends Crawler{

	private int i = 1;
	private int pageNum;
	private String domain = "http://www.fortress.com.hk/webproxy/product_listing.php?";	//Domain for getting data
	private String link = "http://www.fortress.com.hk/tc/product/details.php?productfamily=";	//head of link
	/**
	 * filter brand followed :Mobile, Tablet PC, MP3 and multimedia player, Charger, Headphones
	 */
	private String filter = "[{\"brand\":[1]},{\"priceStart\":\"0\"},{\"priceEnd\":\"14980\"},{\"order\":\"\"}]";
	//ÇëÇóÊ¾Àý£º
	//http://www.fortress.com.hk/tc/product/webproxy/product_listing.php?request=%5B%7B%22priceStart%22%3A%220%22%7D%2C%7B%22priceEnd%22%3A%2214980%22%7D%2C%7B%22order%22%3A%22%22%7D%5D&lang=tc&categoryID=-1&brandID=-1&viewmode=_LIVE&curPage=3


	public FortressCrawler() {
		super();
		HomePage = domain + "request=";
		HomePage += com.util.CodeUtils.encodeURIComponent(filter);
		HomePage += "&lang=tc&categoryID=-1&brandID=-1&viewmode=_LIVE&curPage=";
	}

	public FortressCrawler(String homepage) {
		super(homepage);
		HomePage = domain + "request=";
		HomePage += com.util.CodeUtils.encodeURIComponent(filter);
		HomePage += "&lang=tc&categoryID=-1&brandID=-1&viewmode=_LIVE&curPage=";
	}

	public static void main(String[] args) {
		FortressCrawler nb = new FortressCrawler();
		nb.startCollect();
	}

	public List<Object> getData(URL url) {
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Object> fortressList = new ArrayList<Object>();
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				String head = line.substring(1, 200);	//Get number per page and the total amount
				Pattern pp = Pattern.compile("(.*?)\"itemPerPage\":(.*?),(.*?)Total\":(.*?),(.*?)");
				Matcher pm = pp.matcher(head);
				String itemPerPage = "";
				String total = "";
				while (pm.find()) {
					itemPerPage = pm.group(2);
					total = pm.group(4);
				}
				try {
					pageNum = Integer.parseInt(total) / Integer.parseInt(itemPerPage) + 1;	//Calculate the pages
				} catch (NumberFormatException e) {
					pageNum = 1;
					e.printStackTrace();
				}
				String[] arr = line.split("\\},\\{");
				for(String t : arr){
					Mobile fortress = new Mobile();
					fortress.setRetailer(Retailer.FORTRESS.getName());
					Pattern ap = Pattern.compile("(.*?)PRODUCTFAMILY\":\"(.*?)\",\"BRAND_NAME\":\"(.*?)\"(.*?)\"MODEL\":\"(.*?)\"(.*?)\"PRICE\":\"(.*?)\"(.*?)");
					Matcher am = ap.matcher(t);
					while (am.find()) {
						fortress.setLink(link + am.group(2));
						fortress.setBrand(am.group(3));
						fortress.setModel(am.group(5));
						fortress.setPrice(Float.parseFloat(am.group(7)));
						fortressList.add(fortress);
					}
				}
			}
			System.out.println("Get web successfully! " + url);
			if(i <= pageNum){
				pendUrls.add(HomePage + ++i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fortressList;
	}
	
}
