package com.controller;

import com.domain.Stock;
import com.repo.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StockController {

    @Autowired
    StockRepository stockRepo;

    @GetMapping("/stocks")
    public List<Stock> getStocks (){

        return stockRepo.findAll();

    }

    @GetMapping("/stock/{Id}")
    public Optional<Stock> getStock(@PathVariable("Id") int Id){
        return stockRepo.findById(Id);
    }

    @GetMapping("/stock/name/{name}")
    public Stock getStock(@PathVariable("name") String name){
        return stockRepo.findByName(name);
    }

    @PostMapping("/stock")
    public Stock addStock(@RequestBody Stock stock){
        stockRepo.save(stock);
        return stock;
    }

    @PutMapping("/stock")
    public Stock saveOrUpdateStock(@RequestBody  Stock stock){
        stockRepo.save(stock);
        return stock;
    }

    @DeleteMapping("/stock/{Id}")
    public String deleteStock(@PathVariable int Id){
        Stock stock = stockRepo.getOne(Id);
        stockRepo.delete(stock);
        return "deleted";
    }
}
