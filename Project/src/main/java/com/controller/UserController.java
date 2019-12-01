package com.controller;

import com.DTO.StockPortfolioDTO;
import com.DTO.UserDTO;
import com.domain.StockPortfolio;
import com.domain.User;
import com.repo.StockPortfolioRepository;
import com.repo.StockRepository;
import com.repo.UserRepository;
import com.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    StockPortfolioRepository portfolioRepo;

    @Autowired
    JwtTokenUtil tokenUtil;

    @Autowired
    StockRepository stockRepo;

    @GetMapping("/user")
    public UserDTO getUserByToken(@RequestHeader (name="Authorization") String token){
        String jwtToken = token.substring(7);
        String username = tokenUtil.getUsernameFromToken(jwtToken );
        System.out.println(userRepo.findByUsername(username));
        return new UserDTO(userRepo.findByUsername(username));
    }

    @GetMapping("/user/portfolio")
    public StockPortfolioDTO getPortfolioByToken(@RequestHeader (name="Authorization") String token){
        String jwtToken = token.substring(7);
        String username = tokenUtil.getUsernameFromToken(jwtToken );
        return new StockPortfolioDTO(userRepo.findByUsername(username).getPortfolio()) ;
    }

    @PostMapping("/user/portfolio/add")
    public StockPortfolioDTO addStockToPortfolio(@RequestHeader (name="Authorization") String token,
                                                 @RequestHeader (name="Ticker") String ticker,
                                                 @RequestHeader (name="Number") Integer number ){
        String jwtToken = token.substring(7);
        String username = tokenUtil.getUsernameFromToken(jwtToken );
        User user  = userRepo.findByUsername(username);
        StockPortfolio portfolio = user.getPortfolio();
        portfolio.addStock(ticker, number);
        user.setPortfolio(portfolio);
        userRepo.save(user);
        portfolioRepo.save(portfolio);
        return new StockPortfolioDTO(portfolio);
    }

    @PutMapping("/user")
    public UserDTO saveOrUpdateUser(@RequestBody  User user){
        userRepo.save(user);
        return new UserDTO(user);
    }

    @DeleteMapping("/user/{Id}")
    public String deleteUser(@PathVariable int Id){
        User user = userRepo.getOne(Id);
        userRepo.delete(user);
        return "deleted";
    }



}
