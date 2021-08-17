package helloAndroid.stockGame.Controller;

import helloAndroid.stockGame.Entity.stockEntity;
import helloAndroid.stockGame.Service.MainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/info")
public class MainController {
    private MainServiceImpl service;
    private stockEntity entity;

    @Autowired
    public MainController(MainServiceImpl service, stockEntity entity){
        this.service = service;
        this.entity = entity;
    }

    // 모든 종목정보 가져오기
    @GetMapping
    @ResponseBody
    public List<stockEntity> allStockInfo(){
        return service.DbStockfindAll();
    }

    // 각종목별 정보 가져오기 / 내가 산종목정보 포함
    @PostMapping
    @ResponseBody
    public stockEntity findStockInfo(@RequestParam("recivename") String recivename){
        entity.setStockName(recivename);
        return service.DbStockfind(entity);
    }

    // 매수햇을 경우
    @PostMapping("/toBuy")
    public int changePrice(@RequestParam("change_stock_price") String changePrice){
        stockEntity entity = service.toChangeStock(changePrice);
        return entity.getStockPrice();
    }

    // 매도햇을 경우
    @PostMapping("/toSell")
    public int sellChangePrice(@RequestParam("change_stock_name") String changeName){
        stockEntity entity = service.toSellStock(changeName);
        return entity.getStockPrice();
    }
}
