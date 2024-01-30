package edu.escuelaing.arep.app;


import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class APIControllerTest {

    @Test
    public void testConnectToMoviesAPI() throws IOException {
        String expectedResponse = "{\"Title\":\"Inception\",";
        APIController apiController = new APIController();
        String actualResponse = apiController.connectToMoviesAPI("Inception");
        assertTrue(actualResponse.contains(expectedResponse));
    }

    @Test
    public void testConnectToMoviesAPIMovieNotExist() throws IOException {
        // Configuración de la prueba
        APIController apiController = new APIController();
        String actualResponse = apiController.connectToMoviesAPI("NonExistentMovie");
        assertNull(actualResponse);
    }


}
