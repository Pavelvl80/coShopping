import com.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AdControllerIntegrationTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getAdsByEmailWrongEmailTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/get-ads-by-email-request").param("email","wrong@wrong.com"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAdsByEmailCorrectEmailTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/get-ads-by-email-request").param("email","lotar@smail.ru"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAdsByEmailCorrectEmailUserWithoutAdsTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/get-ads-by-email-request").param("email","lotar@mail.ru"))
                .andExpect(content().string("\"__ALL_ADS__\": \"\", \"__EXPENSIVE__\": \"non\", \"__CHEAPEST__\": \"non\""))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status(). isOk());
    }

    @Test
    public void getAdsByEmailUserWithAdsTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/get-ads-by-email-request").param("email","wrong@wrong.com"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isNotFound());
    }
}
