package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class APIController {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://www.omdbapi.com/?apikey=4e3f8718&t=";

    private static ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<>();

    public APIController(){}

    public  String connectToMoviesAPI(String movie) throws IOException {
        String movieJSON = null;
        if(cache.containsKey(movie)){
            System.out.println(movie + " se encuentra en Caché");
            movieJSON = cache.get(movie);
        }else{
            URL obj = new URL(GET_URL + movie);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = connection.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
                cache.put(movie, response.toString());
                movieJSON = response.toString();
            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
        }
        return movieJSON;
    }
}
