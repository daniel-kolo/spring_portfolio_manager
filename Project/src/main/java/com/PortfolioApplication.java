package com;

import com.domain.StockInstance;
import com.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class PortfolioApplication implements CommandLineRunner {

	@Autowired
	private StockService stockService;

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// create default test stock instances
		stockService.createStockInstance(1,"test_stock1",
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"), 100.0);
		stockService.createStockInstance(2,"test_stock2",
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2018"), 250.0);
		stockService.createStockInstance(3,"test_stock3",
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2017"), 1000.0);
		stockService.lookup().forEach(StockInstance -> System.out.println(StockInstance));
	}
}
