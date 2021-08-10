package com.MIU.OnlineJob;

import static org.assertj.core.api.Assertions.assertThat;

import com.MIU.OnlineJob.Controllers.HomeController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void HomeControllerContextLoads() throws Exception {
        assertThat(homeController).isNotNull();
    }
}