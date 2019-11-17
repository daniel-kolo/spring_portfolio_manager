package com;

import com.domain.*;
import com.repo.StockPortfolioRepository;
import com.repo.StockRepository;
import com.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

	@Override
	public void run(String... args) throws Exception {


		// NOTE: After this, only testing, Dummy data
		StockPortfolio portfolio = new StockPortfolio();
		Stock stock1 = new Stock("Apple", "AAPL", 100.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));
		Stock stock2 = new Stock("Google", "GOOGL", 200.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));

		User user = new User("test_user");
		user.setPortfolio(portfolio);
		userRepo.save(user);



		/*
		portfolio.addStock(stock1);
		portfolio.addStock(stock2);

		user.setPortfolio(portfolio);

		stockRepo.save(stock1);
		stockRepo.save(stock2);

		System.out.println(stock1);
		System.out.println(stock2);

		List<Stock> stocks = portfolio.getStocks();

		System.out.println(portfolio);
		System.out.println(stocks);

		portfolioRepo.save(portfolio);

		*/

	}
}
