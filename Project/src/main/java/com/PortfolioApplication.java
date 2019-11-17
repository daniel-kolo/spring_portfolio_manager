package com;

import com.domain.StockDownloader;
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

	}
}
