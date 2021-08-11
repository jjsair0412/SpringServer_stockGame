package helloAndroid.stockGame.Controller;

import helloAndroid.stockGame.Service.MainServiceImpl;
import helloAndroid.stockGame.StockData.stockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private MainServiceImpl service;

    @Autowired
    public MainController(MainServiceImpl service){
        this.service = service;
    }

    // 모든 종목정보 가져오기
    @GetMapping("/info")
    @ResponseBody
    public stockInfo[] allStockInfo(){
        stockInfo stockInfo = service.StockSave("1번종목", 30000);
        stockInfo stockInfo2 = service.StockSave("2번종목", 30000);
        helloAndroid.stockGame.StockData.stockInfo [] Arr1 = new stockInfo[2];
        Arr1[0] = stockInfo;
        Arr1[1] = stockInfo2;

        return Arr1;
    }
}
