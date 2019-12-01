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


		StockPortfolio portfolio = new StockPortfolio();
		User user = new User();

		user.setName("test_user");
		user.setUsername("test");
		user.setPassword("test");
		user.setPortfolio(portfolio);
		portfolio.setUser(user);
		portfolio.addStock("GOOG", 1 );


		userRepo.save(user);
		portfolioRepo.save(portfolio);

		System.out.println(userRepo.findAll());



	}
}
