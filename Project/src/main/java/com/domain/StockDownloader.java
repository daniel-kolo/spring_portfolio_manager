package com.domain;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;


public class StockDownloader {

    private HashMap<String,String> tickerMap = new HashMap<>();
    private Map<String, Stock> stockMap = new HashMap<>();

    public StockDownloader(){
        this.downloadAllTickers();
        this.downloadAllPrices();
        System.out.println("Stocks downloaded");
    }

    public List<String> getStockNameList(){
        return new ArrayList<>(tickerMap.keySet());
    }

    public BigDecimal getStockPrice(String stockName) throws IOException {
        return YahooFinance.get(stockName).getQuote().getPrice();
    }

    private void downloadAllTickers(){
        try {
            Resource resource = new ClassPathResource("tickers.csv");
            InputStream input = resource.getInputStream();
            File file = resource.getFile();
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row;
            while (( row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                tickerMap.put(data[1],data[0]);
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
        for ( String key : tickerMap.values() ) {
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



}
