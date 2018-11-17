package com.mkyong.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class BaseHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "This is the response";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
//        Map<String, String> mapParam = queryToMap(httpExchange.getRequestURI().getQuery());
//        System.out.println(mapParam);

        Map<String, Object> params =
                (Map<String, Object>)httpExchange.getAttribute("parameters");
        System.out.println("param:"+params);

        os.write(response.getBytes());
        os.close();



    }

    public Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public String convertInputStreamToString(InputStream inputStream) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            // StandardCharsets.UTF_8.name() > JDK 7
            return result.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}


