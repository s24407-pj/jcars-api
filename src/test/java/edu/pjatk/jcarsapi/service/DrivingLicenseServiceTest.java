package edu.pjatk.jcarsapi.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DrivingLicenseServiceTest {

    private DrivingLicenseService underTest;
    public static MockWebServer mockBackEnd;


    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();

        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        underTest = new DrivingLicenseService(baseUrl);
    }


    @Test
    void shouldPassCheckDrivingLicense() {

        mockBackEnd.enqueue(
                new MockResponse()
                        .setBody("{\"dokumentPotwierdzajacyUprawnienia\":{\"stanDokumentu\":{\"stanDokumentu\":{\"wartosc\":\"Wydany\"}}}}")
                        .addHeader("Content-Type", "application/json")
                        .setResponseCode(200));

        Boolean result = underTest.checkDrivingLicense("validHash");
        assertTrue(result);
    }

    @Test
    void shouldNotPassCheckDrivingLicense() {

        mockBackEnd.enqueue(
                new MockResponse()
                        .setBody("{\"dokumentPotwierdzajacyUprawnienia\":{\"stanDokumentu\":{\"stanDokumentu\":{\"wartosc\":\"Zawieszony\"}}}}")
                        .addHeader("Content-Type", "application/json")
                        .setResponseCode(200));

        Boolean result = underTest.checkDrivingLicense("validHash");
        assertFalse(result);
    }
}
