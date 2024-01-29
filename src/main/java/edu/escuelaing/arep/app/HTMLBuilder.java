package edu.escuelaing.arep.app;

import org.json.JSONArray;
import org.json.JSONObject;

public class HTMLBuilder {


    public static String httpMovieInformation(String movieData) {
        System.out.println("Data:  " + movieData);
        JSONObject movieObject = new JSONObject(movieData);
        System.out.println(movieObject.toString());

        StringBuilder tableHtml = new StringBuilder();
        tableHtml.append("<table border=\"1\">\n");
        for (String key : movieObject.keySet()) {
            if (key.equals("Ratings")) {
                // Tratar "Ratings" como un array
                JSONArray ratingsArray = movieObject.getJSONArray(key);
                tableHtml.append("<tr><td>").append(key).append("</td><td>").append(parseRatingsArray(ratingsArray)).append("</td></tr>\n");
            } else {
                // Tratar otras claves como cadenas
                String value = movieObject.getString(key);
                tableHtml.append("<tr><td>").append(key).append("</td><td>").append(value).append("</td></tr>\n");
            }
        }
        tableHtml.append("</table>");
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Movies Information</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        " + tableHtml.toString() +
                "    </body>\n" +
                "</html>";
    }


    private static String parseRatingsArray(JSONArray ratingsArray) {
        // Parsear el array de ratings y construir una representación en cadena
        StringBuilder ratingsHtml = new StringBuilder();
        for (int i = 0; i < ratingsArray.length(); i++) {
            JSONObject ratingObject = ratingsArray.getJSONObject(i);
            String source = ratingObject.getString("Source");
            String value = ratingObject.getString("Value");
            ratingsHtml.append("(").append(source).append(": ").append(value).append(") ");
        }
        return ratingsHtml.toString();
    }

    public static String httpClientHtml() {
        String outputLine =
                "HTTP/1.1 200 OK\r\n"
                        + "Content-Type:text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "    <head>\n" +
                        "        <title>Movies Information</title>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <h1>Get Movie Information</h1>\n" +
                        "        <form action=\"/movies\">\n" +
                        "            <label for=\"title\">Title:</label><br>\n" +
                        "            <input type=\"text\" id=\"title\" name=\"title\"><br><br>\n" +
                        "            <input type=\"button\" value=\"Search\" onclick=\"loadGetMsg()\">\n" +
                        "        </form> \n" +
                        "        <div id=\"getrespmsg\"></div>\n" +
                        "\n" +
                        "        <script>\n" +
                        "            function loadGetMsg() {\n" +
                        "                let nameVar = document.getElementById(\"title\").value;\n" +
                        "                const xhttp = new XMLHttpRequest();\n" +
                        "                xhttp.onload = function() {\n" +
                        "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                        "                    this.responseText;\n" +
                        "                }\n" +
                        "                xhttp.open(\"GET\", \"/movies?name=\"+ nameVar);\n" +
                        "                xhttp.send();\n" +
                        "            }\n" +
                        "        </script>\n" +
                        "    </body>\n" +
                        "</html>";
        return outputLine;
    }

    public static String httpError() {
        String outputLine = "HTTP/1.1 400 Not Found\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "<h1>Error</h1>" +
                "    </body>\n" +
                "</html>";
        return outputLine;
    }

}
