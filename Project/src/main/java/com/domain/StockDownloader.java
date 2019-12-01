package com.domain;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

@Component
public class StockDownloader {

    private HashMap<String,String> tickerMap = new HashMap<>();
    private Map<String, Stock> stockMap = new HashMap<>();

    public StockDownloader(){
        this.downloadAllTickers();
        this.downloadAllPrices();
        System.out.println("Stocks downloaded");
    }

    public List<String> getStockNameList(){
        return new ArrayList<>(tickerMap.values());
    }

    public List<String> getTickerList(){ return new ArrayList<>(tickerMap.keySet()) ;}

    public BigDecimal getStockPriceByName(String stockName) throws IOException {
        return stockMap.get(stockName).getQuote().getPrice();
    }

    public BigDecimal getStockPriceByTicker(String ticker){
        return stockMap.get(ticker).getQuote().getPrice();
    }

    public Stock getTestTicker(String ticker){
        return stockMap.get(ticker);
    }

    public Map getStockMap(){return stockMap;}

    private void downloadAllTickers(){
        try {
            Resource resource = new ClassPathResource("tickers.csv");
            InputStream input = resource.getInputStream();
            File file = resource.getFile();
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row;
            while (( row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                tickerMap.put(data[0],data[1]);
            }
            csvReader.close();
        }
        catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }
    }

    private void downloadAllPrices(){
        String[] stockNames = new String[tickerMap.size()];
        int counter = 0;
        for ( String key : tickerMap.keySet() ) {
            stockNames[counter] = key;
            counter++;
        }
        try {
            stockMap = YahooFinance.get(stockNames);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    @Scheduled(fixedRate = 600000)
    public void refresh(){
        this.downloadAllPrices();
        System.out.println("Stock prices refreshed");
    }


}
