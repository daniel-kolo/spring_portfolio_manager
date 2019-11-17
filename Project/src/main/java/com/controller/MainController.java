package com.controller;

import com.domain.StockInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

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
        StockInstance[] stocks = new StockInstance[] {
                new StockInstance(1,"test_stock1",
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"), 100.0
                ),
                new StockInstance(2,"test_stock2",
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"), 250.0
                ),
                new StockInstance(1,"test_stock3",
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019"), 1000.0
                ),
        };
        modelAndView.addObject("stocks", stocks);
        return modelAndView;
    }

}
