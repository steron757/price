package com.task;

import java.util.TimerTask;

import com.crawler.BroadwayCrawler;
import com.crawler.Crawler;
import com.crawler.FortressCrawler;
import com.crawler.SuningHKCrawler;

public class GetDataTask extends TimerTask {

	public void sleep(){
		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Crawler craw = new BroadwayCrawler();
		synchronized (craw) {
			System.out.println();
			System.out.println("***********************Broadway Polling Thread************************");
			craw.startCollect();
		}
		craw = new FortressCrawler();
		synchronized (craw) {
			System.out.println("************************Fortress Polling Thread************************");
			craw.startCollect();
		}
		craw = new SuningHKCrawler();
		synchronized (craw) {
			System.out.println("***********************Suning HK Polling Thread************************");
			craw.startCollect();
		}
	}

}
