package helloAndroid.stockGame.Service;

import helloAndroid.stockGame.Repository.MemoryRepository;
import helloAndroid.stockGame.DTO.stockInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainServiceImpl implements MainService{

    private MemoryRepository memoryRepository;

    @Autowired
    public MainServiceImpl(MemoryRepository memoryRepository){
        this.memoryRepository = memoryRepository;
    }

    @Override
    public stockInfo StockSave(String stock_name, Integer stock_price) {
        return memoryRepository.save(new stockInfo(stock_name,stock_price));
    }

    @Override
    public stockInfo findStock(String stock_name) {
        log.info("stock_name={}",stock_name);
        stockInfo byName = memoryRepository.findByName(stock_name);

        return byName;
    }
}
