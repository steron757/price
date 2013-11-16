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

/**
 * YiXun
 * 
 * @author Administrator
 *
 */
public class YiXunCrawler extends Crawler{

	private int i = 1;
	private int maxpage = 0;

	public YiXunCrawler() {
		super();
		this.HomePage = "http://searchex.yixun.com/html?YTAG=1.100085000&path=705852t705856&page=";
	}

	public YiXunCrawler(String homepage) {
		super(homepage);
	}
	
	public static void main(String[] args) {
		YiXunCrawler nb = new YiXunCrawler();
		nb.HomePage = "http://searchex.yixun.com/html?YTAG=1.100085000&path=705852t705856&page=";
		nb.startCollect();
	}

	public List<Object> getData(URL url) {
		BufferedReader br = null;
		HttpURLConnection conn;
		List<Object> yixunList = new ArrayList<Object>();
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("contentType", "utf-8");
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) { // Delete useless code
				if (line.contains("<ul class=\"goods_ul\" id=\"itemList\">"))
					break;
			}
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				if (!line.contains("<p class=\"mod_goods_tit\"") && !line.contains("<span class=\"mod_price\"")
						&& !line.contains("mod_page")) { // title
					continue;
				} else {
					System.out.println("----------------------------------------------------");
					line = line.replace("<p class=\"mod_goods_tit\">", "").replace("</p>", "").trim();
					Pattern ap = Pattern.compile("<a\\s.*?href=\"([^\"]+)\"[^>]*>(.*?)</a>");
					Matcher am = ap.matcher(line);
					while (am.find()) {
						System.out.println(am.group(1));
						System.out.println(am.group(2));
					}
					if (line.contains("<span class=\"mod_price\"")) { // price
						while (!line.contains("<span>") && !line.contains("<div class=\"mod_page\"")) {
							line = br.readLine();
						}
						line = line.replace("<span>", "").replace("</span>", "").replace(" ", "");
						line = line.replace("<i>&yen;</i>", "");
						System.out.println(line);
						maxpage++;
					}
					if (line.contains("mod_page")) { // Bottom
						if (String.valueOf(i).equals(maxpage)) {
							break;
						}
						i++;
						pendUrls.add(HomePage + i);
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
		return yixunList;
	}

}
