package com.task;

import java.util.TimerTask;

import com.crawler.BroadwayCrawler;
import com.crawler.Crawler;
import com.crawler.FortressCrawler;
import com.crawler.SuningHKCrawler;

/**
 * Task to acquire data from website or API</br>
 * 
 * 15/11/2013
 * @author Gang.Chen
 *
 */
public class GetDataTask extends TimerTask {

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
