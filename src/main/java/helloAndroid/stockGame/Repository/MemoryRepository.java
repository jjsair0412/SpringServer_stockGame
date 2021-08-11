package helloAndroid.stockGame.Repository;

import helloAndroid.stockGame.StockData.stockInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryRepository {
    private static Map<String, stockInfo> store = new HashMap<>();

    // 저장
    public stockInfo save(stockInfo stockinfo){
        store.put(stockinfo.getStock_name(),stockinfo);
        return stockinfo;
    }

    // 각각 종목정보
    public stockInfo findByName(String stock_name){
        return store.get(stock_name);
    }

    // 전체종목정보
    public List<stockInfo> findAll(){
        return new ArrayList<>(store.values());
    }

    // 종목정보 업데이트
    public void update(String stock_name, stockInfo updateParam){
        stockInfo stockFind = findByName(stock_name);
        stockFind.setStock_price(updateParam.getStock_price());
        stockFind.setStock_name(updateParam.getStock_name());
    }

    // 테스트용
    public void clear(){
        store.clear();
    }
}
