package com.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.Product;
import com.model.enums.Retailer;
import com.model.enums.Subcategory;
import com.util.CodeUtils;


/**
 * Fortress Crawler</br>
 * 12/11/2013
 * 
 * @author Gang.Chen
 *
 */
public class FortressCrawler extends Crawler{

	public int i = 1;
	private int pageNum;
	private String domain = "http://www.fortress.com.hk/webproxy/product_listing.php?";	//Domain for getting data
	private String link = "http://www.fortress.com.hk/tc/product/details.php?productfamily=";	//head of link
	
	private String urlsuffix = "&lang=tc&categoryID=-1&brandID=-1&viewmode=_LIVE&curPage=";
	/**
	 * filter brand definition please refer to fortress document</br>
	 * Don't use it directly and use getFilter() instead, and set "productType" appropriately before using getFilter().
	 */
	private String filter = "";
	
	private String productType = "";
	private Map<String, Subcategory> category = new HashMap<String, Subcategory>();
	private Map<String, String> classfy = new HashMap<String, String>();
	
	//request for example, refers to listing.php
	//http://www.fortress.com.hk/tc/product/webproxy/product_listing.php?request=%5B%7B%22priceStart%22%3A%220%22%7D%2C%7B%22priceEnd%22%3A%2214980%22%7D%2C%7B%22order%22%3A%22%22%7D%5D&lang=tc&categoryID=-1&brandID=-1&viewmode=_LIVE&curPage=3

	public String getFilter() {
		filter = "[{\"brand\":[" + productType + "]},{\"priceStart\":\"0\"},{\"priceEnd\":\"999999\"},{\"order\":\"\"}]";
		return filter;
	}

	public FortressCrawler() {
		super();
		setRelation();
		setClassify();
		for(String classes : category.keySet()){
			productType = classes;
			String requesturl = domain + "mainCategoryID=" + classfy.get(classes) + "&request=" 
					+ CodeUtils.encodeURIComponent(getFilter()) + urlsuffix + "&type=" + category.get(classes).getName();
			urllist.add(requesturl);
		}
	}


	public static void main(String[] args) {
//		FortressCrawler nb = new FortressCrawler();
//		nb.startCollect();
		new FortressCrawler().getHotProduct();
	}

	public List<Product> getHotProduct(){
		String url = "http://www.fortress.com.hk/tc/home/";
		String linkurl = "http://www.fortress.com.hk/tc";
		String imageurl = "http://www.fortress.com.hk";
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Product> hotList = new ArrayList<Product>();
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) { // Delete useless code
				if (line.contains("pagination hot")){	//Hot Product list page
					break;
				}
			}
			Product hot = null;
			while ((line = br.readLine()) != null) {
				if(line.contains("section promotions")) {		//Hot product area end
					break;
				}
//				System.out.println(line);
				Pattern np = Pattern.compile("<a href=\"..(.*?)>");
				Matcher nm = np.matcher(line);
				while (nm.find()) {
//					System.out.println(line);
					String link = linkurl + nm.group(1);
					hot = new Product();
					hot.setLink(link);
					while ((line = br.readLine()) != null) {
						if(line.contains("</a>")) {
							break;
						}
						Pattern p = Pattern.compile("<img data-src=\"(.*?)\"(.*?)");
						Matcher m = p.matcher(line);
						while (m.find()) {
							hot.setImage(imageurl + m.group(1));
						}
						p = Pattern.compile("(.*?)class=\"productName\">(.*?)</(.*?)");
						m = p.matcher(line);
						while (m.find()) {
							hot.setModel(m.group(2));
						}
						p = Pattern.compile("(.*?)class=\"productDes\">(.*?)</(.*?)");
						m = p.matcher(line);
						while (m.find()) {
							hot.setDescription(m.group(2));
						}
						p = Pattern.compile("(.*?)class=\"productPrice\">(.*?)</(.*?)");
						m = p.matcher(line);
						while (m.find()) {
							hot.setPrice(Float.parseFloat(m.group(2).replace("$", "").replace(",", "")));
						}
					}
				}
				if(hot != null) {
					hot.setRetailer(Retailer.SUNINGHK.getName());
					hotList.add(hot);
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
		return hotList;
	}
	public List<Object> getData(URL url) {
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Object> fortressList = new ArrayList<Object>();
		Pattern tp = Pattern.compile("(.*?)type=(.*)");
		Matcher tm = tp.matcher(url.toString());
		String urltype = "";
		while (tm.find()) {
			urltype = tm.group(2);
		}
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
					Product fortress = new Product();
					fortress.setRetailer(Retailer.FORTRESS.getName());
					fortress.setProductType(Subcategory.getSubcategories(urltype).getName());
					Pattern ap = Pattern.compile("(.*?)PRODUCTFAMILY\":\"(.*?)\",\"BRAND_NAME\":\"(.*?)\"(.*?)\"MODEL\":\"(.*?)\"(.*?)\"PRICE\":\"(.*?)\"(.*?)");
					Matcher am = ap.matcher(t);
					while (am.find()) {
						fortress.setLink(link + am.group(2));
						fortress.setBrand(CodeUtils.unicodeToString(am.group(3)));
						fortress.setModel(am.group(5));
						fortress.setPrice(Float.parseFloat(am.group(7)));
						fortressList.add(fortress);
					}
				}
			}
			System.out.println("Get web successfully! " + url);
			if(i <= pageNum){
				pendUrls.add(url.toString().split("curPage")[0] + "curPage=" + ++i);
			}else{
				i = 1;		//Reset the page when acquire data ended
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

	/**
	 *  Refer to fortress doc 
	 */
	private void setRelation(){
		classfy.put("1", "1");
		classfy.put("2", "1");
		classfy.put("3", "1");
		classfy.put("4", "1");
		classfy.put("5", "1");
		classfy.put("6", "2");
		classfy.put("7", "2");
		classfy.put("8", "2");
		classfy.put("9", "2");
		classfy.put("10", "2");
		classfy.put("11", "3");
		classfy.put("12", "3");
		classfy.put("13", "3");
		classfy.put("14", "3");
		classfy.put("15", "3");
		classfy.put("16", "4");
		classfy.put("17", "4");
		classfy.put("18", "4");
		classfy.put("19", "4");
		classfy.put("20", "4");
		classfy.put("21", "4");
		classfy.put("22", "4");
		classfy.put("23", "4");
		classfy.put("24", "4");
		classfy.put("25", "4");
		classfy.put("26", "5");
		classfy.put("27", "5");
		classfy.put("28", "5");
		classfy.put("29", "5");
		classfy.put("30", "5");
		classfy.put("31", "5");
		classfy.put("32", "5");
		classfy.put("33", "5");
		classfy.put("34", "5");
		classfy.put("35", "5");
		classfy.put("36", "6");
		classfy.put("37", "6");	
		classfy.put("38", "6");	
		classfy.put("39", "6");	
		classfy.put("40", "6");	
		classfy.put("41", "6");	
		classfy.put("42", "6");	
		classfy.put("43", "6");	
		classfy.put("44", "6");	
		classfy.put("45", "6");	
		classfy.put("46", "6");	
		classfy.put("47", "6");	
		classfy.put("48", "6");	
		classfy.put("49", "6");	
		classfy.put("50", "6");	
		classfy.put("51", "6");	
		classfy.put("52", "6");	
		classfy.put("53", "7");	
		classfy.put("54", "7");	
		classfy.put("55", "7");	
		classfy.put("56", "7");	
		classfy.put("57", "7");	
		classfy.put("58", "7");	
		classfy.put("59", "7");	
		classfy.put("60", "7");	
		classfy.put("61", "7");	
	}
	
	/**
	 * Classify the products, each item would be added into request URL.
	 */
	private void setClassify(){
		category.put("1", Subcategory.MOBILE);
		category.put("2", Subcategory.PC);
		category.put("10", Subcategory.OFFICE);
		category.put("8", Subcategory.HOUSEHOLDAUDIO);
		category.put("7", Subcategory.HOUSEHOLDAUDIO);
		category.put("6", Subcategory.TV);
		category.put("12", Subcategory.CAMERA);
		category.put("13", Subcategory.CAMERA);
		category.put("14", Subcategory.CAMERA);
		category.put("15", Subcategory.CAMERA);
		category.put("11", Subcategory.CAMERA);
		category.put("16", Subcategory.PC);
		category.put("18", Subcategory.PC);
		category.put("20", Subcategory.OFFICE);
		category.put("35", Subcategory.AIRCONDITIONER);
		category.put("32", Subcategory.WASHINGMACHINE);
		category.put("30", Subcategory.FRIDGE);
		category.put("29", Subcategory.WASHINGMACHINE);
		category.put("26", Subcategory.KITCHEN);
		category.put("34", Subcategory.HOUSEHOLD);
		category.put("33", Subcategory.HOUSEHOLD);
		category.put("44", Subcategory.SMARTKITCHEN);
		category.put("52", Subcategory.LANDLINE);
		category.put("51", Subcategory.HOUSEHOLD);
		category.put("49", Subcategory.SMARTKITCHEN);
		category.put("48", Subcategory.SMARTKITCHEN);
		category.put("47", Subcategory.SMARTKITCHEN);
		category.put("45", Subcategory.HOUSEHOLD);
		category.put("38", Subcategory.SMARTKITCHEN);
		category.put("57", Subcategory.PERSONALCARE);
		category.put("54", Subcategory.PERSONALCARE);
	}
}
