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

	@Autowired
	TestRepo testRepo;

	@Autowired
	Test2Repo test2Repo;

	@Autowired
	ParentRepo parentRepo;

	@Autowired
	ChildRepo childRepo;


	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// TODO JPA? one-to-one, many-to-one?
		// rest api call does not show up

		// NOTE: After this, only testing, Dummy data

		//User user = new User("test_user");
		//Test test = new Test();

		//Test2 test2 = new Test2();
		//test2.setTest(test);

		//test2Repo.save(test2);

		//int id =test2.getId();

		//test2 = (Test2)test2Repo.findById(id);

		//id =test2.getId();


		Test2 test2 = new Test2();
		Test test_class = new Test( test2);
		test2.setTest(test_class);

		//test_class.setId(10);
		//test2.setId(20);


		System.out.println(test_class);
		System.out.println(test2);


		testRepo.save(test_class);
		test2Repo.save(test2);

		Parent parent = new Parent();
		Child child1 = new Child();
		Child child2 = new Child();

		parent.addChild(child1);
		child1.addParent(parent);

		parent.addChild(child2);
		child2.addParent(parent);

		parentRepo.save(parent);
		childRepo.save(child1);
		childRepo.save(child2);


		List<Parent> tmp = parentRepo.findAll();

		System.out.println(tmp.get(0).getChildren());



		for (Parent var : tmp)
		{
			System.out.println(var);
		}










		/*
		Stock stock1 = new Stock("Apple", "AAPL", 100.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));
		Stock stock2 = new Stock("Google", "GOOGL", 200.0,
				new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"));


		StockPortfolio portfolio = new StockPortfolio();
		portfolio.addStock(stock1);
		portfolio.addStock(stock2);

		User user = new User();
		user.setName("test_user");
		user.setPortfolio(portfolio);

		System.out.println(stock1);
		System.out.println(stock2);
		System.out.println(portfolio);
		System.out.println(user);

		stockRepo.save(stock1);
		stockRepo.save(stock2);
		portfolioRepo.save(portfolio);
		userRepo.save(user);


		 */
	}
}
