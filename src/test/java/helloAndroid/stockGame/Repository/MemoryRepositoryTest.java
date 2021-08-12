package helloAndroid.stockGame.Repository;

import helloAndroid.stockGame.DTO.stockInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemoryRepositoryTest {

    private MemoryRepository memoryRepository = new MemoryRepository();
    private final Logger log = LoggerFactory.getLogger(getClass());

    @AfterEach
    void clear(){
        memoryRepository.clear();
    }

    @Test
    void save() {
        //given
        stockInfo stockInfo1 = new stockInfo("1번종목",30000);
        //when
        stockInfo save1 = memoryRepository.save(stockInfo1);
        //then
        stockInfo stockInfo1Find = memoryRepository.findByName(stockInfo1.getStock_name());
        assertThat(save1).isEqualTo(stockInfo1Find);

    }

    @Test
    void findAll() {
        //given
        stockInfo stockInfo1 = new stockInfo("1번종목",30000);
        stockInfo stockInfo2 = new stockInfo("2번종목",20000);

        stockInfo save1 = memoryRepository.save(stockInfo1);
        stockInfo save2 = memoryRepository.save(stockInfo2);

        //when
        List<stockInfo> allStockInfo = memoryRepository.findAll();

        //then
        assertThat(allStockInfo.size()).isEqualTo(2);
        assertThat(allStockInfo).contains(save1,save2);
    }

    @Test
    void update() {
        //given
        stockInfo stockInfo1 = new stockInfo("1번종목",30000);
        memoryRepository.save(stockInfo1);

        String stock1Name = stockInfo1.getStock_name();
        //when
        stockInfo updateParam = new stockInfo("2번종목", 33);
        String updateName = updateParam.getStock_name();
        Integer updatePrice = updateParam.getStock_price();
        memoryRepository.update(stock1Name,updateParam);

        //then
        assertThat(updateParam.getStock_name()).isEqualTo(updateName);
        assertThat(updateParam.getStock_price()).isEqualTo(updatePrice);
    }

}