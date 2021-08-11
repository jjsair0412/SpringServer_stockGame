package helloAndroid.stockGame.Service;

import helloAndroid.stockGame.Repository.MemoryRepository;
import helloAndroid.stockGame.StockData.stockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
        return memoryRepository.findByName(stock_name);
    }
}
