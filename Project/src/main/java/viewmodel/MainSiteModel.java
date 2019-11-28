package viewmodel;

import com.domain.Stock;
import com.domain.User;

public class MainSiteModel {

    private User user;
    private Stock[] randomStocks;

    public MainSiteModel(User user, Stock[] currentShown) {
        this.user = user;
        this.randomStocks = currentShown;
    }

    public MainSiteModel(Stock[] currentShown) {
        this.user = null;
        this.randomStocks = currentShown;
    }

    public MainSiteModel() {
    }

    public User getUser() {
        return user;
    }

    public Stock[] getRandomStocks() {
        return randomStocks;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRandomStocks(Stock[] randomStocks) {
        this.randomStocks = randomStocks;
    }

}
