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

import com.model.Mobile;
import com.model.enums.Retailer;
import com.model.enums.Subcategory;

/**
 * Suning HK Crawler</br>
 * 12/11/2013
 * 
 * @author Gang.Chen
 *
 */
public class SuningHKCrawler extends Crawler{

	/*
	 * Request format refers to cnsuning.com.hk, script.js: function filterProduct(page)
	 * 
	 * In addition, we should add parameter "type=mobile" (or camera, etc) to separate request type manually.
	 * Please note that the type=?, "?" should be the same with the Subcategory name, 
	 * program could not classify the product if type=? is missing or wrong.
	 */

	private String domain = "http://www.cnsuning.com.hk/tch/products/";
	
	/**Suning JS Paging request*/
	private String nextPage = "http://www.cnsuning.com.hk/Connections/productList.aspx?";
	
	//1st one of pages
	private String mobile = domain + "default.aspx?c=120461&type=" + Subcategory.MOBILE.getName() + "&";
	private String camera = domain + "default.aspx?c=120470&type=" + Subcategory.CAMERA.getName() + "&";
	private String fridge = domain + "default.aspx?c=120479&type=" + Subcategory.FRIDGE.getName() + "&";
	private String aircondition = domain + "default.aspx?c=120480&type=" + Subcategory.AIRCONDITIONER.getName() + "&";
	private String washer = domain + "default.aspx?c=120481&type=" + Subcategory.WASHINGMACHINE.getName() + "&";	
	private String kitchen = domain + "default.aspx?c=120482&type=" + Subcategory.KITCHEN.getName() + "&";
	private String smartkitchen = domain + "default.aspx?c=120487&type=" + Subcategory.SMARTKITCHEN.getName() + "&";
	private String household = domain + "default.aspx?c=120488&type="+ Subcategory.HOUSEHOLD.getName() + "&";
	private String personalcare = domain + "default.aspx?c=120489&type="+ Subcategory.PERSONALCARE.getName() + "&";
	private String pc = domain + "default.aspx?c=120494&type="+ Subcategory.PC.getName() + "&";
	private String tv = domain + "default.aspx?c=120473&type="+ Subcategory.TV.getName() + "&";
	private String homeaudio = domain + "default.aspx?c=120473&type="+ Subcategory.HOUSEHOLDAUDIO.getName() + "&";
	
	//Paging
	private String next_mobile = "c=120507,120508,120509&b=&p=&sort=new&type=" + Subcategory.MOBILE.getName() + "&page=";
	private String next_camera = "c=120502,120503,120504,120505&b=&p=&sort=new&type=" + Subcategory.CAMERA.getName() + "&page=";
	private String next_fridge = "c=120573,120574,120575,120576,120577&b=&p=&sort=new&type=" + Subcategory.FRIDGE.getName() + "&page=";
	private String next_air = "c=120579,120580&b=&p=&sort=new&type=" + Subcategory.AIRCONDITIONER.getName() + "&page=";
	private String next_washer = "c=120584,120585,120586,120587,120588&b=&p=&sort=new&type=" + Subcategory.WASHINGMACHINE.getName() + "&page=";
	private String next_kitchen = "c=120589&b=&p=&sort=new&type=" + Subcategory.KITCHEN.getName() + "&page=";
	private String next_smartkitchen = "c=120604,120606,120611,120612,123261&b=&p=&sort=new&type=" + Subcategory.SMARTKITCHEN.getName() + "&page=";
	private String next_household = "c=120627,120629,120630,120634,120645,120886&b=&p=&sort=new&type=" + Subcategory.HOUSEHOLD.getName() + "&page=";
	private String next_personal = "c=120637,120639,120640&b=&p=&sort=new&type=" + Subcategory.PERSONALCARE.getName() + "&page=";
	private String next_pc = "c=120542,120543,120544,120545,120546&b=&p=&sort=new&type=" + Subcategory.PC.getName() + "&page=";
	private String next_tv = "c=120554,120556,120557&b=&p=&sort=new&type=" + Subcategory.TV.getName() + "&page=";
	private String next_homeaudio = "c=120563,120564&b=&p=&sort=new&type=" + Subcategory.HOUSEHOLDAUDIO.getName() + "&page=";
	
	private Map<String, String> url_next = new HashMap<String, String>();		//Maps the next page by product type
	
	//TODO: Office product, Office product fitting, headphone/earphone is not added.
	public SuningHKCrawler() {
		urllist.add(mobile);
		urllist.add(camera);
		urllist.add(fridge);
		urllist.add(aircondition);
		urllist.add(washer);
		urllist.add(kitchen);
		urllist.add(smartkitchen);
		urllist.add(household);
		urllist.add(personalcare);
		urllist.add(pc);
		urllist.add(tv);
		urllist.add(homeaudio);
		url_next.put(Subcategory.MOBILE.getName(), next_mobile);
		url_next.put(Subcategory.CAMERA.getName(), next_camera);
		url_next.put(Subcategory.FRIDGE.getName(), next_fridge);
		url_next.put(Subcategory.AIRCONDITIONER.getName(), next_air);
		url_next.put(Subcategory.WASHINGMACHINE.getName(), next_washer);
		url_next.put(Subcategory.KITCHEN.getName(), next_kitchen);
		url_next.put(Subcategory.SMARTKITCHEN.getName(), next_smartkitchen);
		url_next.put(Subcategory.HOUSEHOLD.getName(), next_household);
		url_next.put(Subcategory.PERSONALCARE.getName(), next_personal);
		url_next.put(Subcategory.PC.getName(), next_pc);
		url_next.put(Subcategory.TV.getName(), next_tv);
		url_next.put(Subcategory.HOUSEHOLDAUDIO.getName(), next_homeaudio);
	}

	public static void main(String[] args) {
		SuningHKCrawler nb = new SuningHKCrawler();
		nb.startCollect();
	}

	public List<Object> getData(URL url) {
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Object> suningList = new ArrayList<Object>();
		Pattern tp = Pattern.compile("(.*?)type=(.*)&");
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
					suningHK.setProductType(Subcategory.getSubcategories(urltype).getName());
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
								//Find which page should be forwarded
								next = nm.group(1).replace("javascript:filterProduct(", "").replace(")", "");
							}
							if(!"".equals(next)){
								pendUrls.add(nextPage + url_next.get(urltype) + next);
							}
						}
					} else {
						if(line.contains("class=\"sprint")){	//Link
							Pattern ap = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)");
							Matcher am = ap.matcher(line);
							while (am.find()) {
								detailURL = domain + am.group(1);
								suningHK.setLink(detailURL);
								if(!detailURL.contains("javascript")){
									//Find the price from another page
									suningHK.setPrice(Float.parseFloat(findDetails(new URL(detailURL))));
								}
							}
						}
						while ((line = br.readLine()) != null) {
							if (line.contains("class=\"caption\"")) { // Brand and model
								line = br.readLine().trim().replace("<div>", "").replace("</div>", "");
								suningHK.setBrand(line);
								line = br.readLine().trim().replace("<div>", "").replace("</div>", "");
								suningHK.setModel(line);
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

	/**
	 * Find price by forwarding its detail page
	 * 
	 * @param url
	 * @return
	 */
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
				if (line.contains("HK$")){
					price = line.replace("<dd>", "").replace("</dd>", "");
					price = price.replace("HK$", "").replace(",", "").trim();
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
