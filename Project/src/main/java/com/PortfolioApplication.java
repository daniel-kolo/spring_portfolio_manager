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
		System.out.println(downloader.getStockPriceByTicker("IPG"));

		// Adding basic test objects of User, StockPortfolio, Stock types
		StockPortfolio portfolio = new StockPortfolio();
		Stock stock1 = new Stock("Apple", "AAPL", 100.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));
		Stock stock2 = new Stock("Google", "GOOGL", 200.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));

		portfolio.addStock(stock1);
		stock1.setPortfolio(portfolio);

		portfolio.addStock(stock2);
		stock2.setPortfolio(portfolio);

		User user = new User();
		user.setName("test_user");
		user.setPortfolio(portfolio);

		userRepo.save(user);
		portfolioRepo.save(portfolio);
		stockRepo.save(stock1);
		stockRepo.save(stock2);

	}
}
