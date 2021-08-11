package helloAndroid.stockGame.Service;

import helloAndroid.stockGame.StockData.stockInfo;

public interface MainService {
    stockInfo StockSave(String stock_name, Integer stock_price);
    stockInfo findStock(String stock_name);
}
