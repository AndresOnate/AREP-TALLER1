package edu.escuelaing.arep.app;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

/**
 * The `HttpServer` class represents a simple HTTP server that listens on port 35000 and handles incoming HTTP requests.
 * It utilizes a basic API for retrieving movie information and responds with HTML content based on the request URI.
 *
 * It supports requests related to movie information and provides a default HTML response.
 */
public class HttpServer
{
    /**
     * Represents the API controller for fetching movie information.
     */
    private static MovieAPI myMoviesAPI = new APIController();


    /**
     * The main method that serves as the entry point for the HTTP server.
     */
    public static void main( String[] args ) throws Exception
    {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean firstLine = true;
            String uriStr = "";
            while ((inputLine = in.readLine()) != null) {
                if(firstLine){
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            if(uriStr.contains("movies")){
                uriStr = URLDecoder.decode(uriStr, "UTF-8");
                outputLine =  getMovieInformation(uriStr);
            }else{
                outputLine = HTMLBuilder.httpClientHtml();
            }
            out.println(outputLine);
            out.println();
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Retrieves movie information based on the provided URI and returns the corresponding HTML response.
     * @param uriStr The URI containing the movie title information.
     * @return The HTML response containing movie data or an error message.
     */
    public static String getMovieInformation(String uriStr ){
        String title = uriStr.split("=")[1].toLowerCase();
        try {
            String movieJSON = myMoviesAPI.connectToMoviesAPI(title);
            if(movieJSON != null) {
                return HTMLBuilder.httpMovieData(movieJSON);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return HTMLBuilder.httpError(title);
    }
}

