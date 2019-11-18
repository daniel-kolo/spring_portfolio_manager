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

	@Override
	public void run(String... args) throws Exception {


		// NOTE: After this, only testing, Dummy data

		Stock stock1 = new Stock("Apple", "AAPL", 100.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));
		Stock stock2 = new Stock("Google", "GOOGL", 200.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));


		StockPortfolio portfolio = new StockPortfolio();
		portfolio.addStock(stock1);
		portfolio.addStock(stock2);

		User user = new User();
		user.setPortfolio(portfolio);
		System.out.println(user);
		stockRepo.save(stock1);
		stockRepo.save(stock2);
		portfolioRepo.save(portfolio);
		userRepo.save(user);

	}
}
