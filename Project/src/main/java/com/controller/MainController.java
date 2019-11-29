package com.controller;

import com.domain.Stock;
import com.domain.User;
import com.viewmodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController{

    private MainSiteModel mainSiteModel;
    private UserPortfolioView userPortfolioView;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcomePage(ModelAndView modelAndView) throws ParseException {
        modelAndView.setViewName("index");

        if (mainSiteModel == null)
        {
            mainSiteModel = new MainSiteModel();
            User u = new User();
            u.setName("testuser");
            mainSiteModel.setUser(u);
        }

        if (mainSiteModel.getUser() != null) {
            // TODO: get user stock
            if (userPortfolioView == null) {
                userPortfolioView = new UserPortfolioView(userDummyView());
            }
            mainSiteModel.setStocksInfo(userPortfolioView.getUserPortfolio());
        } else {
            // TODO: logic get some stock or user portfolio if logged in
            // Random five
            HashMap<String, StockInfoView> stocks = new HashMap<String, StockInfoView>();
            StockPartInfoView[] elements = {
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"), 50, 0),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/10/2019"), 60, 0),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/11/2019"), 900, 0),
                    new StockPartInfoView(new SimpleDateFormat("dd/MM/yyyy").parse("01/12/2019"), 50, 0),
            };
            StockInfoView st = new StockInfoView("stocky",elements);
            stocks.put(st.getStockName(), st);
            stocks.put("Just random string", st);
            stocks.put("Another one", st);
            mainSiteModel.setStocksInfo(stocks);
        }

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

        if (userPortfolioView == null) {
            userPortfolioView = new UserPortfolioView(userDummyView());
            mainSiteModel.setStocksInfo(userPortfolioView.getUserPortfolio());
        }
        modelAndView.addObject("model", mainSiteModel);
        return modelAndView;
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


}
