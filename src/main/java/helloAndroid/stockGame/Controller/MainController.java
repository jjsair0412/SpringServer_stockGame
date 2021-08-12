package helloAndroid.stockGame.Controller;

import helloAndroid.stockGame.Service.MainServiceImpl;
import helloAndroid.stockGame.DTO.stockInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/info")
public class MainController {
    private MainServiceImpl service;

    @Autowired
    public MainController(MainServiceImpl service){
        this.service = service;
    }

    // 모든 종목정보 가져오기
    @GetMapping
    @ResponseBody
    public stockInfo[] allStockInfo(){
        stockInfo stockInfo = service.StockSave("1번종목", 3000);
        stockInfo stockInfo2 = service.StockSave("2번종목", 120);
        helloAndroid.stockGame.DTO.stockInfo [] Arr1 = new stockInfo[2];
        Arr1[0] = stockInfo;
        Arr1[1] = stockInfo2;

        return Arr1;
    }

    // 각종목별 정보 가져오기 / 내가 산종목정보 포함
    @PostMapping
    @ResponseBody
    public stockInfo findStockInfo(@RequestParam("recivename") String recivename){

        service.StockSave("1번종목", 3000);
        service.StockSave("2번종목", 120);
//        log.info("recivename={}",recivename);
        log.info("recivename's info={}",service.findStock(recivename));
        return service.findStock(recivename);
    }

    @PostMapping("/toChange")
    public String changePrice(@RequestParam("change_stock_price") String changePrice){
        log.info("changePrice={}",changePrice);
        return null;
    }

}
