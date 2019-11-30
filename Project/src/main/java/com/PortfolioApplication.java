package com;

import com.domain.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EnableScheduling
@SpringBootApplication
public class PortfolioApplication implements CommandLineRunner {

	@Autowired
	StockPortfolioRepository portfolioRepo;

	@Autowired
	StockRepository stockRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	StockDownloader downloader;






	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	// TODO
	/*
	user/logout
	user/login
	user/register

	email
	password


	Json web token
	user + rank

	email
	 */

	@Override
	public void run(String... args) throws Exception {

		//Map<String, yahoofinance.Stock> map = downloader.getStockPriceByTicker("GOOG=GOOG");

		/*
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(map.toString());
		String prettyJsonString = gson.toJson(je);
		*/
		//System.out.println(prettyJsonString);

		//System.out.println(map.values().toArray()[0].getQuote());

		//System.out.println(downloader.getStockNameList());


		//System.out.println(downloader.getStockPriceByTicker("IPG"));

		// Adding basic test objects of User, StockPortfolio, Stock types
		StockPortfolio portfolio = new StockPortfolio();


		//System.out.println(downloader.getStockPriceByTicker("GOOG"));

		//System.out.println(downloader.getStockMap().get("IPG"));
		//System.out.println(downloader.getStockMap().get("CA"));
		//System.out.println(downloader.getStockMap().get("GOOG"));



		//System.out.println(downloader.getTestTicker("CA"));
		//portfolio.addStock("CA", 1);

		// TODO
		//System.out.println(downloader);
		portfolio.setUser(new User());
		//
		// portfolio.addStock("GOOG", 1);

		StockPriceService stockPriceService = new StockPriceService();
		System.out.println(BeanUtil.getBean(StockDownloader.class).getStockPriceByTicker("GOOG"));

		User user = new User();
		user.setName("test_user");
		user.setUsername("test");
		user.setPassword("test");
		user.setPortfolio(portfolio);

		userRepo.save(user);
		portfolioRepo.save(portfolio);


	}
}
