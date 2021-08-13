package helloAndroid.stockGame.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class stockEntity {
    private String StockName;
    private int StockPrice;
}
