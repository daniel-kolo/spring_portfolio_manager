package com.controller;

import com.domain.StockDownloader;
import com.domain.StockPortfolio;
import com.domain.User;
import com.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import yahoofinance.histquotes.HistoricalQuote;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class MainController{

    @Autowired
    private  JwtAuthenticationController jwtAuthenticationController;
    @Autowired
    private StockDownloader stockDownloader;

    private Calendar cd = Calendar.getInstance();

    private MainSiteModel mainSiteModel = new MainSiteModel();
    private UserPortfolioView userPortfolioView;
    private HashMap<String, StockInfoView> viewMap = new HashMap<String, StockInfoView>();

    public MainController() {
        cd.set(2019, 01, 01);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcomePage(ModelAndView modelAndView) throws ParseException {
        modelAndView.setViewName("index");

        if (mainSiteModel.getUser() != null) {

        } else {
            viewMap = getStocks(5);
            for (String key : viewMap.keySet()){
                System.out.println(viewMap.get(key));
            }
        }
        mainSiteModel.setStocksInfo(viewMap);
        modelAndView.addObject("model", mainSiteModel);
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public  ModelAndView registerUser(ModelAndView modelAndView, UserView userView){
        System.out.println("The input was not ok checking and redirecting");
        modelAndView.setViewName("register");
        if (userView.getUserName() != null) {
            userView.setUserName(userView.getUserName());
        }
        userView.setPassword("");
        modelAndView.addObject("model", userView);
        return  modelAndView;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUserCheck(UserView userView){
        System.out.println("Id " + userView.getUserName() + " pw "  + userView.getPassword());
        if (userView.getUserName() != null) {
            userView.setUserName(userView.getUserName());
            // TODO: to other controller
            return  "redirect:/welcome";
        }
        // The form was not valid to register in db
        userView.setUserName("");
        userView.setPassword("");

        return  "redirect:/register";
    }

    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    public String logoutView()
    {
        mainSiteModel.setUser(null);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/stockEdit", method = RequestMethod.GET)
    public ModelAndView stockEdit(ModelAndView modelAndView) {
        modelAndView.setViewName("stockeditor");
        // TODO: get uid and return in the format, the last element from the portfolio
        mainSiteModel.getStocksInfo();
        EditorView editorModel = new EditorView();
        if (userPortfolioView == null) {
            userPortfolioView = new UserPortfolioView(userDummyView());
            mainSiteModel.setStocksInfo(userPortfolioView.getUserPortfolio());
        }
        editorModel.setEditable(userPortfolioView.getEditorview());
        modelAndView.addObject("model", editorModel);
        return modelAndView;
    }

    @RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
    public String stockChangeSave(ModelAndView modelAndView, EditorView editorView, HttpServletRequest request) {

        System.out.println(request.toString());

        return  "redirect:/welcome";
    }

    private StockInfoView[] userDummyView()
    {
        StockInfoView[] data = new StockInfoView[2];
        try
        {
            StockPartInfoView[] elements = {
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"), 50, 4),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/10/2019"), 60, 5),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/11/2019"), 900, 2),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/12/2019"), 50, 3),
            };
            StockPartInfoView[] elements2 = {
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"), 80, 4),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/10/2019"), 150, 5),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/11/2019"), 90, 2),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/12/2019"), 25, 3),
            };
            StockInfoView st = new StockInfoView("O love bme",elements);
            StockInfoView st2 = new StockInfoView("Boole",elements2);
            data[0] = st;
            data[1] = st2;
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return data;
    }

    private HashMap<String, StockInfoView> getStocks(int numberOfStocks) {
        HashMap<String, StockInfoView> map = new HashMap<>();
        Random rand = new Random();
        HashMap<String, String> stockIdToName = new HashMap<>();
        List<String> stockIds = stockDownloader.getTickerList();

        for (int i = 0; i < numberOfStocks; i++)
        {
            int idx = rand.nextInt(stockIds.size());
            String stockId = stockIds.get(idx);
            yahoofinance.Stock yahooStock = (yahoofinance.Stock)stockDownloader.getStockMap().get(stockId);
            try {
                List<HistoricalQuote> hist = yahooStock.getHistory(cd, Calendar.getInstance());
                StockPartInfoView[] pw = new StockPartInfoView[hist.size()];
                int j = 0;
                for (HistoricalQuote h : hist) {
                    Double price = Double.parseDouble(h.getClose().toString());
                    pw[j] = new StockPartInfoView(h.getDate().getTime(), price, 0);
                    j++;
                }
                map.put(yahooStock.getName(), new StockInfoView(stockId, pw));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
