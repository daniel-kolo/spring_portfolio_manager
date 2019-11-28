package com.controller;

import com.domain.Stock;
import com.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import viewmodel.MainSiteModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/")
public class MainController {

    private MainSiteModel mainSiteModel;

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
        }

        User u = new User();
        u.setName("testuser");
        mainSiteModel.setUser(u);

        // NOTE: Only dummy part here, model added later
        // NOTE: Deleted StockInstance, replacing with Stock, Broke shit, Sorry!!
        mainSiteModel.setRandomStocks(new Stock[] {
                new Stock("test_stock1,", "TEST1", 100.0,
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019")),
                new Stock("test_stock2,", "TEST2", 200.0,
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"))

        });

        modelAndView.addObject("model", mainSiteModel);
        return modelAndView;
    }

}
