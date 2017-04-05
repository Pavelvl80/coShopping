package com.testJunitCoreRunner;

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
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ad/get-ads-by-email/expensive=false&cheapest=false").param("email", "wrong@wrong.com"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void get_all_ads_expensive_false_cheapest_false_correct_email_test() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ad/get-ads-by-email/expensive=false&cheapest=false").param("email", "Lotar@gmail.com"))
                .andExpect(content().string("{\"all\":[]}"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_all_ads_expensive_true_cheapest_false_correct_email_test() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ad/get-ads-by-email/expensive=true&cheapest=false").param("email", "Lotar@gmail.com"))
                .andExpect(content().string("{\"expensive\":null}"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_all_ads_expensive_false_cheapest_true_correct_email_test() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ad/get-ads-by-email/expensive=false&cheapest=true").param("email", "Lotar@gmail.com"))
                .andExpect(content().string("{\"cheapest\":null}"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_all_ads_expensive_true_cheapest_true_correct_email_test() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ad/get-ads-by-email/expensive=true&cheapest=true").param("email", "Lotar@gmail.com"))
                .andExpect(content().string("{\"cheapest\":null,\"expensive\":null}"))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAdsByEmailCorrectEmailUserWithoutAdsTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ad/get-ads-by-email/expensive=false&cheapest=false").param("email", "Lotar@gmail.com"))
//                .andExpect(content().string("\"__ALL_ADS__\": \"\", \"__EXPENSIVE__\": \"non\", \"__CHEAPEST__\": \"non\""))
//                .andExpect(content().contentType("application/json;charset=utf-8"))
//                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
                .andExpect(status().isOk());
    }

//    @Test
//    public void user_exist_test() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/get"))
//    }

//    @TestDb
//    public void getAdsByEmailUserWithAdsTest() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/get-ads-by-email-request").param("email","wrong@wrong.com"))
////                .andExpect(content().contentType("application/json;charset=utf-8"))
////                .andExpect(content().string("{\"error\":\"Wrong password\"}"))
//                .andExpect(status().isNotFound());
//    }
}
