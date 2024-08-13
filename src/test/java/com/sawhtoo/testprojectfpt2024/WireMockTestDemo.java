package com.sawhtoo.testprojectfpt2024;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
public class WireMockTestDemo {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @Test
    public void testWireMock() {
        configureFor("localhost", 8080);
        //stubFor(get(urlEqualTo("/baeldung")).willReturn(aResponse().withBody("Welcome to Baeldung!")));

        stubFor(get(urlEqualTo("/api/users/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"Mock User\", \"age\": 25}")));

        verify(getRequestedFor(urlEqualTo("/api/users/1")));
    }

    @AfterAll
    public static void stop() {
        wireMockServer.stop();
    }
}
