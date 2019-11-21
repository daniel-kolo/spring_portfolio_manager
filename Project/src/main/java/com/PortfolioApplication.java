package com;

import com.domain.*;
import com.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PortfolioApplication implements CommandLineRunner {

	@Autowired
	StockPortfolioRepository portfolioRepo;

	@Autowired
	StockRepository stockRepo;

	@Autowired
	UserRepository userRepo;

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

		StockDownloader downloader = new StockDownloader();

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
