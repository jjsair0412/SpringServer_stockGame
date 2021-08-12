package helloAndroid.stockGame.Repository.RepositoryInterface;

import helloAndroid.stockGame.Entity.stockEntity;

public interface RepositoryInter {

    int save(stockEntity entity);
    stockEntity stockSelect(stockEntity entity);
    int stockUpdate(stockEntity entity);
    int stockDelete(stockEntity entity);
}
