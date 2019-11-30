package com.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockPriceService {

    @Autowired
    StockDownloader downloader;

    public double getStockPrice(String ticker){
        System.out.println(downloader);
        //return downloader.getStockPriceByTicker(ticker).doubleValue();
        return 10.0;
    }
}
