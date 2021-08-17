package helloAndroid.stockGame.Service;

import helloAndroid.stockGame.DTO.stockInfo;
import helloAndroid.stockGame.Entity.stockEntity;
import helloAndroid.stockGame.Repository.DatabaseRepository;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MainServiceImplTest {
   MainServiceImpl mainService = new MainServiceImpl(new DatabaseRepository(), new stockEntity());
   DatabaseRepository databaseRepository = new DatabaseRepository();

    stockEntity entity = new stockEntity();
    stockEntity stockEntity = new stockEntity();
   @AfterEach
   void delete(){
       stockInfo stockinfo = new stockInfo();
       stockinfo.setStock_name("주진성");
       stockinfo.setStock_price(3000);

       stockEntity entity = new stockEntity();

       entity.setStockName(stockinfo.getStock_name());
       entity.setStockPrice(stockinfo.getStock_price());

       databaseRepository.stockDelete(entity);
   }

   @BeforeEach
   void dbStockSave() {
       stockInfo stockinfo = new stockInfo();
       stockinfo.setStock_name("주진성");
       stockinfo.setStock_price(3000);


       entity.setStockName(stockinfo.getStock_name());
       entity.setStockPrice(stockinfo.getStock_price());

        //when
        int i = mainService.DbStockSave("주진성", 3000);
        //then

        assertThat(i).isEqualTo(1);

    }

    @Test
    void dbStockfind() {
       //given
        stockEntity entity = mainService.DbStockfind(this.entity);
        //when
        int entityStockPrice = entity.getStockPrice();
        String stockName = entity.getStockName();
        //then
        assertThat(entityStockPrice).isEqualTo(3000);
        assertThat(stockName).isEqualTo("주진성");
    }


    @Test
    void dbStockfindAll() {
       //given
        stockInfo stockinfo = new stockInfo();
        stockinfo.setStock_name("주진성둘");
        stockinfo.setStock_price(333);


        stockEntity.setStockName(stockinfo.getStock_name());
        stockEntity.setStockPrice(stockinfo.getStock_price());

        mainService.DbStockSave("주진성둘",333);

        //when
        List<stockEntity> stockEntities = mainService.DbStockfindAll();

        //then
        assertThat(stockEntities.size()).isEqualTo(2);

        var entity2 = stockEntities.get(0);
        assertThat(entity2.getStockName()).isEqualTo(entity.getStockName());
        assertThat(entity2.getStockPrice()).isEqualTo(entity.getStockPrice());

        var entity3 = stockEntities.get(1);
        assertThat(entity3.getStockName()).isEqualTo(stockEntity.getStockName());
        assertThat(entity3.getStockPrice()).isEqualTo(stockEntity.getStockPrice());

        databaseRepository.stockDelete(stockEntity);

    }


    @Test
    void dbStockUpdate() {
       //given
        stockEntity updateParam = new stockEntity();
        updateParam.setStockName("주진성");
        updateParam.setStockPrice(4000);
        //when
        int i = mainService.DbStockUpdate(updateParam);
        var updateAfterFind = mainService.DbStockfind(updateParam);
        //then
        assertThat(i).isEqualTo(1);
        assertThat(updateAfterFind.getStockName()).isEqualTo("주진성");
        assertThat(updateAfterFind.getStockPrice()).isEqualTo(4000);

    }
}