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

import com.model.Product;
import com.model.enums.Retailer;
import com.model.enums.Subcategory;

/**
 * Broadway Crawler</br>
 * 12/11/2013
 * 
 * @author Gang.Chen
 *
 */
public class BroadwayCrawler extends Crawler{

	public static String domain = "http://www.broadway.com.hk";
	//	Used when accessing the first page
	public static String mobilesuffix = "/hotitems_sort/101/73?type=" + Subcategory.MOBILE.getName();	//Mobile
	public static String landlinesuffix = "/hotitems_sort/101/71?type=" + Subcategory.LANDLINE.getName();	//Landline
	public static String notebooksuffix = "/hotitems_sort/103/29?type=" + Subcategory.PC.getName();	//Notebook
	public static String desktopsuffix = "/hotitems_sort/103/96?type=" + Subcategory.PC.getName();	//Desktop
	public static String printersuffix = "/hotitems_sort/103/28?type=" + Subcategory.OFFICE.getName();	//Printer
	public static String slrCamerasuffix = "/hotitems_sort/105/15?type=" + Subcategory.CAMERA.getName();	//SLR Camera
	public static String interchangeableCamerasuffix = "/hotitems_sort/105/24?type=" + Subcategory.CAMERA.getName();//Interchangeable lens Camera
	public static String portableCamerasuffix = "/hotitems_sort/105/17?type=" + Subcategory.CAMERA.getName();	//Portable Camera
	public static String videocameraCamerasuffix = "/hotitems_sort/105/30?type=" + Subcategory.CAMERA.getName();	//Video Camera
	public static String personalvideosuffix = "/hotitems_sort/104/2?type=" + Subcategory.PERSONAUDIO.getName();
	public static String homevideosuffix = "/hotitems_sort/104/183?type=" + Subcategory.HOUSEHOLDAUDIO.getName();
	public static String tvsuffix = "/hotitems_sort/104/185?type=" + Subcategory.HOUSEHOLDAUDIO.getName();
	public static String fridgesuffix = "/hotitems_sort/299/302?type=" + Subcategory.FRIDGE.getName();
	public static String washersuffix = "/hotitems_sort/299/301?type=" + Subcategory.WASHINGMACHINE.getName();
	public static String microwavesuffix = "/hotitems_sort/299/303?type=" + Subcategory.KITCHEN.getName();
	public static String airsuffix = "/hotitems_sort/299/300?type=" + Subcategory.AIRCONDITIONER.getName();
	public static String dehumidifiersuffix = "/hotitems_sort/299/300?type=" + Subcategory.HOUSEHOLD.getName();	//Reduce wet air
	public static String warmsuffix = "/hotitems_sort/299/305?type=" + Subcategory.HOUSEHOLD.getName();
	public static String householdsuffix = "/hotitems_sort/306/307?type=" + Subcategory.HOUSEHOLD.getName();
	public static String kitchensuffix = "/hotitems_sort/306/308?type=" + Subcategory.HOUSEHOLD.getName();
	public static String personalcaresuffix = "/hotitems_sort/306/309?type=" + Subcategory.PERSONALCARE.getName();
	
	public BroadwayCrawler() {
		urllist.add(domain + mobilesuffix);
		urllist.add(domain + notebooksuffix);
		urllist.add(domain + desktopsuffix);
		urllist.add(domain + slrCamerasuffix);
		urllist.add(domain + interchangeableCamerasuffix);
		urllist.add(domain + portableCamerasuffix);
		urllist.add(domain + videocameraCamerasuffix);
	}
	

	public static void main(String[] args) {
//		BroadwayCrawler nb = new BroadwayCrawler();
//		nb.startCollect();
		new BroadwayCrawler().getHotProduct();
	}

	public List<Object> getData(URL url) {
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Object> broadwayList = new ArrayList<Object>();
		Pattern tp = Pattern.compile("(.*?)type=(.*)");
		Matcher tm = tp.matcher(url.toString());
		String urltype = "";
		while (tm.find()) {
			urltype = tm.group(2);
		}
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) { // Delete useless code
				if (line.contains("product-list-table"))
					break;
			}
			while ((line = br.readLine()) != null) {
				// Product information is in the same line
				if (!line.contains("field-product-brand-value") && !line.contains("field title")
						&& !line.contains("product-price") && !line.contains("item-list")) {
					// item-list:the paging div
					continue;
				} else {
					Product broadway = new Product();
					broadway.setRetailer(Retailer.BROADWAY.getName());
					//Set specified product type, return "undefined" if not defined in ProductType
					broadway.setProductType(Subcategory.getSubcategories(urltype).getName());
					if (line.contains("<div class=\"item-list\"")) { // Bottom
						while ((line = br.readLine()) != null) {
							if ("".equals(line.trim())) {
								continue;
							}
							if (line.contains("<li class=\"pager-next\"")) {
								break;
							}
						}
						if (line != null) {
							String next = "?page=";
							Pattern np = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)");	//Get next page
							Matcher nm = np.matcher(line);
							while (nm.find()) {
								next = nm.group(1);
							}
							if(!"".equals(next)){
								pendUrls.add(domain + next);
							}
						}
					} else {
						if (line.contains("field-product-brand-value")) {
							//link and the brand
							Pattern dp = Pattern.compile("(.*?)field-product-brand-value\">(.*?)</div>(.*?)");
							Matcher dm = dp.matcher(line);
							while (dm.find()) {
								String a = dm.group(2);
								Pattern ap = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)</a>");
								Matcher am = ap.matcher(a);
								while (am.find()) {
									broadway.setLink(domain + am.group(1));	//link
									broadway.setBrand(am.group(2));			//brand
								}
							}
							//Model
							Pattern pp = Pattern.compile("(.*?)field title\">(.*?)</div>(.*?)");
							Matcher pm = pp.matcher(line);
							while (pm.find()) {
								String a = pm.group(2);
								Pattern ap = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)</a>");
								Matcher am = ap.matcher(a);
								while (am.find()) {
									broadway.setModel(am.group(2));	//model
								}
							}
							//Price
							Pattern prp = Pattern.compile("(.*?)product-price\">(.*?)</span>(.*?)");
							Matcher prm = prp.matcher(line);
							while (prm.find()) {
								String price = prm.group(2);
								price= price.replace("HKD", "").replace(",", "").trim();
								broadway.setPrice(Float.parseFloat(price));	//Price
							}
							broadwayList.add(broadway);
						}
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
		return broadwayList;
	}

	public List<Product> getHotProduct(){
		String url = "http://www.broadway.com.hk/hotitems/mb";
		String imageurl = "http://www.broadway.com.hk";
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Product> hotList = new ArrayList<Product>();
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) { // Delete useless code
				if (line.contains("field-product-image-key-fid")){	//Product list page
					break;
				}
			}
			Product hot = null;
			while ((line = br.readLine()) != null) {
				if(line.contains("panel-col-last")) {		//Hot product area end
					break;
				}
				Pattern np = Pattern.compile("(.*?)field-product-image-key-fid\"><a href=\"(.*?)\"(.*?)>(.*?)" +
						"<img src=\"(.*?)\"(.*?)<a href=\"(.*?)\">(.*?)</a>(.*?)<a href=(.*?)>(.*?)</a>(.*?)");
				Matcher nm = np.matcher(line);
				while (nm.find()) {
					String link = imageurl + nm.group(2);
					String img = nm.group(5);
					String brand = nm.group(8);
					String model = nm.group(11);
					hot = new Product();
					hot.setLink(link);
					hot.setImage(img);
					hot.setBrand(brand);
					hot.setModel(model);
				}
				if(hot != null) {
					hot.setRetailer(Retailer.BROADWAY.getName());
					if(!hotList.contains(hot)){
						hotList.add(hot);
					}
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
}
