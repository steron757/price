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
 * Broadway
 * 
 * @author Administrator
 *
 */
public class BroadwayCrawler extends Crawler{

//	private int i = 1;
//	private int maxpage = 0;
	private String domain = "http://www.broadway.com.hk";
	List<Object> broadwayList = new ArrayList<Object>();
	
	public BroadwayCrawler() {
		this.HomePage = "http://www.broadway.com.hk/hotitems/mb";
	}
	
	public BroadwayCrawler(String homepage) {
		super(homepage);
	}

	public static void main(String[] args) {
		BroadwayCrawler nb = new BroadwayCrawler();
		nb.HomePage = "http://www.broadway.com.hk/hotitems/mb";
		nb.startCollect();
	}

	public List<Object> getData(URL url) {
		BufferedReader br = null;
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) { // Delete useless code
				if (line.contains("product-list-table"))
					break;
			}
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				//Product infos is in the same line
				if (!line.contains("field-product-brand-value") && !line.contains("field title")
						&& !line.contains("product-price")) { // title
					continue;
				} else {
					Mobile broadway = new Mobile();
					broadway.setRetailer(Retailer.BROADWAY.getName());
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
//								System.out.println(domain + am.group(1));
//								System.out.println(am.group(2));
							}
						}
						//model
						Pattern pp = Pattern.compile("(.*?)field title\">(.*?)</div>(.*?)");
						Matcher pm = pp.matcher(line);
						while (pm.find()) {
							String a = pm.group(2);
							Pattern ap = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)</a>");
							Matcher am = ap.matcher(a);
							while (am.find()) {
								broadway.setModel(am.group(2));	//model
//								System.out.println(am.group(2));
							}
						}
						//价格
						Pattern prp = Pattern.compile("(.*?)product-price\">(.*?)</span>(.*?)");
						Matcher prm = prp.matcher(line);
						while (prm.find()) {
							broadway.setPrice(prm.group(2));	//价格
//							String a = prm.group(2);
//							System.out.println(a);
						}
						broadwayList.add(broadway);
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

}
