package com.services;

import com.domain.StockInstance;
import com.repo.StockInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StockService {

    private StockInstanceRepository stockInstanceRepository;

    @Autowired
    public StockService(StockInstanceRepository stockInstanceRepository) {
        this.stockInstanceRepository = stockInstanceRepository;
    }

    public StockInstance createStockInstance(Integer id, String stockInstanceName, Date date, Double price){
        if (!stockInstanceRepository.existsById(id)) {
            stockInstanceRepository.save(new StockInstance(id, stockInstanceName, date, price));
        }
        return null ;
    }

    public Iterable<StockInstance> lookup(){
        return stockInstanceRepository.findAll();
    }

    public long total(){
        return stockInstanceRepository.count();
    }





}
