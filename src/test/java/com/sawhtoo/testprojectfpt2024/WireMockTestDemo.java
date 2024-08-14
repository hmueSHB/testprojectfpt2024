package com.sawhtoo.testprojectfpt2024;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

@SpringBootTest
public class WireMockTestDemo {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

//    @Test
//    public void testWireMock() throws Exception {
//        // Stub the WireMock server
//        stubFor(get(urlEqualTo("/api/users/1"))
//                .willReturn(aResponse()
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"id\": 1, \"name\": \"Mock User\", \"age\": 25}")));
//
//        // Perform the request using MockMvc
//        mockMvc.perform(get("/api/users/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(content().json("{\"id\": 1, \"name\": \"Mock User\", \"age\": 25}"));
//
//
//        // Verify that the request was made to the WireMock server
//        verify(getRequestedFor(urlEqualTo("/api/users/1")));
//    }

    @Test
    public void testWireMock() throws IOException {
        configureFor("localhost", 8080);

        stubFor(get(urlEqualTo("/api/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"Mock User\", \"age\": 25}")));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8080/api/users/1");
        ClassicHttpResponse httpResponse = httpClient.execute(request);

        String responseString = convertResponseToString(httpResponse);

        verify(getRequestedFor(urlEqualTo("/api/users/1")));
        Assertions.assertEquals("{\"id\": 1, \"name\": \"Mock User\", \"age\": 25}", responseString);
    }

    private String convertResponseToString(ClassicHttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

    @AfterAll
    public static void stop() {
        wireMockServer.stop();
    }
}
