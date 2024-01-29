package edu.escuelaing.arep.app;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;


public class HttpServer
{
    private static APIController myMoviesAPI = new APIController();

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
                System.out.println("=====================");
                uriStr = URLDecoder.decode(uriStr, "UTF-8");
                String movieInformation = getMovieInformation(uriStr);
                outputLine = HTMLBuilder.httpMovieInformation(movieInformation);
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

    public static String getMovieInformation(String uriStr ){
        try {
            String title = uriStr.split("=")[1];
            String movieJSON = myMoviesAPI.connectToMoviesAPI(title);
            return  movieJSON;
        }catch (IOException e){
            e.printStackTrace();
        }
        return HTMLBuilder.httpError();
    }
}

