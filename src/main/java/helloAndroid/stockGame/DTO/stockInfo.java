package helloAndroid.stockGame.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class stockInfo {
    private String stock_name;
    private Integer stock_price;

    public stockInfo(){
    }

    public stockInfo(String stock_name, Integer stock_price){
        this.stock_name=stock_name;
        this.stock_price=stock_price;
    }
}
