package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Edvard Piri on 04.02.2017.
 */
@Component
public class BeanExample {
    private String name;

    @Bean(name = "test")
    BeanExample crateBeanExample() {
        BeanExample beanExample = new BeanExample();
        beanExample.setName("1111");
        return beanExample;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
