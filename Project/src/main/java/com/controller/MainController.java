package com.controller;

import com.domain.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcomePage(ModelAndView modelAndView) throws ParseException {
        modelAndView.setViewName("index");
        // NOTE: Only dummy part here, model added later
        // NOTE: Deleted StockInstance, replacing with Stock, Broke shit, Sorry!!
        Stock[] stocks = new Stock[] {
                new Stock("test_stock1,", "TEST1", 100.0,
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019")),
                new Stock("test_stock2,", "TEST2", 200.0,
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"))

        };
        modelAndView.addObject("stocks", stocks);
        return modelAndView;
    }

}
