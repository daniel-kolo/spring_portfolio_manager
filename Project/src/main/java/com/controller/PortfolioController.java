package com.controller;

import com.domain.Stock;
import com.domain.StockPortfolio;
import com.repo.StockPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    StockPortfolioRepository portfolioRepo;

    @GetMapping("/portfolios")
    public List<StockPortfolio> getPortfolios (){

        return portfolioRepo.findAll();

    }



}
