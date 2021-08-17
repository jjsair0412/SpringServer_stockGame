package helloAndroid.stockGame.Controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// static import를해야 .status나 print가 정상작동한다.
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebAppConfiguration
@ContextConfiguration()
@WebMvcTest(MainController.class)
@RunWith(SpringJUnit4ClassRunner.class)
class MainControllerTest {
    @Autowired
    private MainController mainController;
    @Autowired
    private MockMvc mockMvc;

    // mvc를 테스트하기 위해 mockMvc를 만들어준다.
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    void allStockInfo() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("localhost:7090/info")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
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