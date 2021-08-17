package helloAndroid.stockGame.Controller;

import helloAndroid.stockGame.Entity.stockEntity;
import helloAndroid.stockGame.Repository.DatabaseRepository;
import helloAndroid.stockGame.Service.MainServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {
    stockEntity entity = new stockEntity();
    MainController mainController = new MainController(new MainServiceImpl(new DatabaseRepository(), entity),entity);

    @Test
    void allStockInfo() {
    }

    @Test
    void findStockInfo() {
    }

    @Test
    void changePrice() {
    }

    @Test
    void sellChangePrice() {
    }
}