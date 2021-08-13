package helloAndroid.stockGame.Repository;

import helloAndroid.stockGame.DTO.stockInfo;
import helloAndroid.stockGame.Entity.stockEntity;
import helloAndroid.stockGame.Repository.RepositoryInterface.RepositoryInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MemoryRepository implements RepositoryInter {
    private static Map<String, stockInfo> store = new HashMap<>();
    private static Map<String, stockEntity> store2 = new HashMap<>(); // 다형성 구현 실패로인한 메모리

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

    @Override
    public int save(stockEntity entity) {
        store2.put(entity.getStockName(), entity);
        boolean contains = store2.containsKey(entity.getStockName());
        if (contains==true){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public stockEntity stockSelect(stockEntity entity) {
        return store2.get(entity.getStockName());
    }

    @Override
    public int stockUpdate(stockEntity entity) {
        stockEntity updateEntity = store2.get(entity.getStockName());
        updateEntity.setStockName(entity.getStockName());
        updateEntity.setStockPrice(entity.getStockPrice());

        int entityStockPrice = entity.getStockPrice();
        int updateResultPrice = updateEntity.getStockPrice();

        if (entityStockPrice==updateResultPrice){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int stockDelete(stockEntity entity) {
        store2.remove(store2.get(entity.getStockName()));
        boolean result = store2.containsKey(entity.getStockName());
        if (result == true){
            return 1;
        }else{
            return 0;
        }

    }
}
