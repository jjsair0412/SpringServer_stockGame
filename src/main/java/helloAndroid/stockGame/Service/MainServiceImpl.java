package helloAndroid.stockGame.Service;

import helloAndroid.stockGame.Entity.stockEntity;
import helloAndroid.stockGame.Repository.DatabaseRepository;
import helloAndroid.stockGame.Repository.MemoryRepository;
import helloAndroid.stockGame.DTO.stockInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MainServiceImpl implements MainService{


//    private MemoryRepository memoryRepository;
    private DatabaseRepository databaseRepository;
    private stockEntity stockentity;

    @Autowired
    public MainServiceImpl(/**MemoryRepository memoryRepository, **/DatabaseRepository databaseRepository, stockEntity stockentity){
//        this.memoryRepository = memoryRepository;
        this.databaseRepository = databaseRepository;
        this.stockentity = stockentity;
    }

    /**
     * 다형성 구현 실패
    @Override
    public stockInfo StockSave(String stock_name, int stock_price) {
        return memoryRepository.save(new stockInfo(stock_name,stock_price));
    }

    @Override
    public stockInfo findStock(String stock_name) {
        log.info("stock_name={}",stock_name);
        stockInfo byName = memoryRepository.findByName(stock_name);

        return byName;
    }

     **/
    @Override
    public int DbStockSave(String stock_name, int stock_price) {
        stockentity.setStockName(stock_name);
        stockentity.setStockPrice(stock_price);

        int saveResult = databaseRepository.save(stockentity);
        if (saveResult==1){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    // 한가지 종목정보 조회
    public stockEntity DbStockfind(stockEntity stockentity) {
        return databaseRepository.stockSelect(stockentity);
    }

    @Override
    public List<stockEntity> DbStockfindAll() {
        return databaseRepository.stockFindAll();
    }

    @Override
    public int DbStockUpdate(stockEntity stockentity) {
        return databaseRepository.stockUpdate(stockentity);
    }

    // 매도
    public stockEntity toChangeStock(String ChangeStockName){
        stockentity.setStockName(ChangeStockName);

        stockEntity entity = databaseRepository.stockSelect(stockentity);
        int entityStockPrice = entity.getStockPrice();

        entity.setStockPrice(entityStockPrice+1000);
        databaseRepository.stockUpdate(entity);

        return databaseRepository.stockSelect(entity);
    }

    // 매수
    public stockEntity toSellStock(String ChangeStockName){
        stockentity.setStockName(ChangeStockName);

        stockEntity entity = databaseRepository.stockSelect(stockentity);
        int entityStockPrice = entity.getStockPrice();

        entity.setStockPrice(entityStockPrice-1000);
        databaseRepository.stockUpdate(entity);

        return databaseRepository.stockSelect(entity);
    }
}
