package helloAndroid.stockGame.Repository;

import helloAndroid.stockGame.DTO.stockInfo;
import helloAndroid.stockGame.Entity.stockEntity;
import helloAndroid.stockGame.Repository.RepositoryInterface.RepositoryInter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class DatabaseRepository implements RepositoryInter {

//    @Value("${spring.datasource.driver-class-name}")
    private String driver="com.mysql.cj.jdbc.Driver";
//    @Value("${spring.datasource.username}")
    private String userid="root";
//    @Value("${spring.datasource.url}")
    private String url="jdbc:mysql://localhost:3306/StockInfo?serverTimezone=UTC&characterEncoding=UTF-8";
//    @Value("${spring.datasource.password}")
    private String userpw="1234";

    private DriverManagerDataSource dataSource;
    private JdbcTemplate template;

    public DatabaseRepository() {

        log.info("dirve={}",driver);
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userid);
        dataSource.setPassword(userpw);

        template = new JdbcTemplate();
        template.setDataSource(dataSource);

    }


    public int save(stockEntity entity) {
        int result = 0;

        final String sql = "INSERT INTO stocklist (StockName, StockPrice) values (?,?)";
        result = template.update(sql,
                entity.getStockName(),
                entity.getStockPrice());

        return result;
    }

    // select구문 -> 한가지종목 조회
    public stockEntity stockSelect(stockEntity entity) {
        List<stockEntity> stocks = null;
        final String sql = "SELECT * FROM stocklist WHERE StockName = ?";

        stocks = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, entity.getStockName());

            }
        }, new RowMapper<stockEntity>() {

            @Override
            public stockEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                entity.setStockName(rs.getString("StockName"));
                entity.setStockPrice(rs.getInt("StockPrice"));
                return entity;
            }

        });
        if (stocks.isEmpty()) {
            return null;
        } else {
            return entity;
        }
    }

    // 전체종목 조회
    public List<stockEntity> stockFindAll() {
        List<stockEntity> stocks = null;
        final String sql = "SELECT * FROM stocklist";

        stocks = template.query(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {

            }
        }, new RowMapper<stockEntity>() {

            @Override
            public stockEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                stockEntity entity = new stockEntity();
                entity.setStockName(rs.getString("StockName"));
                entity.setStockPrice(rs.getInt("StockPrice"));
                return entity;
            }

        });
        if (stocks.isEmpty()) {
            return null;
        } else {
            return stocks;
        }
    }



    //update 구문
    public int stockUpdate(stockEntity entity){
        int result = 0;

        final String sql = "UPDATE stocklist SET StockPrice = ? WHERE StockName = ?";
        // select를 제외한 CUD들은 template.update를 사용하면 된다.
        result = template.update(sql,
                entity.getStockPrice(),
                entity.getStockName()
        );

        return result;
    }

    //delete 구문
    public int stockDelete(stockEntity entity) {
        int result = 0;

        final String sql = "DELETE FROM stocklist WHERE StockName = ?";
        result = template.update(sql,
                entity.getStockName()
        );

        return result;
    }

}

