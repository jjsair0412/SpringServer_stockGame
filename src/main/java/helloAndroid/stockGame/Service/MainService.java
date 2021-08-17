package helloAndroid.stockGame.Service;

import helloAndroid.stockGame.DTO.stockInfo;
import helloAndroid.stockGame.Entity.stockEntity;

import java.util.List;

public interface MainService {
//    stockInfo StockSave(String stock_name, int stock_price); // 다형성 구현 실패 service
//    stockInfo findStock(String stock_name); // 다형성 구현 실패 sevice

    int DbStockSave(String stock_name, int stock_price); // 새로운 종목 등록
    stockEntity DbStockfind(stockEntity stockentity); // 한가지 종목정보 조회
    List<stockEntity> DbStockfindAll();
    int DbStockUpdate(stockEntity stockentity);
}
