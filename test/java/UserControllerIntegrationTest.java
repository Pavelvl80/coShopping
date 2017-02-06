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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Edvard Piri on 29.01.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class UserControllerIntegrationTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void loginWrongPasswordTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/login-request").param("email","test@test.com").param("password","wrong"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWrongEmailTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/login-request").param("email","wrong@wrong.com").param("password","qwerty"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWrongPasswordAndEmailTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/login-request").param("email","wrong").param("password","wrong"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginCorrectPasswordAndEmailTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/login-request").param("email","test@test.com").param("password","qwerty"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index.vm"))
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().attributeDoesNotExist("test"));
    }

    @Test
    public void dunno() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/get-ads-by-email-request").param("email","test@test.com"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isCreated());
    }

}
