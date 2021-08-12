package helloAndroid.stockGame.Repository;

import helloAndroid.stockGame.DTO.stockInfo;
import helloAndroid.stockGame.Entity.stockEntity;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseRepositoryTest {

    DatabaseRepository databaseRepository = new DatabaseRepository();
    stockEntity stockentity = new stockEntity();
    stockInfo stockInfo;


    @AfterEach
    void delete(){
        //given
        stockInfo = new stockInfo("주진2성",1300);

        stockentity.setStockName(stockInfo.getStock_name());
        stockentity.setStockPrice(stockInfo.getStock_price());
        //when
        databaseRepository.stockDelete(stockentity);

    }

    @BeforeEach
    void save() {
        //given
        stockInfo = new stockInfo("주진2성",1300);

        stockentity.setStockName(stockInfo.getStock_name());
        stockentity.setStockPrice(stockInfo.getStock_price());
        //when
        databaseRepository.save(stockentity);
    }

    @Test
    void stockSelect(){
        //given
        Integer stock_price = stockInfo.getStock_price();
        String stock_name = stockInfo.getStock_name();

        //when
        stockEntity stockSelectResult = databaseRepository.stockSelect(stockentity);

        //then
        assertThat(stock_name).isEqualTo(stockSelectResult.getStockName());
        assertThat(stock_price).isEqualTo(stockSelectResult.getStockPrice());

    }

    @Test
    void stockUpdate(){
        //when
        int result = databaseRepository.stockUpdate(stockentity);

        //then
        assertThat(result).isEqualTo(1);
    }

}