package com.sawhtoo.testprojectfpt2024;

import com.sawhtoo.testprojectfpt2024.controller.HomeController;
import com.sawhtoo.testprojectfpt2024.service.UtilityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class UnitTestDemo {

    private static final Logger log = LoggerFactory.getLogger(UnitTestDemo.class);

    @Autowired
    HomeController homeController;

    @Autowired
    UtilityService utilityService;

    @Test
    void testHomeController() {
        log.info(homeController.hello());
        assertEquals("Hello There!", homeController.hello());
    }

    @Test
    @DisplayName("Sum Two Numbers")
    void testTwoSumNumber() {
        assertEquals(2, utilityService.twoSumNumber(1, 1));
    }
}
