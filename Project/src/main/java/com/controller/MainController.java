package com.controller;

import com.DTO.StockPortfolioDTO;
import com.DTO.UserDTO;
import com.domain.StockDownloader;
import com.security.model.JwtRequest;
import com.security.model.JwtResponse;
import com.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import yahoofinance.histquotes.HistoricalQuote;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class MainController{

    @Autowired
    private  UserController userController;
    @Autowired
    private  JwtAuthenticationController jwtAuthenticationController;
    @Autowired
    private StockDownloader stockDownloader;

    private Calendar cd = Calendar.getInstance();

    private MainSiteModel mainSiteModel = new MainSiteModel();
    private UserPortfolioView userPortfolioView;
    private HashMap<String, StockInfoView> viewMap = new HashMap<String, StockInfoView>();
    // TODO: somewhere else
    private String token;
    // bearer token string just bunch of trash
    final String BEARER = "0123456";

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
            viewMap = new HashMap<>();
            if (mainSiteModel.getPortfolioDTO().getStocks() != null) {
                List<com.domain.Stock> stocks = mainSiteModel.getPortfolioDTO().getStocks();
                for (com.domain.Stock s : stocks) {
                    yahooAsk(viewMap, s.getTicker());
                }
            }
        } else {
            viewMap = getStocks(5);
        }
        mainSiteModel.setStocksInfo(viewMap);
        modelAndView.addObject("model", mainSiteModel);
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public  ModelAndView registerUser(ModelAndView modelAndView, UserView userView){
        modelAndView.setViewName("register");
        userView.setPassword("");
        modelAndView.addObject("model", userView);
        return  modelAndView;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUserCheck(UserView userView){
        if (userView.getUserName() != null && userView.getPassword() != null) {
            userView.setUserName(userView.getUserName());
            UserDTO dto = new UserDTO();
            dto.setUsername(userView.getUserName());
            dto.setPassword(userView.getPassword());
            try {
                ResponseEntity<?> response = jwtAuthenticationController.saveUser(dto);
                JwtRequest jwtRequest = new JwtRequest(dto.getUsername(), dto.getPassword());
                response = jwtAuthenticationController.createAuthenticationToken(jwtRequest);
                return getAuthenticatedUser(response);
            } catch (Exception e) {
                e.printStackTrace();
                return  "redirect:/register";
            }
        }
        return  "redirect:/register";
    }

    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    public String logoutView()
    {
        token = "";
        mainSiteModel.reset();
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/stockEdit", method = RequestMethod.GET)
    public ModelAndView stockEdit(ModelAndView modelAndView) {

        if (mainSiteModel.getUser() != null) {
            modelAndView.setViewName("stockeditor");
            // TODO: after add
            // TODO: get uid and return in the format, the last element from the portfolio
            EditorView editorModel = new EditorView();
            if (mainSiteModel.getPortfolioDTO().getStocks() != null) {
                StockInfoView[] transformed = transformToView(mainSiteModel.getPortfolioDTO());
                userPortfolioView = new UserPortfolioView(transformed);
                mainSiteModel.setStocksInfo(userPortfolioView.getUserPortfolio());
                editorModel.setEditable(userPortfolioView.getEditorview());
            }

            HashMap<String, String> tickerToName = new HashMap<>();
            for (Object id : stockDownloader.getStockMap().keySet()){
                String stockId = id.toString();
                yahoofinance.Stock yahooStock = (yahoofinance.Stock)stockDownloader.getStockMap().get(stockId);
                tickerToName.put(stockId, yahooStock.getName());
            }
            editorModel.setTickerToStockName(tickerToName);
            modelAndView.addObject("model", editorModel);
        } else {
            modelAndView.setViewName("index");
            modelAndView.addObject("model", mainSiteModel);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
    public String stockChangeSave(HttpServletRequest request) {
        // TODO: get curent protfolio numbers
        // TODO: update the portfolio controller
        // TODO: add/API call here with token
        if (mainSiteModel.getPortfolioDTO().getStocks() != null) {
            List<String> paramNames = Collections.list(request.getParameterNames());
            for (String param : paramNames) {
                System.out.println(param);
                if (param.startsWith("stockCount") && param.contains("_")) {
                    String value = request.getParameter(param);
                    System.out.println(value);
                    String tickerId = param.split("_")[1];
                    Integer currentAmount = Integer.parseInt(request.getParameter(param));
                    Integer pastValue = mainSiteModel.getPortfolioDTO().getStockMap().get(tickerId);
                    System.out.println("Past " + pastValue + " Current " + currentAmount);
                    if (pastValue != currentAmount) {
                        Integer toAdd = currentAmount - pastValue;
                        System.out.println("Input value was " + toAdd);
                        StockPortfolioDTO newDTO = userController.addStockToPortfolio(token, tickerId, toAdd);
                        mainSiteModel.setPortfolioDTO(newDTO);
                    }

                }
            }
        }
        return  "redirect:/welcome";
    }

    @RequestMapping(value = "/addStock", method = RequestMethod.POST)
    public String addStock(HttpServletRequest request) {
        if (mainSiteModel.getUser() != null) {
            List<String> paramNames = Collections.list(request.getParameterNames());
            for (String param : paramNames) {
                if (param.startsWith("stockId;")) {
                    String value = request.getParameter(param);
                    if (value.equals("on")) {
                        String tickerId = param.split(";")[1];
                        StockPortfolioDTO newDTO = userController.addStockToPortfolio(token, tickerId, 1);
                        mainSiteModel.setPortfolioDTO(newDTO);
                    }
                }
            }
            return "redirect:/stockEdit";
        }
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginUser(ModelAndView modelAndView, UserView userView){
        modelAndView.setViewName("login");
        userView.setPassword("");
        modelAndView.addObject("model", userView);
        return  modelAndView;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUserCheck(UserView userView){
        if (userView.getUserName() != null && userView.getPassword() != null) {
            userView.setUserName(userView.getUserName());
            UserDTO dto = new UserDTO();
            dto.setUsername(userView.getUserName());
            dto.setPassword(userView.getPassword());
            try {
                JwtRequest jwtRequest = new JwtRequest(dto.getUsername(), dto.getPassword());
                ResponseEntity<?> response = jwtAuthenticationController.createAuthenticationToken(jwtRequest);
                String redirection = getAuthenticatedUser(response);
                return redirection;
            } catch (Exception e) {
                e.printStackTrace();
                return  "redirect:/login";
            }
        }
        return  "redirect:/login";
    }

    private String getAuthenticatedUser(ResponseEntity<?> response) {
        UserDTO dto;
        JwtResponse resp = (JwtResponse)response.getBody();
        token = BEARER + resp.getToken();
        dto = userController.getUserByToken(token);
        StockPortfolioDTO stockDTO = userController.getPortfolioByToken(token);
        mainSiteModel.setPortfolioDTO(stockDTO);
        mainSiteModel.setUser(dto);
        return  "redirect:/welcome";
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
            yahooAsk(map, stockId);
        }
        return map;
    }

    private void yahooAsk(HashMap<String, StockInfoView> map, String stockId) {
        try {
            yahoofinance.Stock yahooStock = (yahoofinance.Stock)stockDownloader.getStockMap().get(stockId);
            List<HistoricalQuote> hist = yahooStock.getHistory(cd, Calendar.getInstance());
            StockPartInfoView[] pw = new StockPartInfoView[hist.size()];
            int j = 0;
            for (HistoricalQuote h : hist) {
                Double price = Double.parseDouble(h.getClose().toString());
                pw[j] = new StockPartInfoView(h.getDate().getTime(), price, 0);
                j++;
            }
            Integer amount = 0;
            if (mainSiteModel.getPortfolioDTO() != null && mainSiteModel.getPortfolioDTO().getStockMap() != null) {
                amount = mainSiteModel.getPortfolioDTO().getStockMap().get(stockId);
            }
            map.put(yahooStock.getName(), new StockInfoView(stockId, pw, "", amount));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StockInfoView[] transformToView(StockPortfolioDTO portfolioDTO) {
        System.out.println(portfolioDTO);
        StockInfoView[] views = new StockInfoView[portfolioDTO.getStocks().size()];
        int i = 0;
        for (com.domain.Stock st : portfolioDTO.getStocks()){
            Integer amount = portfolioDTO.getStockMap().get(st.getTicker());
            StockPartInfoView p = new StockPartInfoView();
            p.setAmount(amount);
            p.setPrice(st.getPrice());
            StockPartInfoView[] parts = new StockPartInfoView[] {p};
            String name = st.getName();
            if (name == null) {
                name = stockDownloader.getStockNameByTicker(st.getTicker());
            }
            System.out.println(stockDownloader.getStockNameByTicker(st.getTicker()));

            views[i] = new StockInfoView(name, parts, st.getTicker(), 0);
            i++;
        }
        return views;
    }

}
