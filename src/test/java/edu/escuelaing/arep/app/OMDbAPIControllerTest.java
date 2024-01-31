package edu.escuelaing.arep.app;


import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class OMDbAPIControllerTest {

    @Test
    public void testConnectToMoviesAPI() throws IOException {
        String expectedResponse = "{\"Title\":\"Inception\",";
        OMDbAPIController OMDbApiController = new OMDbAPIController();
        String actualResponse = OMDbApiController.connectToMoviesAPI("Inception");
        assertTrue(actualResponse.contains(expectedResponse));
    }

    @Test
    public void testConnectToMoviesAPIMovieNotExist() throws IOException {
        // Configuración de la prueba
        OMDbAPIController OMDbApiController = new OMDbAPIController();
        String actualResponse = OMDbApiController.connectToMoviesAPI("NonExistentMovie");
        assertNull(actualResponse);
    }


}
